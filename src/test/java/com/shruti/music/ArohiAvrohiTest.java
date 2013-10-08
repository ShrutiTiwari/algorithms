package com.shruti.music;

import org.junit.Test;

import com.shruti.music.ArohiAvrohiPlayer.AllThaat;

public class ArohiAvrohiTest
{
    public ArohiAvrohiTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    @Test
    public void testPlayThaats() {
        ArohiAvrohiPuzzles puzzleBuilder = new ArohiAvrohiPuzzles();
        //puzzleBuilder.playThats();
    }

    //@Test
    public void testKafiSequence() {
        ArohiAvrohiPuzzles puzzleBuilder = new ArohiAvrohiPuzzles();
        AllThaat.KAFI.playAarohiAvrohi();
        for( int i = 0; i < 2; i++ ) {
            String PATTERN12 = "1-2";
            String PATTERN123 = "1-2-3";
            AllThaat.KAFI.playSequence( PATTERN12 );
        }
    }
}
