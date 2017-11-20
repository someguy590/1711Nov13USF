package com.revature.bubblesort;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		
		int[] arr = {1,0,5,5,3,2,3,5,9,8,4};
		bubbleSort(arr);
	}
	
	static int[] bubbleSort(int[] arr){
		
		boolean sorted = true;
		int swapCount = 0;
		int passCount = 0;
		
		System.out.println("Running Bubble Sort...");
		
		while(sorted){
			
			swapCount = 0;
		
			for(int i = 0; i < arr.length; i++){
				
				if(i < arr.length - 1){
					if(arr[i] > arr[i + 1]){
						int temp = arr[i + 1];
						arr[i + 1] = arr[i];
						arr[i] = temp;
						swapCount++;
					}
				}
				
				if(i > 0){
					if(arr[i] < arr[i - 1]){
						int temp = arr[i - 1];
						arr[i - 1] = arr[i];
						arr[i] = temp;
						swapCount++;
					}
				}
			}
			
			if(passCount > 0 && swapCount == 0){
				
				sorted = true;
				break;
			}
			
			passCount++;
		}
		
		System.out.println("Bubble Sort Completed in " + passCount + " Passes!");
		System.out.print("Sorted List: ");
		Arrays.stream(arr).forEach(n -> System.out.print(n + ", "));
		System.out.println();
		System.out.println();
			
		return arr;
	}
}
