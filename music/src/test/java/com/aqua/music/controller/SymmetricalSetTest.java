package com.aqua.music.controller;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

	@Test
	public void testKafiWithPattern() {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		freqSeq.play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}
}
