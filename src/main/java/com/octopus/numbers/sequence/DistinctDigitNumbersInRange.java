package com.octopus.numbers.sequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DistinctDigitNumbersInRange
{
	private final long min;
	private final long max;

	private DistinctDigitNumbersInRange( long min, long max ) {
		this.min = min;
		this.max = max;
	}
	
	static List<Long> within(long min, long max) {
		return new DistinctDigitNumbersInRange(min,max).generateNumbers();
	}
	
	/**
	 * NOTE: You can improve performance here.
	 * @param numberString
	 * @return
	 */
	private static int distinctDigitCount( String numberString ) {
		Set<Character> numberDigit = new HashSet<Character>();
		char[] charArray = numberString.toCharArray();
		for( char each : charArray ) {
			numberDigit.add( each );
		}
		return numberDigit.size();
	}

	private static boolean doesNumberContainDistinctDigit( String numberString ) {
		int length = numberString.length();
		return length > 10 ? false
				: length == 1 || distinctDigitCount( numberString ) == length;
	}

	private List<Long> generateNumbers() {
		List<Long> numbers = new ArrayList<Long>();
		for( long candidate = min; candidate < max + 1; candidate++ ) {
			if( doesNumberContainDistinctDigit( Long.toString( candidate ) ) ) {
				numbers.add( candidate );
			}
		}
		return numbers;
	}
}
