package com.shruti.large.number.operation;

import java.math.BigInteger;

import com.shruti.large.number.operation.LargeInteger;

import junit.framework.TestCase;


/**
 * This is main test for adding {@link LargeInteger}.
 * @author shruti.tiwari
 *
 */
public class LargeIntegerSumTest extends TestCase
{
	public void testAddingIsCorrectForEqualDigitInput1() {
		String parameter1 = "12345678901234567890";
		String parameter2 = "23232323232323232324";
		String expected = "35578002133557800214";
		String output = LargeInteger.Builder.buildFrom( parameter1 ).add( LargeInteger.Builder.buildFrom( parameter2 ) );
		assertEquals( expected, output); 
	}
	
	
	public void testAddingIsCorrectForEqualDigitInput() {
		String parameter1 = "23232323232323232324";
		String parameter2 = "93232323232323232324";
		String expected = expected( parameter1, parameter2 );
		String output = LargeInteger.Builder.buildFrom( parameter1 ).add( LargeInteger.Builder.buildFrom( parameter2 ) );
		assertEquals( expected, output); 
	}

	private String expected( String parameter1, String parameter2 ) {
		BigInteger n1 = new BigInteger(parameter1);
		BigInteger n2 = new BigInteger(parameter2);
		BigInteger expected = n1.add( n2 );
		return expected.toString();
	}
	
	
	/**
	 * This test focuses on inner implementation details of {@link LargeInteger}.
	 */
	public void testChoppingIsCorrectForTwentyDigitInput() {
		// String stringRepresentation="23232323232323232324";
		String string1 = "323232324";
		String string2 = "232323232";
		String string3 = "23";
		String stringRepresentation = string3 + string2 + string1;
		int[] choppedString = LargeInteger.Builder.buildFrom( stringRepresentation ).choppedParts;
		assertEquals( 3, choppedString.length );
		assertEquals( choppedString[0], Integer.parseInt( string1 ) );
		assertEquals( choppedString[1], Integer.parseInt( string2 ) );
		assertEquals( choppedString[2], Integer.parseInt( string3 ) );
	}

	/**
	 * Just checking the integer max limit.
	 */
	public void testIntegerParseIntFails() {
		boolean failed = false;
		try {
			int i = Integer.parseInt( "23232323232323232324" );
			System.err.print( "this should have failed! i[" + i + "]" );
		} catch( Exception e ) {
			//e.printStackTrace();
			failed = true;
		}

		assertTrue( failed );
	}
}
