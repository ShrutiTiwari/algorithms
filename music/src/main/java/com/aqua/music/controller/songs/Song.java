package com.aqua.music.controller.songs;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;

public enum Song {
	RAAG_BHIMPALASI(new RaagBhimpalasi());
	//RAAG_KAFI(new RaagKaafi());

	private final AbstractSong song;

	private Song(AbstractSong song) {
		this.song = song;
	}

	public String songNameAsText() {
		return this.name();
	}

	public String asText() {
		return null;
	}

	private final AudioLifeCycleManager player = AudioLifeCycleManager.instance;

	public void play(AudioPlayConfig audioPlayConfig) {
		player.play(song.frequencies(), audioPlayConfig);
	}

}