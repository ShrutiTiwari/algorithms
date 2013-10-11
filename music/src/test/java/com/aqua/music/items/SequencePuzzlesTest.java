package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.items.SequencePlayer.AllThaat;
import com.aqua.music.play.AscendDescendSequencePuzzles;
import com.aqua.music.play.AudioLibrary;

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
