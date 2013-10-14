package com.aqua.music.items;

import java.util.HashSet;

import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public class AscendDescendSequencePuzzles
{
    public AscendDescendSequencePuzzles() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    public void playThaat() {
        SymmetricalSet.THAAT_BILAWAL.playAscendAndDescend();
    }

    public void playMultipleThaats() {
        AudioFileListBuilder.BuilderForMultipleSymmetricalSets multipleThaatEnqueuer = new AudioFileListBuilder.BuilderForMultipleSymmetricalSets( new SymmetricalSet[] { SymmetricalSet.THAAT_BILAWAL, SymmetricalSet.THAAT_ASAVARI } );
        System.out.println(multipleThaatEnqueuer.prettyPrintText());
        AudioLibrary.audioPlayer().playList(multipleThaatEnqueuer.collectedAudioFiles);
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

    private void playAscendAndDescend( int count, FrequencySet... raags ) {
        SymmetricalSet.THAAT_BILAWAL.playAscendAndDescend();
        System.out.println( "\n Played [BILAWAL]" );
        HashSet<FrequencySet> hasheddata = randomize( raags );
        for( FrequencySet each : hasheddata ) {
            for( int i = 0; i < count; i++ ) {
                each.playAscendAndDescend();
                System.out.println( "\nPlayed [" + each.name() + "] ." + i );
                System.out.println( "\n" );
            }
        }
    }

    private HashSet<FrequencySet> randomize( FrequencySet... raag ) {
        HashSet<FrequencySet> hasheddata = new HashSet<FrequencySet>();
        for( FrequencySet each : raag ) {
            hasheddata.add( each );
        }
        return hasheddata;
    }
}
