package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "DevOps", "Azure");

	@Test
	void test() {
		boolean testTrue = todos.contains("Azure");
		boolean testFalse = todos.contains("GCP");
		
		assertEquals(true, testTrue);
		assertTrue(testTrue); //Instead of the above method, we can use this method for boolean check
		assertTrue(testTrue, "Error Occured"); //this method is same as above but can be used to throw an error message if the test case fails
		assertFalse(testFalse); //Like assertTrue, we have method for assertFalse too
		
//		assertNull()
//		assertNotNull();
		
		assertArrayEquals(new int[] {1,2}, new int[] {1,2});
		assertArrayEquals(new int[] {1,2}, new int[] {2,3}, "Error Occured in Array Equals");
		
		assertEquals(3, todos.size());
		assertEquals(3, todos.size(), "Error Occured"); //this method is same as above but can be used to throw an error message if the test case fails
		// fail("Not yet implemented");
	}

}
