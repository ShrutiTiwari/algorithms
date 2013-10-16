package com.aqua.large.numbers.operation;

/**
 * Problem Statement: Write a Function that takes two strings as input (x and y) representing two integer values and
 * returns a string representing the sum of x and y. For instance, given the inputs x = �12345678901234567890� and y =
 * �23232323232323232324�, the function must return �35578002133557800214�. The function must be able to operate on and
 * compute the result of string inputs of arbitrary length!
 * 
 * 
 * TODO: Increase robustness for sign handling.
 * 
 * @author shruti.tiwari
 * 
 */
class LargeInteger
{
	private static final int CHOPPING_LENGTH = 9;
	private static final int MAX_NUMBER_IN_SLOT = 999999999;
	final int[] choppedParts;
	final int choppedPartsCount;
	private final String stringValue;

	private LargeInteger( final String stringValue, final int[] choppedParts ) {
		this.choppedParts = choppedParts;
		this.choppedPartsCount = choppedParts.length;
		this.stringValue = stringValue;
	}

	@Override
	public String toString() {
		return stringValue;
	}

	String add( LargeInteger increment ) {
		return new Adder().add( this, increment );
	}

	static class Adder
	{
		private int[] choppedSum;
		private int carryOver;
		private int maxCommonLength;

		private String add( LargeInteger value1,LargeInteger value2 ) {
			addCommonParts( value1, value2 );
			if( carryOver != 0 ) {
				for( int i = maxCommonLength; i < choppedSum.length && carryOver != 0; i++ ) {
					choppedSum[i] = choppedSum[i] + carryOver;
					carryOver = (choppedSum[i] > MAX_NUMBER_IN_SLOT) ? 1
							: 0;
				}
			}
			return Builder.createString(choppedSum);
		}
		
		private void addCommonParts( LargeInteger value1,LargeInteger value2 ) {
			this.choppedSum = greaterOfTwo(value1, value2 );
			this.carryOver = 0;
			this.maxCommonLength = maxCommonLengthOfTwo(value1, value2 );
			for( int i = 0; i < maxCommonLength; i++ ) {
				int sum = value1.choppedParts[i] + value2.choppedParts[i] + carryOver;
				String sumstr = Integer.toString( sum, 10 );
				choppedSum[i] = (sum > MAX_NUMBER_IN_SLOT) ? Integer.parseInt( sumstr.substring( 1 ) )
						: sum;
				// FIXME: find better logic to improve performance
				carryOver = (sum > MAX_NUMBER_IN_SLOT) ? sumstr.charAt( 0 )
						: 0;
			}
		}

		private static int[] greaterOfTwo( LargeInteger value1, LargeInteger value2 ) {
			return (value1.choppedPartsCount <= value2.choppedPartsCount) ? value1.choppedParts
					: value2.choppedParts;
		}

		private static int maxCommonLengthOfTwo( LargeInteger value1, LargeInteger value2  ) {
			return (value1.choppedPartsCount <= value2.choppedPartsCount) ? value1.choppedPartsCount
					: value2.choppedPartsCount;
		}


	}

	static class Builder
	{
		static LargeInteger buildFrom( int[] choppedParts ) {
			return new LargeInteger( createString(choppedParts), choppedParts);
		}
		static LargeInteger buildFrom( String stringRepresentation ) {
			return new LargeInteger( stringRepresentation, createParts( stringRepresentation ) );
		}

		private static int[] createParts( String stringRepresentation ) {
			int size = stringRepresentation.length();
			int choppedPartsCount = size / CHOPPING_LENGTH + (size % CHOPPING_LENGTH != 0 ? 1
					: 0);
			int[] choppedParts = new int[choppedPartsCount];
			for( int i = 1; i <= choppedPartsCount; i++ ) {
				int beginIndex = size - i * CHOPPING_LENGTH;
				choppedParts[i - 1] = Integer.parseInt( stringRepresentation.substring( beginIndex < 0 ? 0
						: beginIndex, beginIndex + CHOPPING_LENGTH ) );
			}
			return choppedParts;
		};

		private static String createString( int[] choppedParts ) {
			String stringValue = "";
			for( int each : choppedParts ) {
				stringValue = each + stringValue;
			}
			return stringValue;
		}
	}
}