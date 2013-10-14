package com.aqua.music.puzzles;

import static com.aqua.music.model.Frequency.ClassicalNote.DHA;
import static com.aqua.music.model.Frequency.ClassicalNote.DHA_;
import static com.aqua.music.model.Frequency.ClassicalNote.GA;
import static com.aqua.music.model.Frequency.ClassicalNote.GA_;
import static com.aqua.music.model.Frequency.ClassicalNote.MA;
import static com.aqua.music.model.Frequency.ClassicalNote.MA_;
import static com.aqua.music.model.Frequency.ClassicalNote.NI;
import static com.aqua.music.model.Frequency.ClassicalNote.NI_;
import static com.aqua.music.model.Frequency.ClassicalNote.RE;
import static com.aqua.music.model.Frequency.ClassicalNote.RE_;
import static com.aqua.music.play.AudioLibrary.addFileIfFound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.aqua.music.model.Frequency;
import com.aqua.music.play.AudioLibrary;
import com.aqua.music.play.AudioPlayer;

public class OldPuzzlesWithNotes
{

    static HashSet<Frequency> saTopa = new HashSet<Frequency>();
    static HashSet<Frequency> paToHighSa = new HashSet<Frequency>();
    static int REPEAT_COUNT = 6;

    public static void main( String[] args ) {
        initialize();
        // alternatePlay();
        sequentialPay();
    }

    private static void alternatePlay( HashSet<Frequency>... saTopa2 ) {
        Iterator<Frequency> iterator1 = saTopa.iterator();
        Iterator<Frequency> iterator2 = paToHighSa.iterator();
        List<Frequency> playNotes = new ArrayList<Frequency>();
        for( int index = 0; index < paToHighSa.size(); index++ ) {
            playNotes.add( iterator1.next() );
            playNotes.add( iterator2.next() );
        }
        play( playNotes );
        playNotes = new ArrayList<Frequency>();
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

    private static void play( HashSet<Frequency> saTopa2 ) {
        List<Frequency> playNotes = new ArrayList<Frequency>();
        for( int i = 0; i < REPEAT_COUNT; i++ ) {
            System.out.println( "\n" );
            for( Frequency each : saTopa2 ) {
                playNotes.add( each );
            }
        }
        play( playNotes );
    }

    private static void populateNotes( HashSet<Frequency> agreegatator, Frequency... notes ) {
        for( Frequency each : notes ) {
            agreegatator.add( each );
        }
    }

    private static void sequentialPay() {
        System.out.println( "Sa to pa" );
        play( saTopa );
        System.out.println( "\n pa to high sa" );
        play( paToHighSa );
    }

    public static void play( List<Frequency> notes ) {
        List<File> audioFiles = new ArrayList<File>();
        StringBuffer printPlaylist = new StringBuffer();
        for( Frequency each : notes ) {
            addFileIfFound( audioFiles, each );
            printPlaylist.append( ", " + each );
        }
        System.out.println( "playing [" + printPlaylist.toString() + "]" );
        AudioPlayer.BLOCKING_VLC_PLAYER.playList( audioFiles );
    }
}
