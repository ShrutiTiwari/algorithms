package com.aqua.music.model.song;

import java.util.Collection;

import com.aqua.music.api.Playable;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.core.DynamicFrequency;

public enum Song implements Playable{
	SONG_AHIR_BHAIRAV(new SongAhirBhairav(2)),
	SONG_BAIRAGI(new SongBairagi(2)),
	SONG_BHAIRAV(new SongBhairav(4)),
	SONG_BHIMPALASI(new SongBhimpalasi(4)),
	SONG_JAUNPURI(new SongJaunpuri(4)),
	SONG_MULTANI(new SongMultani(2)),
	SONG_SHUDH_SARANG(new SongShudhSarang(4)),
	SONG_YAMAN1(new SongYaman1(4));

	private final AbstractSong song;

	private Song(AbstractSong song) {
		this.song = song;
	}

	public String asText() {
		return song.printSummary();
	}

	public Collection<DynamicFrequency> frequencies(){
		return song.frequencies();
	}

	@Override
	public void playInLoop(AudioPlayConfig audioPlayConfig) {
	}
	

	public void playTaan(AudioPlayConfig audioPlayConfig) {
		Collection<Taan> taans = song.taans();
		for (Taan each : taans) {
			Taan playtaan = each;
			System.out.println(playtaan.printText());
			for (int i = 0; i < 2; i++) {
				audioPlayConfig.play(playtaan.frequencies());
			}
		}
	}

	public String songNameAsText() {
		return this.name();
	}
}