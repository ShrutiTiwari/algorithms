package com.octopus.numbers.sorting;

import com.octopus.numbers.sorting.MergeSortArray;

import junit.framework.TestCase;

public class MergeSortArrayTest extends TestCase {

	public static void testBaseConditionWithOneNumber() {
		int[] numberContainer = new int[] { 2 };
		executeAndAssertResult(numberContainer, numberContainer);
	}

	public static void testBaseConditionWithMoreThanTwoNumbers() {
		int[] numberContainer = new int[] { 3, 2, 1 };
		int[] expectedResult = new int[] { 1, 2, 3 };
		executeAndAssertResult(numberContainer, expectedResult);
	}
	
	public static void testBaseConditionWithManyRepeatitveNumbers() {
		int[] numberContainer = new int[] { 3, 2, 1, 33, 5, 6, 1  };
		int[] expectedResult = new int[] { 1,1,2,3,5,6,33 };
		executeAndAssertResult(numberContainer, expectedResult);
	}
	

	public static void testBaseConditionWithTwoNumbersInReverseOrder() {
		int[] numberContainer = new int[] { 3, 2 };
		int[] expectedResult = new int[] { 2, 3 };
		executeAndAssertResult(numberContainer, expectedResult);
	}

	public static void testBaseConditionWithTwoNumbersInCorrectOrder() {
		int[] numberContainer = new int[] { 2, 3 };
		int[] expectedResult = new int[] { 2, 3 };
		executeAndAssertResult(numberContainer, expectedResult);
	}

	private static void executeAndAssertResult(int[] numberContainer,
			int[] expectedResult) {
		int[] actualResult = new MergeSortArray().sort(numberContainer);
		printResult(actualResult);
		assertEquals(expectedResult.length, actualResult.length);
		assertArrayAreSame(expectedResult, actualResult);
	}

	private static void assertArrayAreSame(int[] actualResult,
			int[] expectedResult) {
		for (int index = 0; index < actualResult.length; index++) {
			assertEquals(actualResult[index], expectedResult[index]);
		}
	}

	private static void printResult(int[] actualResult) {
		StringBuffer buf= new StringBuffer();
		for(int each: actualResult){
				buf.append(each + ",");
		}
		System.out.println(buf.toString());
	}
}
