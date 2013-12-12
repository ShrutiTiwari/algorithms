package com.aqua.music.bo.audio.manager;

import javax.sound.midi.Instrument;

import open.music.api.InstrumentRole;
import open.music.api.PlayApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AudioLifeCycleManager {
	Logger logger = LoggerFactory.getLogger(AudioLifeCycleManager.class);
	final AudioLifeCycleManager instance = new AudioLifeCycleManagerImpl();
	
	void stop();

	PlayApi.AudioPlayerNextStatus togglePauseAndResume();

	void increaseTempo();

	void decreaseTempo();

	void changeInstrumentTo(Instrument instrument, InstrumentRole changingInstrument);
}
