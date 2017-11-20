package com.revature.palindromes;

import java.util.ArrayList;

import com.revature.iseven.IsEven;

public class Palindromes {
	public static void main(String[] args) {
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("karan");
		stringList.add("madam");
		stringList.add("tom");
		stringList.add("civic");
		stringList.add("radar");
		stringList.add("sexes");
		stringList.add("jimmy");
		stringList.add("kayak");
		stringList.add("john");
		stringList.add("refer");
		stringList.add("billy");
		stringList.add("did");
		
		System.out.println(sortPalindromes(stringList));
		
	}
	
	static ArrayList<String> sortPalindromes(ArrayList<String> s){
		ArrayList<String> palindromeList = new ArrayList<String>();
//		boolean t = true;
		s.stream().forEach(str -> {
			System.out.println();
			int length = str.length();
			int mid = (length / 2) + 1;
			boolean match = true;
			
			if(IsEven.isEven(length) != true){
				for(int i = 0; i < mid; i++){
					System.out.println(str.charAt(i) + ", " + str.charAt((length - 1) - i));
					if(str.charAt(i) != str.charAt((length - 1) - i)){
						System.out.println("FALSE"); 
						System.out.println();
						match = false; 
						break;
					}
				}
				
				if(match == true){
					System.out.println();
					System.out.println(str + " is a palindrome!");
					palindromeList.add(str);
				}
			}
			System.out.println("------------------------------------------------------");
		});
		
		System.out.println();
		return palindromeList;
	}
}
