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
        AllThaat.KAFI.playAscendAndDescend();
    }
}
