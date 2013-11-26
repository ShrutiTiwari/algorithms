package com.aqua.music.api;

import java.util.Collection;

import javax.sound.midi.Instrument;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioTask;
import com.aqua.music.bo.audio.manager.PlayMode;
import com.aqua.music.bo.audio.player.AudioPlayer;
import com.aqua.music.model.core.DynamicFrequency;

public enum AudioPlayerSettings {
	ASYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Asynchronous),
	ASYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Asynchronous),
	SYNCHRONOUS_DYNAMIC_PLAYER(AudioPlayer.Factory.DYNAMIC_AUDIO, PlayMode.Synchronous),
	SYNCHRONOUS_STATIC_PLAYER(AudioPlayer.Factory.STATIC_AUDIO, PlayMode.Synchronous);

	private final AudioPlayer.Factory audioPlayerFactory;

	private final PlayMode playMode;
	private AudioPlayerSettings(AudioPlayer.Factory audioPlayer, PlayMode playMode) {
		this.audioPlayerFactory = audioPlayer;
		this.playMode = playMode;
	}

	public static void decreaseTempo() {
		try {
			AudioLifeCycleManager.instance.decreaseTempo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void increaseTempo() {
		try {
			AudioLifeCycleManager.instance.increaseTempo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pause() {
		try {
			AudioLifeCycleManager.instance.pause();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void playAsynchronously(AudioTask<Playable> audioTask) {
		PlayMode.Asynchronous.playTask(audioTask);
	}

	public static void resume() {
		try {
			AudioLifeCycleManager.instance.resume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeInstrumentTo(Object obj) {
		if (obj instanceof Instrument) {
			AudioLifeCycleManager.instance.changeInstrumentTo((Instrument)obj);
		}
	}

	public static void stop() {
		try {
			AudioLifeCycleManager.instance.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.play(audioPlayerFactory.fetchInstance(), frequencyList);
	}

	public void playInLoop(Collection<? extends DynamicFrequency> frequencyList) {
		playMode.playInLoop(audioPlayerFactory.fetchInstance(), frequencyList);
	}
}
