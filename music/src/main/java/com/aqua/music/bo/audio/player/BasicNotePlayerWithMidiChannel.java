package com.aqua.music.bo.audio.player;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
class BasicNotePlayerWithMidiChannel implements BasicNotePlayer {
	Logger logger = LoggerFactory.getLogger(BasicNotePlayerWithMidiChannel.class);
	private final Instrument[] allInstruments;
	private final MidiChannel[] mc;
	private final MidiChannel midiChannel;
	private final Synthesizer synth;
	private volatile int activeNoteNumber=-1;

	public Instrument[] allInstruments() {
		return allInstruments;
	}

	public BasicNotePlayerWithMidiChannel() {
		this.synth = getSynth();
		this.mc = (synth == null ? null : synth.getChannels());
		this.allInstruments = (synth == null ? null : synth.getDefaultSoundbank().getInstruments());
		synth.loadAllInstruments(synth.getDefaultSoundbank());
		this.midiChannel = mc[10];
		this.notifyInstrumentChange(allInstruments[73]);
	}

	@Override
	public void finish() {

	}

	public void notifyInstrumentChange(Instrument instrument) {
		logger.info("changing the instrument to [" + instrument + "]");
		midiChannel.programChange(instrument.getPatch().getProgram());
	}

	@Override
	public void play(DynamicFrequency each, int duration) {
		this.activeNoteNumber = each.midiNoteNumber();
		midiChannel.noteOn(activeNoteNumber, 127);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		midiChannel.noteOn(activeNoteNumber, 0);
		activeNoteNumber = -1;
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {
		if (activeNoteNumber != -1) {
			midiChannel.noteOn(activeNoteNumber, 0);
		}
	}

	@Override
	public void tidyup() {

	}

	private Synthesizer getSynth() {
		Synthesizer synth = null;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return synth;
	}

}
