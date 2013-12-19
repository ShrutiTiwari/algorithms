package com.aqua.music.model.raag.song;

import open.music.api.AudioPlayerFacade;
import open.music.api.DeviceType;

import org.junit.Test;

public class TaanTest {
	//@Test
	public void testJaunpuriTaans1() {
		initialize();
		Song.S_JAUNPURI.playTaan(AudioPlayerFacade.SYNCHRONOUS_PLAYER);
	}
	
	//@Test
	public void testBhairavTaans1() {
		initialize();
		Song.S_BHAIRAV.playTaan(AudioPlayerFacade.SYNCHRONOUS_PLAYER);
	}
	
	//@Test
	public void testYamanTaans1() {
		initialize();
		AudioPlayerFacade.increaseTempo();
		AudioPlayerFacade.increaseTempo();
		Song.S_YAMAN1.playTaan(AudioPlayerFacade.SYNCHRONOUS_PLAYER);
	}

	@Test
	public void testBhimpalasiTaans1() {
		initialize();
		AudioPlayerFacade.increaseTempo();
		AudioPlayerFacade.increaseTempo();
		Song.S_BHIMPALASI.playTaan(AudioPlayerFacade.SYNCHRONOUS_PLAYER);
	}
	
	private void initialize() {
		DeviceType.DESKTOP_DYNAMIC.initializeAudioFactory();
	}
}
