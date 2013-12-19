package com.aqua.music.model.cyclicset;

import open.music.api.AudioPlayerFacade;
import open.music.api.DeviceType;

import org.junit.Test;

public class SymmetricalSetTest {
	// @Test
	public void testKafi() {
		initialize();
		AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies(), 1);
	}

	@Test
	public void testKafiWithPattern() {
		initialize();
		AudioPlayerFacade.ASYNCHRONOUS_PLAYER.play(CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_KAFI).frequencies(), 1);
		CyclicFrequencySet freqSeq = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(SymmetricalSet.THAAT_KAFI,new int[] { 1, 4, 3 });
		AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(freqSeq.frequencies(), 1);
	}
	
	private void initialize() {
		DeviceType.DESKTOP_DYNAMIC.initializeAudioFactory();
	}

}
