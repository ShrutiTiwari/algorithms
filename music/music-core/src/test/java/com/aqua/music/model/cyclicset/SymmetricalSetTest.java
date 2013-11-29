package com.aqua.music.model.cyclicset;

import org.junit.Test;

import com.aqua.music.api.AudioPlayerSettings;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies());
	}

	@Test
	public void testKafiWithPattern() {
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies());
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(freqSeq.frequencies());
	}
}
