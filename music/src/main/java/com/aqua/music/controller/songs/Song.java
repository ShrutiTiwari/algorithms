package com.aqua.music.controller.songs;

import java.util.Collection;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public enum Song {
	RAAG_BHIMPALASI(new RaagBhimpalasi(4)),
	RAAG_JAUNPURI(new RaagJaunpuri(4));

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

	public void play(AudioPlayConfig audioPlayConfig) {
		System.out.println(song.printSummary());
		player.play(song.frequencies(), audioPlayConfig);
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