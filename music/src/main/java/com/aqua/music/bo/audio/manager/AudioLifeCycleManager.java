package com.aqua.music.bo.audio.manager;

import javax.sound.midi.Instrument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AudioLifeCycleManager {
	Logger logger = LoggerFactory.getLogger(AudioLifeCycleManager.class);
	final AudioLifeCycleManager instance = new AudioLifeCycleManagerImpl();
	
	void stop();

	String togglePauseAndResume();

	void increaseTempo();

	void decreaseTempo();

	void changeInstrumentTo(Instrument instrument);
}
