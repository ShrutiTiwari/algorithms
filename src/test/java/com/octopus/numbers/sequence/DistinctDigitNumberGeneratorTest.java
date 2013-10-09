package com.aqua.numbers.sequence;

import junit.framework.TestCase;

import com.aqua.numbers.sequence.DistinctDigitNumberGenerator;
import com.aqua.numbers.sequence.DistinctDigitNumbersInRange;

public class DistinctDigitNumberGeneratorTest extends TestCase
{
	public void testIterationonDistingDigitNumbersInRange(){
		int rangeMin=95;
		int rangeMax=120;
		DistinctDigitNumberGenerator instance= DistinctDigitNumberGenerator.forRange( rangeMin,rangeMax);
		
		String actualOutput="";
		for(Long each:instance){
			actualOutput=actualOutput+each+",";
		}
		
		System.out.println(actualOutput);
		assertEquals( "95,96,97,98,102,103,104,105,106,107,108,109,120,", actualOutput );
	}
	
	/**
	 * This test focuses on inner implementation details of {@link DistinctDigitNumbersInRange}.
	 */
	public void testDistinctDigitNumbersInRange(){
		int rangeMin=95;
		int rangeMax=120;
		String actual = DistinctDigitNumbersInRange.within( rangeMin,rangeMax).toString();
		System.out.println(actual);
		assertEquals( "[95, 96, 97, 98, 102, 103, 104, 105, 106, 107, 108, 109, 120]", actual );
	}
}


