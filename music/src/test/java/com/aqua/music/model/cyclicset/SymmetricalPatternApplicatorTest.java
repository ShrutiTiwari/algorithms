package com.aqua.music.model.cyclicset;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.CyclicSequence;
import com.aqua.music.model.raag.RaagArohiAvrohi;

public class SymmetricalPatternApplicatorTest {
	private final Frequency[] testset=testset();
	
	@Test
	public void simpleTest() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(new Frequency[] { ClassicalNote.S, ClassicalNote.R,
				ClassicalNote.G });
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("SR\tRG", actual[0]);
		assertEquals("GR\tRS", actual[1]);
	}
	
	@Test
	public void simpleTestPattern12() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("SR_	R_M	MP	PN_	N_S3", actual[0]);
		assertEquals("S3N_	N_P	PM	MR_	R_S", actual[1]);
	}
	
	@Test
	public void simpleTestPattern21() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 2, 1 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		
		assertEquals("R_S	MR_	PM	N_P	S3N_", actual[0]);
		assertEquals("N_S3	PN_	MP	R_M	SR_	N1_S", actual[1]);
	}

	@Test
	public void simpleTestPattern321() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 3,  2, 1 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		//System.out.println(freqSeq.asString());
		assertEquals("MR_S	PMR_	N_PM	S3N_P", actual[0]);
		assertEquals("PN_S3	MPN_	R_MP	SR_M	N1_SR_	P1N1_S", actual[1]);
	}

	
	@Test
	public void simpleTestPattern312() {
		PermutationApplicatorForSymmetricalSet patternApplicator = new PermutationApplicatorForSymmetricalSet(new int[] { 3, 1, 2 });
		CyclicSequence freqSeq = patternApplicator.initializeWith(testset);
		String[] actual = freqSeq.asString().split(patternApplicator.NEW_LINE_SEP);
		assertEquals("MSR_	PR_M	N_MP	S3PN_", actual[0]);
		assertEquals("PS3N_	MN_P	R_PM	SMR_	N1_R_S", actual[1]);
	}

	
	private Frequency[] testset() {
		return RaagArohiAvrohi.BAIRAGI.ascendNotes();
	}
}
