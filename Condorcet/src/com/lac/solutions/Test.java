package com.lac.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Test {
	
	private Random random = new Random();

	public static void main(String[] args) {
		Map<Integer,Integer>candidateOrder = new HashMap<Integer,Integer>();
		candidateOrder.put(0, 3);
		candidateOrder.put(1, 1);
		candidateOrder.put(2, 0);
		candidateOrder.put(3, 2);
		Integer pairMatrix[][] = new Integer[4][4];
		for(int i=0;i<pairMatrix.length;i++){
			//get the runner candidate order in the ballot;
			int runnerIndex = candidateOrder.get(i);
			for(int j=0;j<pairMatrix[i].length;j++){
				if(i==j) pairMatrix[i][j] = 0;
				else{
					int opponentIndex = candidateOrder.get(j);
					if(runnerIndex < opponentIndex)
						pairMatrix[i][j] = 1;
					else
						pairMatrix[i][j] = 0;
				}
			}
		}
		for(int i=0;i<pairMatrix.length;i++){
			for(int j=0;j<pairMatrix[i].length;j++){
				System.out.print(pairMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public List<Integer> generateCandidates(int numberOfCandidates){
		List<Integer> candidates = new ArrayList<Integer>();
		for(int i=0;i<numberOfCandidates;i++)
			candidates.add(i);
		return candidates;
	}
	
	public int selectCandidate(List<Integer> candidates) {
		int length = candidates.size();
		int index = random.nextInt(length);
		int selected = candidates.get(index);
		candidates.remove(index);
		return selected;
	}
	
	public Map<Integer,Integer> map(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<100;i++){
			map.put(i, random.nextInt(100));
		}
		sortMap(map);
		return map;
	}
	
	private void sortMap(Map<Integer, Integer> result) {
		List<Map.Entry<Integer, Integer>> mappingList = new ArrayList<Map.Entry<Integer, Integer>>(
				result.entrySet());
		Collections.sort(mappingList,
				new Comparator<Map.Entry<Integer, Integer>>() {
					@Override
					public int compare(Entry<Integer, Integer> count1,
							Entry<Integer, Integer> count2) {
						return count1.getValue().compareTo(count2.getValue());
					}
				});
	}
}
