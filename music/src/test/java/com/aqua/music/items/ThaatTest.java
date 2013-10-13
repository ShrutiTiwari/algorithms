package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.model.CommonAscDescPattern;
import com.aqua.music.model.PredefinedFrequency;
import com.aqua.music.model.PredefinedFrequencySet.Thaat;
import com.aqua.music.play.AudioLibrary;

public class ThaatTest
{
    public ThaatTest() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
    }

    //@Test
    public void testKafi() {
    	Thaat.KAFI.playAscendAndDescend();
    }
    
    @Test
    public void testKafiWithPatter() {
    	CommonAscDescPattern<PredefinedFrequency> pattern = new CommonAscDescPattern<PredefinedFrequency>(new int[] { 1, 4, 3 });
    	Thaat.KAFI.playAscendAndDescend(pattern);
    }
    
}

