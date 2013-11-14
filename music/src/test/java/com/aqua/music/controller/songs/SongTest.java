package com.aqua.music.controller.songs;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public class SongTest {
	//@Test
	public void testPlayingRaagBhimpalasi() {
		AbstractSong song = new RaagBhimpalasi();
		for (int i = 0; i < 2; i++) {
			AudioLifeCycleManager.instance.play(song.frequencies(), AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
		}
	}
	
	@Test
	public void testPlayingSong() {
		for (int i = 0; i < 2; i++) {
			Song.RAAG_BHIMPALASI.play(AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER);
		}
	}
}