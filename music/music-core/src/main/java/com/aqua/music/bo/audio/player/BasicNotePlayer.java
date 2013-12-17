package com.aqua.music.bo.audio.player;

import open.music.api.InstrumentRole;

import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface BasicNotePlayer {
	void finish();
	void play(DynamicFrequency each, int duration);
	void start();
	void stop();
	void tidyup();
	void notifyInstrumentChange(String instrument, InstrumentRole changingInstrument);
	String[] allInstruments();
	
	BasicNotePlayer MIDI_BASED_PLAYER = new BasicNotePlayerWithMidiChannel();
	BasicNotePlayer MATH_SIN_BASED_PLAYER = new BasicNotePlayerWithMathSin();
}