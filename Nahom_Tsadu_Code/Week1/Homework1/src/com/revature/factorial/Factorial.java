package com.revature.factorial;

public class Factorial {
	public static void main(String[] args) {
		int num = 7;
		System.out.println("Number: " + num);
		System.out.println("The factorial of " + num + " is " + factorial(num));
	}
	
	static int factorial(int n){
		if(n == 0){return 0;}
		else if((n-1)>0){
			n = n*factorial(n-1);
			return n;
		}else{
			return 1;
		}
	}
}
