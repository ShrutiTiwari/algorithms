package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioLibrary;

public class SymmetricalSetTest
{
    public SymmetricalSetTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    //@Test
    public void testKafi() {
    	SymmetricalSet.THAAT_KAFI.playAscendAndDescend();
    }
    
    @Test
    public void testKafiWithPattern() {
        SymmetricalSet.THAAT_KAFI.playAscendAndDescend();
    	SymmetricalSet.THAAT_KAFI.playAscendAndDescend(new SymmetricalPatternApplicator<FundamentalFrequency>(new int[] { 1, 4, 3 }));
    }
    
}

