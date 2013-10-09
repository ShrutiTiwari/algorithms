package com.octopus.large.numbers.operation;

import java.math.BigInteger;

public class LargeNumberMultiplication
{
	private final String number1;
	private final String number2;
	private final int regularResult;

	LargeNumberMultiplication( int number1, int number2 ) {
		this.number1 = Integer.toString( number1 );
		this.number2 = Integer.toString( number2 );
		regularResult = number1 * number2;
	}

	int regularMultiply() {
		return regularResult;
	}

	long recursiveKaratsubaAlgorithm( String number1, String number2 ) {
		int midPoint = number1.length() / 2;
		int a1 = Integer.parseInt( number1.substring( 0, midPoint ) );
		int a2 = Integer.parseInt( number1.substring( midPoint ) );

		int b1 = Integer.parseInt( number2.substring( 0, midPoint ) );
		int b2 = Integer.parseInt( number2.substring( midPoint ) );

		if( midPoint < 2 ) {
			int leftDigits = a1 * b1;
			int rightDigits = a2 * b2;
			int middleDigits = (a1 + a2) * (b1 + b2) - (leftDigits + rightDigits);
			return baseCase( midPoint, leftDigits, middleDigits, rightDigits );
		} else {
			long leftDigits = recursiveKaratsubaAlgorithm( a1 + "", b1 + "" );
			long rightDigits = recursiveKaratsubaAlgorithm( a2 + "", b2 + "" );
			long middleDigits = recursiveKaratsubaAlgorithm( (a1 + a2) + "", (b1 + b2) + "" )
					- (leftDigits + rightDigits);
			return baseCase( midPoint, leftDigits, middleDigits, rightDigits );
		}
	}

	// do it by recursion for larger range.(numbers greater than 4 digit)-- does it require even digits necessarily?
	// assume equal number of digits
	long multiplyByKaratsubaAlgorithm() {
		int midPoint = number1.length() / 2;
		int a1 = Integer.parseInt( number1.substring( 0, midPoint ) );
		int a2 = Integer.parseInt( number1.substring( midPoint ) );

		int b1 = Integer.parseInt( number2.substring( 0, midPoint ) );
		int b2 = Integer.parseInt( number2.substring( midPoint ) );

		int leftDigits = a1 * b1;
		int rightDigits = a2 * b2;
		int middleDigits = (a1 + a2) * (b1 + b2) - (leftDigits + rightDigits);
		return baseCase( midPoint, leftDigits, middleDigits, rightDigits );
	}

	private static long baseCase( long midPoint, long leftDigits, long rightDigits, long middleDigits ) {
		return sum( leftDigits, rightDigits, middleDigits, padZero( midPoint ) );
	}

	private static long sum( long leftDigits, long rightDigits, long middleDigits, String middlePadZero ) {
		return new BigInteger(  leftDigits + middlePadZero + middlePadZero ).add( new BigInteger(middleDigits + middlePadZero ) )
				.add( new BigInteger(rightDigits+"")).longValue();
	}

	private static String padZero( long i ) {
		StringBuffer vuf = new StringBuffer();
		for( long x = 0; x < i; x++ ) {
			vuf.append( 0 );
		}
		return vuf.toString();
	}
}
