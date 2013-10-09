package com.octopus.puzzles.topcoder.security;

/**
 * 
 * Problem Statement:
 * 
 * Create logic to decoded an encrpted code with below logic.
 * Encoded message contains sum of consecutive and main digit. For edges, the adjacent values are assumed zero.
 * Eg. 
 * Given string: 011100011 (P)
 * Encoded Message:  123210122 (Q)
 * 
 * Decoding the encrypted message could be done by assuming that first digit of source message was either 0 or 1.
 * eg. given 123210122
 * Assuming P(0)=0 => 
 * P(1)+P(0)=Q(0) 	==>	  P(1)= Q(0)-P(0)= 1-0=1;
 * P(2)+P(1)+P(0)=Q(1) 	==>	  P(2)= Q(1)-P(1)-P(0)= 2-1-0=1;
 * Repeating the process will yield all digits for P.
 * 
 * the last digit of Q is used to verify if reconstructed (P) is correct.
 * 
 *  
 *  Doing above exercise will yield 011100011 (assuming firstDigit=0) and NONE(assuming firstDigit=1)
 * @author shruti.tiwari
 *
 */
public class BinaryCodeEfficient
{
	private boolean nonDigitInput = false;
	private String[] finalResult = new String[2];

	public static void main( String[] args ) {
		BinaryCodeEfficient binaryCode = new BinaryCodeEfficient();
		String[] result = binaryCode.decode( "123210122" );
		System.out.println( "results::::" );
		for( String each : result ) {
			System.out.println( each );
		}
	}

	private int[] convert( char[] chars ) {
		int[] result = new int[chars.length];
		for( int i = 0; i < chars.length; i++ ) {
			result[i] = Character.digit( chars[i], 10 );
		}
		return result;
	}

	public String[] decode( String input ) {
		for( int assumedFirstBit = 0; assumedFirstBit < finalResult.length && !nonDigitInput; assumedFirstBit++ ) {
			StringBuffer result = new StringBuffer();
			int[] decodedBinary = new int[2];
			for( int i = 0; i < input.length(); i++ ) {
				nonDigitInput = !Character.isDigit( input.charAt( i ) );
				if( nonDigitInput ) {
					finalResult[assumedFirstBit] = "NONE";
					break;
				}
				if( i == 0 ) {
					result.append( assumedFirstBit );
					decodedBinary[0] = assumedFirstBit;
				} else {
					int lastDigit = Character.digit( input.charAt( i - 1 ), 10 );
					if( i == 1 ) {
						int secondBinary = lastDigit - assumedFirstBit;
						if( digitIsNotBinary( secondBinary ) ) {
							System.out.println( "First trap! digit Is Not Binary" );
							finalResult[assumedFirstBit] = "NONE";
							break;
						}
						result.append( secondBinary );
						decodedBinary[1] = secondBinary;
					} else if( i <= input.length() - 1 ) {
						int nextBinary = lastDigit - decodedBinary[0] - decodedBinary[1];
						if( digitIsNotBinary( nextBinary ) ) {
							System.out.println( "Second trap! violatedValue[" + nextBinary + "], result ["
									+ result.toString() + "] lastDigit[" + lastDigit + "]" );
							finalResult[assumedFirstBit] = "NONE";
							break;
						}
						result.append( nextBinary );
						decodedBinary[0] = decodedBinary[1];
						decodedBinary[1] = nextBinary;
					} 
					if( i == input.length() - 1 ) {
						int currentDigit = Character.digit( input.charAt( i ), 10 );
						if( currentDigit != (decodedBinary[0] + decodedBinary[1]) ) {
							System.out.println( "Last trap! result [" + result + "] currentDigit[" + currentDigit
									+"]" );
							finalResult[assumedFirstBit] = "NONE";
						} else {
							finalResult[assumedFirstBit] = result.toString();
						}
					}
				}
			}
		}
		return finalResult;
	}

	private boolean digitIsNotBinary( int digit ) {
		return digit != 1 && digit != 0;
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
