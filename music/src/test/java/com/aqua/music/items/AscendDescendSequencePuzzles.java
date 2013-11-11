package com.aqua.music.items;

import java.util.HashSet;

import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class AscendDescendSequencePuzzles
{
    public void playThaat() {
    	PlayableItem.Factory.forPlayerAndSet(FilesystemPlayableItem.blocking,SymmetricalSet.THAAT_BILAWAL).play();
    }

    public void playMultipleThaats() {
        FrequencyListBuilder.BuilderForMultipleSymmetricalSets multipleThaatEnqueuer = new FrequencyListBuilder.BuilderForMultipleSymmetricalSets( new SymmetricalSet[] { SymmetricalSet.THAAT_BILAWAL, SymmetricalSet.THAAT_ASAVARI } );
        System.out.println(multipleThaatEnqueuer.prettyPrintText());
        FilesystemPlayableItem.blocking.play(multipleThaatEnqueuer.collectedFrequencies);
    }

    public void playAllThats() {
        int repeatCount = 2;
        System.out.println( "Repeating each item[" + repeatCount + "] \n" );
        for( int i = 0; i < 3; i++ ) {
            // puzzleBuilder.playArohiAvrohi(repeatCount, SecondYearRaag.values());
            playAscendAndDescend( repeatCount, FrequencySet.SymmetricalSet.values() );
            System.out.println( "round[" + i + "] done \n" );
        }
    }

    private void playAscendAndDescend( int count, SymmetricalSet... raags ) {
    	PlayableItem.Factory.forPlayerAndSet(FilesystemPlayableItem.blocking,SymmetricalSet.THAAT_BILAWAL).play();
        System.out.println( "\n Played [BILAWAL]" );
        HashSet<SymmetricalSet> hasheddata = randomize( raags );
        for( SymmetricalSet each : hasheddata ) {
            
            for( int i = 0; i < count; i++ ) {
            	PlayableItem.Factory.forPlayerAndSet(FilesystemPlayableItem.blocking,each).play();
                System.out.println( "\nPlayed [" + each.name() + "] ." + i );
                System.out.println( "\n" );
            }
        }
    }

    private HashSet<SymmetricalSet> randomize( SymmetricalSet... raag ) {
        HashSet<SymmetricalSet> hasheddata = new HashSet<SymmetricalSet>();
        for( SymmetricalSet each : raag ) {
            hasheddata.add( each );
        }
        return hasheddata;
    }
}
