package com.ex.probs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CodingChallengesTest {
	
	private CodingChallenges c;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Welcome to the CodingChallenges Test!");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		System.out.println();
		System.out.println("Test Complete!");
	}

	@Before
	public void setUp() throws Exception {
			c = new CodingChallenges();
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testFactorial() {
		System.out.println();
		System.out.println();
		System.out.println("Test Case: testFactorial()");
		System.out.println("--------------------------------------------------------");
		/*
		 * Insert Assertions Below
		 */
		equals(120, c.factorial(5), "1. 5! equals 120");
		
		System.out.println("--------------------------------------------------------");
	}
	
	@Test
	public void testFactorialZero(){
		System.out.println();
		System.out.println();
		System.out.println("Test Case: testFactorialZero()");
		System.out.println("--------------------------------------------------------");
		/*
		 * Insert Assertions Below
		 */
		notEquals(1, c.factorial(5), "1. 5! does not equal 1");
		
		System.out.println("--------------------------------------------------------");
	}
	
	@Test
	public void testReverseString(){
		System.out.println();
		System.out.println();
		System.out.println("Test Case: testReverseString()");
		System.out.println("--------------------------------------------------------");
		/*
		 * Insert Assertions Below
		 */
		equals("stop", c.reverse("pots"), "1. stop equals pots in reverse");
		equals("cat", c.reverse("hat"), "2. cat equals hat in reverse");
		
		System.out.println("--------------------------------------------------------");
	}
	
	public void equals(Object x, Object y, String message){
		try{
			assertEquals(x, y);
			System.out.println();
			System.out.println(message + " -passed");
		}catch(AssertionError e){
			System.out.println();
			System.out.println(message + " -failed");
		}
	}
	
	public void notEquals(Object x, Object y, String message){
		try{
			assertNotEquals(x, y);
			System.out.println();
			System.out.println(message + " -passed");
		}catch(AssertionError e){
			System.out.println();
			System.out.println(message + " -failed");
		}
	}

}