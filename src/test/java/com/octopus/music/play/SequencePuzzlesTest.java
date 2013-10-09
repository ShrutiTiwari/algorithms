package com.aqua.music.play;

import org.junit.Test;

import com.aqua.music.play.AudioLibrary;
import com.aqua.music.play.SequencePlayer.AllThaat;

public class SequencePuzzlesTest
{
    public SequencePuzzlesTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    @Test
    public void testPlayThaats() {
        AscendDescendSequencePuzzles puzzleBuilder = new AscendDescendSequencePuzzles();
        //puzzleBuilder.playThats();
    }

    //@Test
    public void testKafiSequence() {
        AscendDescendSequencePuzzles puzzleBuilder = new AscendDescendSequencePuzzles();
        AllThaat.KAFI.playAscendAndDescend();
        for( int i = 0; i < 2; i++ ) {
            String PATTERN12 = "1-2";
            String PATTERN123 = "1-2-3";
            AllThaat.KAFI.playSequence( PATTERN12 );
        }
    }
}
