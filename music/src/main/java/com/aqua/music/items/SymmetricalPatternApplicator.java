package com.aqua.music.items;

import java.util.ArrayList;
import java.util.List;

public class SymmetricalPatternApplicator<T> implements PatternApplicator<T>
{
    private final int[] pattern;
    private List<T> ascendSequence;
    private List<T> descendSequence;
    

    public SymmetricalPatternApplicator( int[] pattern ) {
        this.pattern = pattern;
    }

    public void generateAscendAndDescendSequences( T[] symmetricInput ) {
        List<T> inputList = new ArrayList<T>();
        for( T each : symmetricInput ) {
            inputList.add( each );
        }
        this.ascendSequence = sequenceForGiven( inputList );
        this.descendSequence = sequenceForGiven( reverse( symmetricInput ) );
    }

    public List<T> allNotes() {
        List<T> allNotes = new ArrayList<T>();
        allNotes.addAll( ascendSequence );
        allNotes.addAll( descendSequence );
        return allNotes;
    }

    public String prettyPrintTextForAscDesc() {
        return groupItemsForPrettyPrint( ascendSequence ) + SEP + groupItemsForPrettyPrint( descendSequence );
    }

    public List<T> ascendSequence() {
        return ascendSequence;
    }

    public List<T> descendSequence() {
        return descendSequence;
    }

    private List<T> sequenceForGiven( List<T> input ) {
        List<T> result = new ArrayList<T>();
        for( int index = 0; index < input.size(); index++ ) {
            List<T> subResult = patternAt( input, index );
            if( subResult == null ) {
                break;
            }
            for( T each1 : subResult ) {
                result.add( each1 );
            }
        }
        return result;
    }

    private List<T> patternAt( List<T> input, int index ) {
        List<T> result = new ArrayList<T>();
        try {
            int k = 0;
            for( int i : pattern ) {
                T noteForPattern = input.get( index + (i - 1) );
                result.add( noteForPattern );
            }
            return result;
        } catch( Exception ex ) {
            return null;
        }
    }

    private String groupItemsForPrettyPrint( List<T> noteSequence ) {
        int numberItemsToBeGrouped = pattern.length;
        return insertComma( noteSequence, numberItemsToBeGrouped ).toString();
    }

    private StringBuffer insertComma( List<T> itemSequence, int numberItemsToBeGrouped ) {
        StringBuffer buffer = new StringBuffer();
        int processedItems=0;
        int i = 1;
        for( T eachItem : itemSequence ) {
            if( i == numberItemsToBeGrouped) {
                i = 1;
                buffer.append( eachItem + (processedItems!=itemSequence.size()-1?", ":"" ));
            } else {
                buffer.append( eachItem );
                i++;
            }
            processedItems++;
        }
        return buffer;
    }

    private List<T> reverse( T[] inputData ) {
        int dataLength = inputData.length;
        List<T> reverseData = new ArrayList<T>();
        for( int i = 0; i < dataLength; i++ ) {
            reverseData.add( inputData[(dataLength - 1) - i] );
        }
        return reverseData;
    }
}
