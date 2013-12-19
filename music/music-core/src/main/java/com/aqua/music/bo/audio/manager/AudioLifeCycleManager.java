package com.aqua.music.bo.audio.manager;

import open.music.api.InstrumentRole;
import open.music.api.PlayApi;
import open.music.api.StateDependentUi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.player.AudioPlayer;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AudioLifeCycleManager {
	Logger logger = LoggerFactory.getLogger(AudioLifeCycleManager.class);
	final AudioLifeCycleManager instance = new AudioLifeCycleManagerImpl();
	
	void stop();
	
	AudioPlayer currentAudioPlayer();

	PlayApi.AudioPlayerNextStatus togglePauseAndResume();

	void increaseTempo();

	void decreaseTempo();

	void changeInstrumentTo(String instrument, InstrumentRole changingInstrument);

	/**
	 * @param stateDependentUi
	 */
	void addStateObserver(StateDependentUi stateDependentUi);
}
