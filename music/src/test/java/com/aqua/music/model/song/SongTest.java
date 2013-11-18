package com.aqua.music.model.song;

import org.junit.Test;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public class SongTest {
	//@Test
	public void testPlayingRaagBhimpalasi() {
		AbstractSong song = new SongBhimpalasi(4);
		for (int i = 0; i < 2; i++) {
			AudioPlayConfig audioPlayConfig = AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER;
			audioPlayConfig.play(song.frequencies());
		}
	}
	
	@Test
	public void testPlayingSong() {
		for (int i = 0; i < 2; i++) {
			AudioPlayConfig.SYNCHRONOUS_DYNAMIC_PLAYER.play(Song.SONG_BHIMPALASI.frequencies());
		}
	}
}
