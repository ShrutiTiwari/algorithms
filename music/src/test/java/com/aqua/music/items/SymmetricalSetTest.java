package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.audio.player.AudioLibrary;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class SymmetricalSetTest
{
    public SymmetricalSetTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    //@Test
    public void testKafi() {
        PlayableItem.Factory.forPlayerAndSet(FilesystemPlayableItem.blocking,SymmetricalSet.THAAT_KAFI).play();
    }

     @Test
    public void testKafiWithPattern() {
        PlayableItem.Factory.forPlayerAndSet(PlayableItem.nonBlockingPlayer,SymmetricalSet.THAAT_KAFI).play();
        SymmetricalPatternApplicator<Frequency> patternApplicator = new SymmetricalPatternApplicator<Frequency>( new int[] { 1, 4,
                3 } );
        PlayableItem.Factory.forPlayerAndSet(FilesystemPlayableItem.blocking,SymmetricalSet.THAAT_KAFI).andPattern( patternApplicator ).play();
    }
}
