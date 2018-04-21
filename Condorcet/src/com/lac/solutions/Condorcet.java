package com.lac.solutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Condorcet {

	//private static int BALLOT_BOUND = 501;
	//private static int CANDIDATE_BOUND = 2501;
	
	private static int WIN = 1;
	private static int LOST = 0;
	private static int SELF = 0;
	
	private static int BALLOT_BOUND = 10;
	private static int CANDIDATE_BOUND = 10;
	
	private int numberOfBallots;
	private int numberOfCandidates;
	private Integer[][] matrix;

	private Random random;
	
	public Condorcet() {
		random = new Random();
		setNumberOfBallots();
		setNumberOfCandidates();
		//numberOfBallots = 3;
		//numberOfCandidates = 3;
		matrix = new Integer[numberOfCandidates][numberOfCandidates];
		initMatrix();
	}

	public int getNumberOfBallots() {
		return numberOfBallots;
	}

	public void setNumberOfBallots() {
		this.numberOfBallots = random.nextInt(BALLOT_BOUND);;
	}

	public int getNumberOfCandidates() {
		return numberOfCandidates;
	}

	public void setNumberOfCandidates() {
		this.numberOfCandidates = random.nextInt(CANDIDATE_BOUND);;
	}
	
	public Integer[][] getMatrix(){
		return matrix;
	}
	
	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}
	
	public Integer[][] generateBallots() {
		Integer ballots[][] = new Integer[numberOfBallots][numberOfCandidates];
		for (int i = 0; i < numberOfBallots; i++) {
			List<Integer> candidates = generateCandidates();
			//for each voter, select his/her preference of candidate order
			for (int j = 0; j < numberOfCandidates; j++)
				ballots[i][j] = selectCandidate(candidates);
		}
		writeArrayToFile(ballots);
		return ballots;
	}

	public void generateFinalResult (Integer ballots[][]) {
		//Integer metrix[][] = new Integer[numberOfCandidates][numberOfCandidates];
		Map<Integer,Integer> candidateOrder = null;
		Integer ballot[];
		List<Integer> candidates = generateCandidates();
		for(int i=0;i<ballots.length;i++){
			ballot = ballots[i];
			candidateOrder = getCandidateOrder(ballot,candidates);
			Integer pairMetrix[][] = countPairWise(candidateOrder);
			addMetrix(pairMetrix);
		}
		writeArrayToFile(matrix);
	}
	
	/**
	 * (runner, opponent) - (opponent,runner) > 0
	 * @return
	 */
	public int findWinner(){
		int winner = -1;
		for(int i=0;i<numberOfCandidates;i++) {
			for(int j=0;j<numberOfCandidates;j++) {
				if(i!=j) {
					int runnerBeatOpponent = matrix[i][j];
					int opponentBeatRunner = matrix[j][i];
					if(runnerBeatOpponent-opponentBeatRunner<=0)
						break;
				}
				if(j==numberOfCandidates-1)
					winner = i;
			}
		}
		return winner;
	}
	
	/**
	 * in one ballot, decide who is the winner and who lost
	 * @param candidateOrder eg:[0-3,1-1,2-0,3-2]
	 * @return
	 * {[0,0,0,0]
	 *  [1,0,0,1]
	 *  [1,1,0,1]
	 *  [1,0,0,0]
	 * }
	 */
	private Integer[][] countPairWise(Map<Integer,Integer> candidateOrder) {
		Integer pairMatrix[][] = new Integer[numberOfCandidates][numberOfCandidates];
		for(int i=0;i<pairMatrix.length;i++){
			//get the runner candidate order in the ballot;
			int runnerIndex = candidateOrder.get(i);
			for(int j=0;j<pairMatrix[i].length;j++){
				if(i==j) pairMatrix[i][j] = SELF;
				else{
					int opponentIndex = candidateOrder.get(j);
					if(runnerIndex < opponentIndex)
						pairMatrix[i][j] = WIN;
					else
						pairMatrix[i][j] = LOST;
				}
			}
		}
		writeArrayToFile(pairMatrix);
		return pairMatrix;
	}
	
	/**
	 * add two matrix
	 * @param ballotResult
	 */
	private void addMetrix(Integer[][] ballotResult) {
		for(int i=0;i<numberOfCandidates;i++){
			for(int j=0;j<numberOfCandidates;j++){
				matrix[i][j] += ballotResult[i][j];
			}
		}
	}
	
	/**
	 * what is the candidate's order in one ballot
	 * @param ballot  for example[3,1,2,4,6,5,0]
	 * @param candidates [0,1,2,3,4,5,6]
	 * @return[0-5,1-1,2-2,3-0,4-3,5-5,6-4]
	 */
	private Map<Integer,Integer> getCandidateOrder(Integer ballot[],List<Integer> candidates) {
		Map<Integer,Integer> candidateOrder = new HashMap<Integer,Integer>();
		List<Integer> ballotList = Arrays.asList(ballot);
		for(int i=0;i<candidates.size();i++){
			int candidate = candidates.get(i);
			int index = ballotList.indexOf(candidate);
			candidateOrder.put(candidate, index);
		}
		return candidateOrder;
	}

	/**
	 * generate candidate list according to the number of candidates
	 * for example, there are 5 candidates, then from list0 to list5 is 0,1,2,3,4,5
	 * @return 
	 */
	private List<Integer> generateCandidates() {
		List<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0; i < numberOfCandidates; i++)
			candidates.add(i);
		return candidates;
	}

	//select candidate from the candidate list
	private int selectCandidate(List<Integer> candidates) {
		int length = candidates.size();
		int index = random.nextInt(length);
		int selected = candidates.get(index);
		candidates.remove(index);
		return selected;
	}
	
	private void initMatrix(){
		matrix = new Integer[numberOfCandidates][numberOfCandidates];
		for(int i=0;i<numberOfCandidates;i++)
			for(int j=0;j<numberOfCandidates;j++)
				matrix[i][j] = 0;
	}

	private void writeArrayToFile(Integer[][] ballots) {
		File f = new File("D:\\ballots.txt");
		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true)));
			for (int i = 0; i < ballots.length; i++) {
				for (int j = 0; j < ballots[i].length; j++) {
					outputWriter.write(ballots[i][j] + " ");
				}
				outputWriter.newLine();
			}
			outputWriter.newLine();
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				outputWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Condorcet cond = new Condorcet();
		int numberOfBallots = cond.getNumberOfBallots();
		int numberOfCandidates = cond.getNumberOfCandidates();
		System.out.println("numberOfBallots "+numberOfBallots);
		System.out.println("numberOfCandidates "+numberOfCandidates);
		if(numberOfBallots ==0 || numberOfCandidates == 0) return;
		Integer ballots[][] = cond.generateBallots();
		
		for(int i=0;i<ballots.length;i++){
			for(int j=0;j<ballots[i].length;j++) {
				System.out.print(ballots[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		/*Integer ballots[][] = {
				{5,	2,	4,	1,	3,	0},
				{0,	5,	3,	4,	1,	2},
				{3,	1,	4,	0,	5,	2},
				{1,	2,	0,	4,	5,	3}
		};
		
		Integer ballots[][] = {
				{0,1,2},
				{1,0,2},
				{2,1,0}
		};*/
		cond.generateFinalResult(ballots);
		Integer[][] matrix = cond.getMatrix();
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		//Integer matrix[][] = {{0,2,1},{1,0,1},{2,2,0}};
		cond.setMatrix(matrix);
		int winner = cond.findWinner();
		System.out.println(winner);
	}
}
