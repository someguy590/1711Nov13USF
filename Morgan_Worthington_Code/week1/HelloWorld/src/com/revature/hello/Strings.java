package com.revature.hello;

import java.util.Scanner;

public class Strings {
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in);
		System.out.println("Input string 1. If done, just press enter.");
		String input1=sc.nextLine();
		System.out.println("Input string 2. If done, just press enter.");
		String input2=sc.nextLine();
		
		System.out.println("String 1 is a substring of String 2: ");
		if (input2.indexOf(input1)<0){
			System.out.println("no");
		} else{
			System.out.println("yes");
		}
		System.out.println("length of string 1: " + input1.length());
		System.out.println("length of string 2: " + input2.length());
		System.out.println("string 1 and string 2 are the same:"+input1.equals(input2));
		
		while(!input1.equals("")&&!input2.equals("")){
			System.out.println("Input string 1. If done, just press enter.");
			input1=sc.nextLine();
			System.out.println("Input string 2. If done, just press enter.");
			input2=sc.nextLine();
			
			System.out.println("String 1 is a substring of String 2: ");
			if (input2.indexOf(input1)<0){
				System.out.println("no");
			} else{
				System.out.println("yes");
			}
			
			
			System.out.println("length of string 1: " + input1.length());
			System.out.println("length of string 2: " + input2.length());
			System.out.println("string 1 and string 2 are the same:"+input1.equals(input2));
		}
		
	}
	
	
	
	
}
