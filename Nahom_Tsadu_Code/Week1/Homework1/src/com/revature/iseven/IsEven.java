package com.revature.iseven;

public class IsEven {
	public static void main(String[] args) {
		isEven(6);
	}
	
	public static boolean isEven(double x){
		boolean isEven = false;
		System.out.println("Checking number without using modulus..");
		if(x > 0){
			double num = x/2;
			int intNum = (int) Math.floor(num);
			double remainder = num - intNum;
			if(remainder == 0) isEven = true;
		}else{
			System.out.println("Please enter a number greater than zero");
		}
		System.out.println("The number " + (int)x + " is" + (!isEven ? " not " : " ") + "even" );
		System.out.println();
		return isEven;
	}
}
