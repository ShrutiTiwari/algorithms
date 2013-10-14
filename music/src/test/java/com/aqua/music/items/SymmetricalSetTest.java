package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.items.PlayableItem.SymmetricalPlayableItem;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public class SymmetricalSetTest
{
    public SymmetricalSetTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    @Test
    public void testKafi() {
        SymmetricalPlayableItem.forSet( SymmetricalSet.THAAT_KAFI ).play();
    }

    // @Test
    public void testKafiWithPattern() {
        SymmetricalPlayableItem.forSet( SymmetricalSet.THAAT_KAFI ).play();
        SymmetricalPatternApplicator<Frequency> patternApplicator = new SymmetricalPatternApplicator<Frequency>( new int[] { 1, 4,
                3 } );
        SymmetricalPlayableItem.forSet( SymmetricalSet.THAAT_KAFI ).andPattern( patternApplicator ).play();
    }
}
