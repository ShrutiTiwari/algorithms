package com.octopus.text.search.pair;

import java.util.List;

import com.octopus.text.search.pair.KeywordOccurance;
import com.octopus.text.search.pair.MicroIndex;

import junit.framework.TestCase;

/**
 * Write a Console Application that loads a text file, takes a pair of words as input (x and y), and returns the number
 * of times the two words appear adjacent (x after y or y after x) in the text file and the list of positions at which
 * they appear. For instance, given the input text file contains �a b c d b a�, for the input pair <�a�, �b�> it will
 * return 2 as the number of times the two words appear adjacent in the text and <0, 8> as the list of positions at
 * which the pairs appear. It can be safely assumed that the input file is small enough to fit in memory of the machine
 * hosting the application.
 * 
 * @author shruti.tiwari
 * 
 */
public class MicroIndexTest extends TestCase
{
	public void testSearchWithFunExample() {
		List<KeywordOccurance> searchResult = MicroIndex.with( "shruti  ", "sweet  ",true).searchIn( "sweet    shruti wrote this program. shruti sweet isnt valid sentence. shruti is sweet." );
		print(searchResult);
		assertEquals(2, searchResult.size());
	}

	public void testSearchWithQuotedExample() {
		List<KeywordOccurance> searchResult = MicroIndex.with( "a", "b").searchIn( "a b c d b a" );
		print(searchResult);
		assertEquals(2, searchResult.size());
	}

	/**
	 * "bbbbbb a" should not be counted as valid combination like "b a".
	 */
	public void testSearchWithQuotedExampleButMadeConfusing() {
		List<KeywordOccurance> searchResult = MicroIndex.with( "a", "b").searchIn( "a b c d bbbbbbb a" );
		print(searchResult);
		assertEquals(1, searchResult.size());
	}

	public void testSearchWithQuotedExampleCorruptedWithWhiteSpace() {
		List<KeywordOccurance> searchResult = MicroIndex.with( "a", "b").searchIn( "a b c d b    a" );
		print(searchResult);
		assertEquals(1, searchResult.size());
	}

	public void testSearchWithQuotedExampleCorruptedWithWhiteSpaceToBeIgnored() {
		List<KeywordOccurance> searchResult = MicroIndex.with( "a", "b",true).searchIn( "a b c d b    a" );
		print(searchResult);
		assertEquals(2, searchResult.size());
	}



	private void print(List<KeywordOccurance> searchResult) {
		System.out.println( "Matches Found [" + searchResult.size() + "]" );
		for( KeywordOccurance each : searchResult ) {
			System.out.println( each.locationInFile() );
		}
	}
}
