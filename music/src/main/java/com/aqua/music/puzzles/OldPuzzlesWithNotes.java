package com.aqua.music.puzzles;

import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.DHA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.DHA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.GA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.GA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.MA;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.MA_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.NI;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.NI_;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.RE;
import static com.aqua.music.model.FundamentalFrequency.ClassicalNote.RE_;
import static com.aqua.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.play.AudioLibrary;

public class OldPuzzlesWithNotes
{

    static HashSet<FundamentalFrequency> saTopa = new HashSet<FundamentalFrequency>();
    static HashSet<FundamentalFrequency> paToHighSa = new HashSet<FundamentalFrequency>();
    static int REPEAT_COUNT = 6;

    public static void main( String[] args ) {
        initialize();
        // alternatePlay();
        sequentialPay();
    }

    private static void alternatePlay( HashSet<FundamentalFrequency>... saTopa2 ) {
        Iterator<FundamentalFrequency> iterator1 = saTopa.iterator();
        Iterator<FundamentalFrequency> iterator2 = paToHighSa.iterator();
        List<FundamentalFrequency> playNotes = new ArrayList<FundamentalFrequency>();
        for( int index = 0; index < paToHighSa.size(); index++ ) {
            playNotes.add( iterator1.next() );
            playNotes.add( iterator2.next() );
        }
        play( playNotes );
        playNotes = new ArrayList<FundamentalFrequency>();
        for( int index = paToHighSa.size(); index < saTopa.size(); index++ ) {
            playNotes.add( iterator1.next() );
        }
        play( playNotes );
    }

    private static void initialize() {
        AudioLibrary.initializeWithGivenSeconds( 2 );
        populateNotes( saTopa, RE, RE_, GA, GA_, MA, MA_ );
        populateNotes( paToHighSa, DHA, DHA_, NI, NI_ );
    }

    private static void play( HashSet<FundamentalFrequency> saTopa2 ) {
        List<FundamentalFrequency> playNotes = new ArrayList<FundamentalFrequency>();
        for( int i = 0; i < REPEAT_COUNT; i++ ) {
            System.out.println( "\n" );
            for( FundamentalFrequency each : saTopa2 ) {
                playNotes.add( each );
            }
        }
        play( playNotes );
    }

    private static void populateNotes( HashSet<FundamentalFrequency> agreegatator, FundamentalFrequency... notes ) {
        for( FundamentalFrequency each : notes ) {
            agreegatator.add( each );
        }
    }

    private static void sequentialPay() {
        System.out.println( "Sa to pa" );
        play( saTopa );
        System.out.println( "\n pa to high sa" );
        play( paToHighSa );
    }

    public static void play( List<FundamentalFrequency> notes ) {
        List<File> audioFiles = new ArrayList<File>();
        StringBuffer printPlaylist = new StringBuffer();
        for( FundamentalFrequency each : notes ) {
            addFileIfFound( audioFiles, each );
            printPlaylist.append( ", " + each );
        }
        System.out.println( "playing [" + printPlaylist.toString() + "]" );
        AudioLibrary.audioPlayer().playList( audioFiles );
    }
}
