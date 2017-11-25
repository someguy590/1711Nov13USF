package com.revature.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.pojo.User;
import com.revature.service.Service;


public class BankDriver {
	static String filename = "src/logs/user.txt";
	static Service service = new Service();
	public static void main(String[] args){
		File makefile = new File(filename);
		try {
			makefile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run();
		
	}
	
	public static void run() {
		System.out.println("Welcome to DE Bank!");
		System.out.println("Would you like to LOGIN(1), CREATE(2) account, or EXIT(3)?");		
		
		Scanner sc = new Scanner(System.in);
		String op = sc.nextLine();
		
		switch(op) {
		
		case "1":
			login();
			break;
		case "2": 
			createAccount();
			break;
		case "3":
			break;
		default: 
			run();
		
		}
		sc.close();
	}
	
	static User login() {

		User ul = new User();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a valid username: ");
		ul.setUsername(in.nextLine());
		System.out.println("Please enter your password: ");
		ul.setPassword(in.nextLine());
		
		service.logIn(ul);
		in.close();
		return ul;
	}
	
	static User createAccount(){

		User u = new User();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your USERNAME:");
		u.setUsername(in.nextLine());
		System.out.println("Please enter your FIRST NAME:");
		u.setFirstname(in.nextLine());
		System.out.println("Please enter your LAST NAME:");
		u.setLastname(in.nextLine());
		System.out.println("Please enter your PASSWORD:");
		u.setPassword(in.nextLine());
		u.setBalance((double)100.00);
		service.addUser(u);	
		in.close();
		return u;
	}
}
