package com.aqua.music.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.aqua.music.model.FundamentalFrequency;
import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;

public class ComprehensivePatternGeneratorTest
{
    @Test
    public void testPair() {
        FundamentalFrequency[] input = SymmetricalSet.THAAT_KAFI.ascendNotes();
        System.out.println( "==>"+input.length );
        List<int[]> result = PatternGenerator.PAIR.generatePatterns( input );
        assertNotNull( result );
        assertEquals( input.length*2, result.size() );
        assertEquals( "12,21,13,31,14,41,15,51,16,61,17,71,",toStringForComparison( result ) );
    }

    private String toStringForComparison( List<int[]> result ) {
        StringBuffer buf= new StringBuffer();
        for( int[] each : result ) {
            String agg="";
            for( int x : each ) {
                agg+=x;
            }
            buf.append( agg +",");
            
        }
        return buf.toString();
    }

}
