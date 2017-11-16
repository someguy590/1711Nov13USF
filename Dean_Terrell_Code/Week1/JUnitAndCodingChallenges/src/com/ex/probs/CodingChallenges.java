package com.ex.probs;

public class CodingChallenges {
	
	public int factorial(int n) {
		if (n < 0) {System.out.println("cannot be negative"); return 0;}
		else if(n == 0)
			return 1;
		
		return n * factorial(n-1);
	}
	
	public String reverseString(String input) {
		String output = "";
		for(int i = input.length()-1; i >= 0; i--) {
			output = output.concat(String.valueOf(input.charAt(i)));
		}
		return output;
	}
	
	public boolean isEven(int i) {
		if((i - (i / 2) * 2) > 0)
			return false;
		return true;
	}
}
