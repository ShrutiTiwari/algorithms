package com.aqua.music.model.raag.song;

import open.music.api.AudioPlayerFacade;
import open.music.api.DeviceType;

public class SongTest {
	// @Test
	public void testPlayingRaagBhimpalasi() {
		initialize();
		AbstractSong song = new SongBhimpalasi();
		AudioPlayerFacade audioPlayConfig = AudioPlayerFacade.SYNCHRONOUS_PLAYER;
		audioPlayConfig.play(song.frequencies(), 2);
	}

	// @Test
	public void testPlayingSong() {
		initialize();
		AudioPlayerFacade.SYNCHRONOUS_PLAYER.play(Song.S_BHIMPALASI.frequencies(), 2);
	}
	
	private void initialize() {
		DeviceType.DESKTOP_DYNAMIC.initializeAudioFactory();
	}

}
