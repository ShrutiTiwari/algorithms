package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.model.SymmetricalPattern;
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
    public void testKafiWithPatter() {
        SymmetricalSet.THAAT_KAFI.playAscendAndDescend();
    	SymmetricalPattern<FundamentalFrequency> pattern = new SymmetricalPattern<FundamentalFrequency>(new int[] { 1, 4, 3 });
    	SymmetricalSet.THAAT_KAFI.playAscendAndDescend(pattern);
    }
    
}

