package com.ex.probs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CodingChallengesTest {
	
	CodingChallenges c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after class");
	}

	@Before
	public void setUp() throws Exception {
		c = new CodingChallenges();
		System.out.println("before method");
	}

	@After
	public void tearDown() throws Exception {
		c = null;
		System.out.println("after method");
	}
	
	@Test
	public void testReverse() {
		String actual = "this is a test";
		String expected = c.reverseString(actual);
		System.out.println(expected);
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void testFactorial() {
		int expected = 120;
		int actual = c.factorial(5);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testFactorialZero() {
		assertNotEquals(1, c.factorial(5));
		System.out.println("has failed"); //does NOT run if above fail
	}

}
