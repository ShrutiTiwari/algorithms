package com.octopus.large.numbers.operation;

import java.math.BigInteger;

import com.octopus.large.numbers.operation.LargeNumberMultiplication;

import junit.framework.TestCase;

public class LargeNumberMultiplicationTest extends TestCase
{
	public void _testFourDigitMultiplaction(){
		LargeNumberMultiplication calc= new LargeNumberMultiplication(4536, 8967);
		assertEquals( calc.regularMultiply(), calc.multiplyByKaratsubaAlgorithm() );
	}
	
	public void testSixDigitMultiplaction(){
		LargeNumberMultiplication calc= new LargeNumberMultiplication(453621, 896745);
		String value1 = "453621";
		String value2 = "896745";
		System.out.println(bigInt( value1 ).multiply( bigInt( value2 )).longValue());
		System.out.println(calc.recursiveKaratsubaAlgorithm(value1, value2));
	}

	private BigInteger bigInt( String number1 ) {
		return new BigInteger(number1,10);
	}

}
