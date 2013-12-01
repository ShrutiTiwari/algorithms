package com.aqua.music.model.raag.song;

import open.music.api.AudioPlayerSettings;

public class SongTest {
	// @Test
	public void testPlayingRaagBhimpalasi() {
		AbstractSong song = new SongBhimpalasi();
		AudioPlayerSettings audioPlayConfig = AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER;
		audioPlayConfig.play(song.frequencies(), 2);
	}

	// @Test
	public void testPlayingSong() {
		AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(Song.S_BHIMPALASI.frequencies(), 2);
	}
}
