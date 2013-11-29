package com.aqua.music.model.raag.song;

import com.aqua.music.api.AudioPlayerSettings;

public class SongTest {
	//@Test
	public void testPlayingRaagBhimpalasi() {
		AbstractSong song = new SongBhimpalasi();
		for (int i = 0; i < 2; i++) {
			AudioPlayerSettings audioPlayConfig = AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER;
			audioPlayConfig.play(song.frequencies());
		}
	}
	
	//@Test
	public void testPlayingSong() {
		for (int i = 0; i < 2; i++) {
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(Song.S_BHIMPALASI.frequencies());
		}
	}
}
