package com.ex.probs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Warmup {

	public static void main(String[] args) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		for(int i = 1; i <= 100; i++)
			primes.add(i);
		
		for(int i: primes) {
			if(isPrime(i))
				System.out.println(i);
		}
		
		System.out.println("Minimum number is " + (minNum(5, 3)));
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Choose an option!\n1. Square root\n2. Display today's date\n3. Split a string");
		
		int choice = 0;
		do {
			choice = s.nextInt();
			
			if(choice < 1 || choice > 3)
				System.out.println("Invalid choice");
		} while(choice < 1 || choice > 3);
		
		switch(choice) {
		case 1:
			System.out.println("Enter a number");
			double i = s.nextDouble();
			Math.abs(i);
			System.out.println("Square root of " + i + " is " + Math.sqrt(i));
			break;
		case 2:
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate));
			break;
		case 3:
			String str = "I am learning core Java";
			String[] words = str.split(" ");
			ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
			System.out.println(wordList);
		}

	}
	
	public static boolean isPrime(int num) {
		if (num < 2) return false;
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		for (int i = 3; i * i <= num; i+= 2)
			if (num % i == 0) return false;
		return true;
	}
	
	public static int minNum(int i, int k) {
		return (i < k) ? i : k;
	}

}
