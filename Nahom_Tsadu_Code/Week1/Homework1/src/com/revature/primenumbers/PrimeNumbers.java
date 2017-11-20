package com.revature.primenumbers;

import java.util.ArrayList;

public class PrimeNumbers {
	public static void main(String[] args) {
		printPrimeElements(new ArrayList<Integer>());
	}
	
    public static boolean isPrime(int x){
        for(int i = 2; i < x; i++){
            if(x%i == 0) return false;}
        return true;
    }
    
	public static void printPrimeElements(ArrayList<Integer> n){
		System.out.println("Printing all prime numbers from 1 to 100..");
		for(int i = 1; i <= 100; i++){
			n.add(i);
			if(isPrime(i)) System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();
	}
}
