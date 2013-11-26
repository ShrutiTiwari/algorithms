package com.aqua.music.model.raag.song;

import org.junit.Test;

import com.aqua.music.api.AudioPlayerSettings;

public class TaanTest {
	//@Test
	public void testJaunpuriTaans1() {
		Song.S_JAUNPURI.playTaan(AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER);
	}
	
	@Test
	public void testBhairavTaans1() {
		Song.S_BHAIRAV.playTaan(AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER);
	}
}
