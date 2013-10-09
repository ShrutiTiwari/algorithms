package com.octopus.text.search.pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Write a Console Application that loads a text file, takes a pair of words as input (x and y), and returns the number
 * of times the two words appear adjacent (x after y or y after x) in the text file and the list of positions at which
 * they appear. For instance, given the input text file contains �a b c d b a�, for the input pair <�a�, �b�> it will
 * return 2 as the number of times the two words appear adjacent in the text and <0, 8> as the list of positions at
 * which the pairs appear. It can be safely assumed that the input file is small enough to fit in memory of the machine
 * hosting the application.
 * 
 * @author shruti.tiwari TODO: 1. Read searchSpace from file.
 */
public class MicroIndex
{
	private static final char WHITESPACE = ' ';
	private final AllowedWords allowedWords;
	private final List<KeywordOccurance> successfullyMatched = new ArrayList<KeywordOccurance>();
	private final ProgressiveItems progressiveItems = new ProgressiveItems();
	private final boolean ignoreExtraWhiteSpace;

	public MicroIndex( String word1, String word2 ) {
		this( word1, word2, false );
	}

	MicroIndex( String word1, String word2, boolean ignoreExtraWhiteSpace ) {
		this.allowedWords = new AllowedWords( word1.trim(), word2.trim() );
		this.ignoreExtraWhiteSpace = ignoreExtraWhiteSpace;
	}

	public static MicroIndex with( String word1, String word2 ) {
		return new MicroIndex( word1, word2 );
	}

	public static MicroIndex with( String word1, String word2, boolean ignoreWhiteSpace ) {
		return new MicroIndex( word1, word2, ignoreWhiteSpace );
	}

	List<KeywordOccurance> searchIn( String searchSpace ) {
		long locationInFile = 0;
		char[] allCharacters = searchSpace.toCharArray();
		char previousChar = WHITESPACE;
		for( char currentCharacter : allCharacters ) {
			if( !continuousWhiteSpaces( previousChar, currentCharacter ) ) {
				progressiveItems.updateSuccessfullyMatchedAndPurgeFinishedOrInvalidItems( currentCharacter,
						successfullyMatched );

				if( beginningOfANewWord( previousChar, currentCharacter ) ) {
					List<ValidKeyword> possibleValidKeywords = allowedWords
							.validKeywordsStartingFromGivenCharacter( currentCharacter );
					for( ValidKeyword eachPossibleKeyword : possibleValidKeywords ) {
						progressiveItems.add( new KeywordOccurance( locationInFile, eachPossibleKeyword ) );
					}
				}
			}
			locationInFile++;
			previousChar = currentCharacter;
		}
		// if all is over, merge finished matches
		progressiveItems.updateSuccessfullyMatched( locationInFile, successfullyMatched );

		return successfullyMatched;
	}

	private boolean beginningOfANewWord( char previousChar, char currentCharacter ) {
		return previousChar == WHITESPACE && currentCharacter != WHITESPACE;
	}

	private boolean continuousWhiteSpaces( char previousChar, char currentCharacter ) {
		boolean continuouseWhiteSpace = currentCharacter == WHITESPACE && currentCharacter == previousChar;
		return ignoreExtraWhiteSpace && continuouseWhiteSpace;
	}

	static class AllowedWords
	{
		private final ValidKeyword keyword1;
		private final ValidKeyword keyword2;
		private final Set<Character> validStartCharacters = new HashSet<Character>();
		static List<ValidKeyword> EMPTY = new ArrayList<ValidKeyword>();

		private AllowedWords( String word1, String word2 ) {
			this.keyword1 = new ValidKeyword( word1, word2 );
			this.keyword2 = new ValidKeyword( word2, word1 );
			validStartCharacters.add( keyword1.startChar );
			validStartCharacters.add( keyword2.startChar );
		}

		List<ValidKeyword> validKeywordsStartingFromGivenCharacter( char c ) {
			if( !validStartCharacters.contains( c ) )
				return EMPTY;
			List<ValidKeyword> result = new ArrayList<ValidKeyword>();
			if( keyword1.startChar == c ) {
				result.add( keyword1 );
			}
			if( keyword2.startChar == c ) {
				result.add( keyword2 );
			}
			return result;
		}
	}
}