package com.aqua.music.model.song;

import org.junit.Test;

import com.aqua.music.api.AudioPlayerSettings;

public class TaanTest {
	@Test
	public void testJaunpuriTaans1() {
		Song.SONG_JAUNPURI.playTaan(AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER);
	}
}
