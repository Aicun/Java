package com.lac.ahaalgorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Decrypt {
	LinkedList<Integer> linkedList;

	public static void main(String[] args) {
		Decrypt d = new Decrypt();
		d.initList();
		d.decrypt();
	}
	
	public void initList() {
		int[] numbers = new int[] {6,3,1,7,5,8,9,2,4};
		List<Integer> nums = Arrays.stream(numbers).boxed().collect(Collectors.<Integer>toList());
		linkedList = new LinkedList(nums);
	}
	
	public void decrypt() {
		int head = 0, tail = linkedList.size()-1;
		while(head <= tail) {
			System.out.println(linkedList.get(head));
			head++;
			
			//move head to tail
			linkedList.add(linkedList.get(head));
			tail++;
			head++;
		}
	}
}
