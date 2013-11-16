package com.aqua.music.model.song;

import java.util.Collection;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.cyclicset.Playable;

public enum Song implements Playable{
	SONG_BHIMPALASI(new SongBhimpalasi(4)),
	SONG_JAUNPURI(new SongJaunpuri(4)),
	SONG_YAMAN1(new SongYaman1(4)),
	SONG_BAIRAGI(new SongBairagi(2)),
	SONG_MULTANI(new SongMultani(2)),
	SONG_SHUDH_SARANG(new SongShudhSarang(4));

	private final AbstractSong song;

	private Song(AbstractSong song) {
		this.song = song;
	}

	public String songNameAsText() {
		return this.name();
	}

	public String asText() {
		return song.printSummary();
	}

	private final AudioLifeCycleManager player = AudioLifeCycleManager.instance;

	public String play(AudioPlayConfig audioPlayConfig) {
		String printSummary = song.printSummary();
		System.out.println(printSummary);
		player.play(song.frequencies(), audioPlayConfig);
		return printSummary;
	}
	

	public void playTaan(AudioPlayConfig audioPlayConfig) {
		Collection<Taan> taans = song.taans();
		for (Taan each : taans) {
			Taan playtaan = each;
			System.out.println(playtaan.printText());
			for (int i = 0; i < 2; i++) {
				AudioLifeCycleManager.instance.play(playtaan.frequencies(), audioPlayConfig);
			}
		}
	}
}