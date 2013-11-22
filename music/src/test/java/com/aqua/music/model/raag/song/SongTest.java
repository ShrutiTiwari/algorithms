package com.aqua.music.model.raag.song;

import org.junit.Test;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.model.raag.song.AbstractSong;
import com.aqua.music.model.raag.song.Song;
import com.aqua.music.model.raag.song.SongBhimpalasi;

public class SongTest {
	//@Test
	public void testPlayingRaagBhimpalasi() {
		AbstractSong song = new SongBhimpalasi(4);
		for (int i = 0; i < 2; i++) {
			AudioPlayerSettings audioPlayConfig = AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER;
			audioPlayConfig.play(song.frequencies());
		}
	}
	
	@Test
	public void testPlayingSong() {
		for (int i = 0; i < 2; i++) {
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(Song.SONG_BHIMPALASI.frequencies());
		}
	}
}
