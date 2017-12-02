package com.revature.designpatterns;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		/*
		 * SINGLETON
		 * must b the getInstance() method
		 */
		
		Singleton single = Singleton.getInstance();
		single.hello();
		System.out.println(single.count);
		
		Singleton anotherone = Singleton.getInstance();
		anotherone.count++;
		System.out.println(single.count);
		
		/*
		 * FACTORY DESIGN PATTERN
		 * Here, we create a object without exposing creation logic to the client.
		 * We refer to the newly create object using a common interface
		 */
		
		
		ToolFactory factory = new ToolFactory();
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose a tool: ");
		String tool = scan.nextLine().toLowerCase();
		
		Tool t = factory.workWithTool(tool);
		System.out.println(t.work());
		
		
	}
	
}