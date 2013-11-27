package com.aqua.music.bo.audio.manager;

import javax.sound.midi.Instrument;

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

	AudioPlayer.NextStatus togglePauseAndResume();

	void increaseTempo();

	void decreaseTempo();

	void changeInstrumentTo(Instrument instrument);
}
