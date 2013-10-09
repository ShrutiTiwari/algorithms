package com.aqua.numbers.sequence;

import java.util.Iterator;
import java.util.List;

/**
 * Write an Iterator (following the standard way of doing them in the language of choice) that for a given range defined
 * by two integers (x and y) at each call returns the next integer which is composed of different digits and which is
 * within the range. The range is inclusive of both x and y. For instance, if the iterator is initialized with x = 95
 * and y = 120, it must produce the sequence of values 95, 96, 97, 98, 102, 103, 104, 105, 106, 107, 108, 109, and 120.
 * 
 * @author shruti.tiwari
 * 
 */
public class DistinctDigitNumberGenerator implements Iterable<Long>
{
	private int currentSize;
	private List<Long> numbers;

	public static DistinctDigitNumberGenerator forRange( long min, long max ) {
		List<Long> generateNumbersForRange = DistinctDigitNumbersInRange.within( min, max );
		return new DistinctDigitNumberGenerator(
				generateNumbersForRange );
	}

	private DistinctDigitNumberGenerator( List<Long> numbers ) {
		this.numbers = numbers;
		this.currentSize = numbers.size();
	}

	@Override
	public Iterator<Long> iterator() {
		Iterator<Long> it = new Iterator<Long>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < currentSize && numbers.get( currentIndex ) != null;
			}

			@Override
			public Long next() {
				return numbers.get( currentIndex++ );
			}

			@Override
			public void remove() {
			}
		};
		return it;
	}

}