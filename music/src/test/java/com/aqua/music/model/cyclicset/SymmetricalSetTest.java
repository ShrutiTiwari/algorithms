package com.aqua.music.model.cyclicset;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies());
	}

	@Test
	public void testKafiWithPattern() {
		AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies());
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER.play(freqSeq.frequencies());
	}
}
