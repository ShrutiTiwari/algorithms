package com.aqua.music.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aqua.music.logic.CyclicFrequencySet.CyclicSequence;
import com.aqua.music.model.Frequency;

public class SymmetricalPatternApplicatorTest {
	@Test
	public void simpleTest() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(new Frequency[] { Frequency.ClassicalNote.SA, Frequency.ClassicalNote.RE,
				Frequency.ClassicalNote.GA });
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("SaRe\tReGa", actual[0]);
		assertEquals("GaRe\tReSa", actual[1]);
	}
}
