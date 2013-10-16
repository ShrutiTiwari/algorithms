package com.aqua.puzzles.topcoder.security;

public class BinaryCodeFirstAttempt
{
	private int[] convert( char[] chars ) {
		int[] result = new int[chars.length];
		for( int i = 0; i < chars.length; i++ ) {
			result[i] = Character.digit( chars[i], 10 );
		}
		return result;
	}

	public String[] decode( String input ) {
		int[] digits = convert( input.toCharArray() );
		return new String[] { decodeString( digits, 0 ), decodeString( digits, 1 ) };
	}

	private String decodeString( int[] encodedDigits, int start ) {
		StringBuffer result = new StringBuffer();
		int length = encodedDigits.length;
		
		int[] decodedBinary = new int[length];
		decodedBinary[0] = start;
		result.append( start );
		
		int secondBinary = encodedDigits[0] - start;
		if( violationDetected( secondBinary ) ) {
			System.out.println("First trap!");
			return "NONE";
		}
		decodedBinary[1]=secondBinary;
		result.append( secondBinary );
		
		for( int i = 1; i < length-1; i++ ) {
			int nextBinary = encodedDigits[i] - decodedBinary[i]- decodedBinary[i-1];
			if( violationDetected( nextBinary ) ) {
				System.out.println("Second trap!");
				return "NONE";
			}
			decodedBinary[i+1]=nextBinary;
			result.append( nextBinary );
		}
		if(encodedDigits[length-1]!=(decodedBinary[length-1]+decodedBinary[length-2])) {
			System.out.println("Last trap! result="+result);
			return "NONE";
		}
		
		return result.toString();
	}

	private boolean violationDetected( int secondBinary ) {
		return secondBinary != 1 && secondBinary != 0;
	}

	private String[] encode( String input ) {
		int[] chars = convert( input.toCharArray() );
		return new String[] { encodeString( chars ) };
	}

	private String encodeString( int[] chars ) {
		StringBuffer result = new StringBuffer();
		int last = chars.length - 1;
		for( int i = 0; i < chars.length; i++ ) {
			int start = (i == 0) ? 0
					: chars[i - 1];
			int end = (i == last) ? start
					: chars[i + 1];
			result.append( start + chars[i] + end );
		}
		return result.toString();
	}
}
