package com.aqua.text.search.pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ProgressiveItems
{
	List<KeywordOccurance> inProgressKeywords = new ArrayList<KeywordOccurance>();
	private int addcount = 0;

	void add( KeywordOccurance item ) {
		System.out.println( "adding new item[" + item + "] - " + (++addcount) );
		inProgressKeywords.add( item );
	}

	void updateSuccessfullyMatched( long locationInFile, List<KeywordOccurance> successfullyMatched ) {
		for( KeywordOccurance candidate : inProgressKeywords ) {
			if( candidate.matched() ) {
				successfullyMatched.add( candidate );
				System.out.println( "yaay!! matched item[" + candidate + "]" );
			}
		}
	}

	void updateSuccessfullyMatchedAndPurgeFinishedOrInvalidItems( char currentCharacter,
			List<KeywordOccurance> successfullyMatched ) {
		if( inProgressKeywords.isEmpty() )
			return;
		Set<KeywordOccurance> droppedItems = createDroppedItemsList( currentCharacter, successfullyMatched );
		inProgressKeywords.removeAll( droppedItems );
	}

	private Set<KeywordOccurance> createDroppedItemsList( char currentCharacter,
			List<KeywordOccurance> successfullyMatched ) {
		Set<KeywordOccurance> droppedItems = new HashSet<KeywordOccurance>();
		for( KeywordOccurance currentCandidate : inProgressKeywords ) {
			currentCandidate.update( currentCharacter );
			if( currentCandidate.matched() ) {
				successfullyMatched.add( currentCandidate );
				updateDroppedItem( droppedItems, currentCandidate );
				System.out.println( "yaay!! matched item[" + currentCandidate + "]" );
			} else if( currentCandidate.invalidated() ) {
				updateDroppedItem( droppedItems, currentCandidate );
				System.out.println( "removing item[" + currentCandidate + "]" );
			}
		}
		return droppedItems;
	}

	private void updateDroppedItem( Set<KeywordOccurance> droppedItems, KeywordOccurance currentCandidate ) {
		droppedItems.add( currentCandidate );
	}

	public boolean isEmpty() {
		return inProgressKeywords.isEmpty();
	}
}