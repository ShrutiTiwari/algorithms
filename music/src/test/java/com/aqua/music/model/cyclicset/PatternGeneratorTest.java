package com.aqua.music.model.cyclicset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;

public class PatternGeneratorTest {
	@Test
	public void testPair() {
		Frequency[] input = SymmetricalSet.THAAT_KAFI.ascendNotes();
		System.out.println("==>" + input.length);
		List<int[]> result = PermuatationsGenerator.PAIR.generatePermutations(input);
		assertNotNull(result);
		assertEquals(input.length * 2, result.size());
		assertEquals("12,21,13,31,14,41,15,51,16,61,17,71,", toStringForComparison(result));
	}

	// @Test
	public void playPairOfNotes() {
		Frequency[] input = SymmetricalSet.THAAT_KAFI.ascendNotes();
		List<int[]> result = PermuatationsGenerator.PAIR.generatePermutations(input);
		for (int[] each : result) {
			CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,each);
			AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER.play(freqSeq.frequencies());
		}
	}

	private String toStringForComparison(List<int[]> result) {
		StringBuffer buf = new StringBuffer();
		for (int[] each : result) {
			String agg = "";
			for (int x : each) {
				agg += x;
			}
			buf.append(agg + ",");

		}
		return buf.toString();
	}

}
