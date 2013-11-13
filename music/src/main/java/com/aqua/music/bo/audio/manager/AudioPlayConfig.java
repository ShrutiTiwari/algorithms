package com.aqua.music.bo.audio.manager;

import com.aqua.music.bo.audio.player.AudioPlayer;

public enum AudioPlayConfig {
	ASYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Asynchronous),
	ASYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Asynchronous),
	SYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Synchronous),
	SYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Synchronous);

	private AudioPlayConfig(AudioPlayer.Factory audioPlayer, PlayMode playMode) {
		this.audioPlayerFactory = audioPlayer;
		this.playMode = playMode;
	}

	private final AudioPlayer.Factory audioPlayerFactory;
	private final PlayMode playMode;
	public PlayMode playMode() {
		return playMode;
		
	}
	public AudioPlayer audioPlayer() {
		return audioPlayerFactory.fetchInstance();
	}
}
