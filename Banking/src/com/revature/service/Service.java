package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.dao.DAO;
import com.revature.dao.FileDAO;
import com.revature.main.BankDriver;
import com.revature.pojo.User;

public class Service {
	
	static DAO dao = new FileDAO();

	public User addUser(User u){
		//validate that username does not exist 
		// assuming that it DNE:
		if(dao.getUser().contains(u.getUsername())) {
			System.out.println("Username is already taken. Please try again.");
			System.out.println("--------------------------------------------");
			BankDriver.run();
			return null;
		}
		
		dao.addUser(u);
		
		System.out.println("Congratulations! You created an account.");
		System.out.println("Your initial balance is $" + u.getBalance() + ".");
		BankDriver.run();
		return u;
	}
	
	
	public static User logIn(User u) {
		
		ArrayList<String> result = new ArrayList<>();
		result = dao.logOn(u.getUsername(), u.getPassword());
		if(!(dao.getUser().contains(u.getUsername()))) {
			System.out.println("Username does not exist! Please try again.");
			System.out.println("--------------------------------------------");
			BankDriver.run();
			return null;
		}

		if(result == null) {
			System.out.println("Username/Password mismatch. Please try again.");
			System.out.println("--------------------------------------------");
			BankDriver.run();
			return null;
		}
		
		System.out.println();
		System.out.println("Login Success!");
		System.out.println();
		System.out.println("WELCOME " + result.get(0) + "!");
		transac(u, result);
		return u;
	}
	
	public static User transac(User u, ArrayList<String> arr) throws NoSuchElementException{
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Do you want to EXIT(1), DEPOSIT(2) or WITHDRAW(3)?");
		System.out.println();
		String lo = scan.nextLine();
		switch(lo) {
		case "1":
			System.out.println("Goodbye!");
			break;
		case "2":
			depo(u, arr);
			break;
		case "3":
			with(u, arr);
			break;
		default:
			transac(u, arr);
		}
		scan.close();
		return u;
		
	}


	static void with(User u, ArrayList<String> userInfo) {
		// TODO Auto-generated method stub
		System.out.println("How much money would you like to withdraw?");
		double dew = Double.parseDouble(userInfo.get(4));
		Scanner c = new Scanner(System.in);
		double wit = c.nextDouble();
		if(wit > dew) {
			
			System.out.println("Cannot withdraw more than current balance.");
			transac(u, userInfo);
		}
		dao.withTransac(wit, dew);
		
		System.out.println("Successfully withdrawn money.");

		System.out.println("Amount taken: " + wit);
		transac(u, userInfo);
		c.close();
	}


	static void depo(User u, ArrayList<String> uInfo) {
		System.out.println("How much money would you like to deposit?");
		double cur = Double.parseDouble(uInfo.get(4));
		Scanner c = new Scanner(System.in);
		double dep = c.nextDouble();
		dao.depTransac(dep, cur);
		
		System.out.println("Successfully deposited money.");

		System.out.println("Amount Deposited: " + dep);
		transac(u, uInfo);
		c.close();

	}

}