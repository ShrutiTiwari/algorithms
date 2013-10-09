package com.octopus.ms.problms;

import java.util.Collection;
import java.util.HashSet;

public class Probs
{
    private Collection<String> dictionary = new HashSet<String>();

    Probs() {
        dictionary.add( "a" );
        dictionary.add( "am" );
        dictionary.add( "kind" );
        dictionary.add( "amazon" );
        dictionary.add( "kindle" );
    }

    String[] findValidPartsOfWord( String input ) {
        
        StringBuffer tempFirstWord = new StringBuffer();
        
        for( int index = 0; index < input.length(); index++ ) {
            Character c = input.charAt( index );
            tempFirstWord.append( c );
            if( dictionary.contains( tempFirstWord.toString() ) ) {
                String tempSecondWord = input.subSequence( index + 1, input.length() ).toString();
                if( dictionary.contains( tempSecondWord ) ) {
                    return new String[] { tempFirstWord.toString(), tempSecondWord };
                } else {
                    System.out.println( "found first word[" + tempFirstWord+ "] but invalid second word[" + tempSecondWord + "]");
                }
            }
        }
        return new String[] { null, null };
    }

    public static void main( String[] args ) {
        StringBuffer words = new StringBuffer();
        for( String each : new Probs().findValidPartsOfWord( "amazonkindle" ) ) {
            words.append( "[" + each + "]" );
        }
        System.out.println( words );
    }
}
