package com.aqua.music.controller.songs;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public enum Song {
	RAAG_BHIMPALASI(new RaagBhimpalasi(4)),
	RAAG_KAFI(new RaagKaafi(4));

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

}