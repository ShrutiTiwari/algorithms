package com.aqua.music.items;

import static org.junit.Assert.*;

import org.junit.Test;

public class SymmetricalPatternApplicatorTest
{
    @Test
    public void simpleTest() {
        SymmetricalPatternApplicator<String> patternApplicator = new SymmetricalPatternApplicator<String>( new int[] { 1, 2 } );
        patternApplicator.generateAscendAndDescendSequences( new String[] { "A1", "A2", "A3" } );
        assertEquals( "A1A2, A2A3" + PatternApplicator.SEP + "A3A2, A2A1", patternApplicator.prettyPrintTextForAscDesc() );
    }
}
