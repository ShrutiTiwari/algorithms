package com.aqua.music.audio.manager;

import java.util.HashMap;
import java.util.Map;

import com.aqua.music.audio.manager.DualModeManager.PlayMode;
import com.aqua.music.audio.player.AudioPlayer;


public enum AudioLifeCycleManagers {
	FREQUENCY_BASED(AudioPlayer.Factory.DYNAMIC_AUDIO),
	VLC_BASED(AudioPlayer.Factory.STATIC_AUDIO);

	private final AudioPlayer.Factory audioPlayer;

	private Map<PlayMode, AudioLifeCycleManager > players= new HashMap<PlayMode, AudioLifeCycleManager>();

	private boolean initialized=false;

	private AudioLifeCycleManagers(AudioPlayer.Factory audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	public AudioLifeCycleManager player(PlayMode playMode) {
		try {
			if (!initialized) {
				lazyInitialize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return players.get(playMode);
	}

	private synchronized void lazyInitialize() throws InstantiationException, IllegalAccessException {
		if (!initialized) {
			AudioPlayer audioPlayerInstance = audioPlayer.instance();
			for(PlayMode eachMode: PlayMode.values()){
				players.put(eachMode, new AudioLifeCycleManagerWithDualMode(eachMode, audioPlayerInstance));
			}
			initialized=true;
		}
	}
	
	public static AudioLifeCycleManager nonBlockingFrequencyPlayer() {
		return FREQUENCY_BASED.player(DualModeManager.PlayMode.Asynchornous);
	}
}
