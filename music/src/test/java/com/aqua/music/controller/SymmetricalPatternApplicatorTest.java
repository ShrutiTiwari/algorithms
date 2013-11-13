package com.aqua.music.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.controller.PermutationApplicatorForSymmetricalSet;
import com.aqua.music.controller.CyclicFrequencySet.CyclicSequence;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public class SymmetricalPatternApplicatorTest {
	private final Frequency[] testset=testset();
	
	
	//@Test
	public void simpleTest() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(new Frequency[] { Frequency.ClassicalNote.SA, Frequency.ClassicalNote.RE,
				Frequency.ClassicalNote.GA });
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("SaRe\tReGa", actual[0]);
		assertEquals("GaRe\tReSa", actual[1]);
	}
	
	@Test
	public void simpleTestPattern12() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("SaRe_	Re_Ma	MaPa	PaNi_	Ni_Sa3", actual[0]);
		assertEquals("Sa3Ni_	Ni_Pa	PaMa	MaRe_	Re_Sa", actual[1]);
	}
	
	@Test
	public void simpleTestPattern21() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 2, 1 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		
		assertEquals("Re_Sa	MaRe_	PaMa	Ni_Pa	Sa3Ni_", actual[0]);
		assertEquals("Ni_Sa3	PaNi_	MaPa	Re_Ma	SaRe_	Ni1_Sa", actual[1]);
	}

	@Test
	public void simpleTestPattern321() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 3,  2, 1 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		//System.out.println(freqSeq.asString());
		assertEquals("MaRe_Sa	PaMaRe_	Ni_PaMa	Sa3Ni_Pa", actual[0]);
		assertEquals("PaNi_Sa3	MaPaNi_	Re_MaPa	SaRe_Ma	Ni1_SaRe_	Pa1Ni1_Sa", actual[1]);
	}

	
	@Test
	public void simpleTestPattern312() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 3, 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("MaSaRe_	PaRe_Ma	Ni_MaPa	Sa3PaNi_", actual[0]);
		assertEquals("PaSa3Ni_	MaNi_Pa	Re_PaMa	SaMaRe_	Ni1_Re_Sa", actual[1]);
	}

	
	private Frequency[] testset() {
		List<Frequency> testFreq = CyclicFrequencySet.CyclicSequence.frequencies(FrequencySet.SymmetricalSet.RAAG2_BAIRAGI.ascendNotes(), Frequency.ClassicalNote.SA, Frequency.ClassicalNote.SA3);
		Frequency[] result = testFreq.toArray(new Frequency[testFreq.size()]);
		return result;
	}
}
