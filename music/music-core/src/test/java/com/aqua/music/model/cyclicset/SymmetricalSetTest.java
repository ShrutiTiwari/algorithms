package com.aqua.music.model.cyclicset;

import open.music.api.AudioPlayerSettings;

import org.junit.Test;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies(), 1);
	}

	@Test
	public void testKafiWithPattern() {
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies(), 1);
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(freqSeq.frequencies(), 1);
	}
}
