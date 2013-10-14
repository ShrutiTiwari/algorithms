package com.aqua.music.items;

import org.junit.Test;

import com.aqua.music.model.PredefinedFrequencySet;
import com.aqua.music.play.AudioLibrary;

public class AsymmetricalSetTest
{

    @Test
    public void test1() {
        AudioLibrary.initializeWithGivenSeconds( 1 );
        int repeatCount = 2;
        //playRaags( repeatCount, ArohiAvrohiPlayer.AllThaat.values() );
        // puzzleBuilder.playRaags(MULTANI);
        // puzzleBuilder.playRaags(GUJARI_TODI);
    }

    private void playRaags( int count, PredefinedFrequencySet... raag ) {
        int item = 1;
        for( PredefinedFrequencySet each : raag ) {
            System.out.println( "\n Playing " + count + "times  [" + each.name() + "_" + each.type() + "]=[" + item + " of "
                    + raag.length + " items]" );
            for( int i = 0; i < count; i++ ) {
                System.out.println( "\t Playing " + (i + 1) + "/" + count + " time. " );
                each.playAscendAndDescend();
            }
            item++;
        }
    }
}
