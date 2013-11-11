package com.aqua.music.logic;

import org.junit.Test;

import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		FrequencySequence.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

	@Test
	public void testKafiWithPattern() {
		FrequencySequence.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
		FrequencySequence freqSeq = FrequencySequence.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		freqSeq.play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}
}
