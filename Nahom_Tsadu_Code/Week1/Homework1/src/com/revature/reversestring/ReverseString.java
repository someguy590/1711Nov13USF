package com.revature.reversestring;

public class ReverseString {
	
	public static void main(String[] args) {
		
		reverse("pots");
	}
	
	static String reverse(String s){
		System.out.println("Original String: " + s);
		System.out.println("Reversing...");
		char[] cArr = s.toCharArray();
		for(int i = s.length()-1; i >= 0; i--) cArr[s.length()-(i+1)] = s.charAt(i);
		s = new String(cArr);
		System.out.println("New String: " + s);
		return s;
	}
}
