package com.ex.probs;

public class CodingChallenges {

	public int factorial(int n){
		int multiplied=n;
		if(n>1){
			multiplied*=factorial(n-1);
		}
		else if(n==0){
			multiplied=1;
		}
		return multiplied;
		
	}
	
	String reverse(String str){
		String rev="";
		if (str.length()>1){
			rev+=str.charAt(str.length()-1);
			rev+=reverse(str.substring(0,str.length()-1));
		} else if(str.length()==1){
			rev=str;
		}
		return rev;
	}
	
}
