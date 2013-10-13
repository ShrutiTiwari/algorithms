package com.aqua.music.play;

import java.util.HashSet;

import com.aqua.music.model.PredefinedFrequencySet;
import com.aqua.music.model.PredefinedFrequencySet.Thaat;

public class AscendDescendSequencePuzzles
{
    public AscendDescendSequencePuzzles() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    public void playThaat() {
        Thaat.BILAWAL.playAscendAndDescend();
    }

    public void playMultipleThaats() {
        AudioFileListMaker.MultipleThaatListMaker multipleThaatEnqueuer = new AudioFileListMaker.MultipleThaatListMaker( new Thaat[] { Thaat.BILAWAL, Thaat.ASAVARI } );
        System.out.println(multipleThaatEnqueuer.printableAudios());
        AudioLibrary.audioPlayer().playList(multipleThaatEnqueuer.collectedAudioFiles);
    }

    public void playAllThats() {
        int repeatCount = 2;
        System.out.println( "Repeating each item[" + repeatCount + "] \n" );
        for( int i = 0; i < 3; i++ ) {
            // puzzleBuilder.playArohiAvrohi(repeatCount, SecondYearRaag.values());
            playAscendAndDescend( repeatCount, PredefinedFrequencySet.Thaat.values() );
            System.out.println( "round[" + i + "] done \n" );
        }
    }

    private void playAscendAndDescend( int count, PredefinedFrequencySet... raags ) {
        Thaat.BILAWAL.playAscendAndDescend();
        System.out.println( "\n Played [BILAWAL]" );
        HashSet<PredefinedFrequencySet> hasheddata = randomize( raags );
        for( PredefinedFrequencySet each : hasheddata ) {
            for( int i = 0; i < count; i++ ) {
                each.playAscendAndDescend();
                System.out.println( "\nPlayed [" + each.name() + "] ." + i );
                System.out.println( "\n" );
            }
        }
    }

    private HashSet<PredefinedFrequencySet> randomize( PredefinedFrequencySet... raag ) {
        HashSet<PredefinedFrequencySet> hasheddata = new HashSet<PredefinedFrequencySet>();
        for( PredefinedFrequencySet each : raag ) {
            hasheddata.add( each );
        }
        return hasheddata;
    }
}
