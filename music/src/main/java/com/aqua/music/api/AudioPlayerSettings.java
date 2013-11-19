package com.aqua.music.api;

import java.util.Collection;

import com.aqua.music.bo.audio.manager.PlayMode;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

public enum AudioPlayerSettings {
	ASYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Asynchronous),
	ASYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Asynchronous),
	SYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Synchronous),
	SYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Synchronous);

	private AudioPlayerSettings(AudioPlayer.Factory audioPlayer, PlayMode playMode) {
		this.audioPlayerFactory = audioPlayer;
		this.playMode = playMode;
	}

	private final AudioPlayer.Factory audioPlayerFactory;
	private final PlayMode playMode;

	public void play(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.play(audioPlayerFactory.fetchInstance(), frequencyList);
	}
	
	public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.playInLoop(audioPlayerFactory.fetchInstance(), frequencyList);
	}
	
	public static void stop(){
		PlayMode.stop();
	}
	
	public static void pause(){
		PlayMode.pause();
	}

}
