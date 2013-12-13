package com.aqua.music.bo.audio.player;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import open.music.api.InstrumentRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.DynamicFrequency;

/**
 * @author "Shruti Tiwari"
 * 
 */
class BasicNotePlayerWithMidiChannel implements BasicNotePlayer {
	Logger logger = LoggerFactory.getLogger(BasicNotePlayerWithMidiChannel.class);
	private final Instrument[] allInstruments;
	private final MidiChannel[] mc;
	private final MidiChannel mainNoteChannel;
	private final MidiChannel rhythmChannel;
	private final Synthesizer synth;
	private volatile int activeNoteNumber = -1;
	private final int rhythmNote = ClassicalNote.S.midiNoteNumber();

	public Instrument[] allInstruments() {
		return allInstruments;
	}

	public BasicNotePlayerWithMidiChannel() {
		this.synth = getSynth();
		this.mc = (synth == null ? null : synth.getChannels());
		this.allInstruments = (synth == null ? null : synth.getDefaultSoundbank().getInstruments());
		synth.loadAllInstruments(synth.getDefaultSoundbank());
		this.rhythmChannel = mc[9];
		this.mainNoteChannel = mc[8];
		this.notifyInstrumentChange(allInstruments[13], InstrumentRole.RHYTHM);
	}

	@Override
	public void finish() {

	}

	public void notifyInstrumentChange(Instrument instrument, InstrumentRole instrumentRole) {
		if (instrumentRole == InstrumentRole.MAIN) {
			logger.info("changing main instrument to [" + instrument + "]");
			synth.loadInstrument(instrument);
			mainNoteChannel.programChange(instrument.getPatch().getProgram());
		} else {
			logger.info("changing rhythm instrument to [" + instrument + "]");
			synth.loadInstrument(instrument);
			rhythmChannel.programChange(instrument.getPatch().getProgram());
		}
	}

	@Override
	public void play(DynamicFrequency each, int duration) {
		this.activeNoteNumber = each.midiNoteNumber();
		//rhythmChannel.noteOn(rhythmNote, 90);
		mainNoteChannel.noteOn(activeNoteNumber, 127);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mainNoteChannel.noteOn(activeNoteNumber, 0);
		//rhythmChannel.noteOn(rhythmNote, 0);
		activeNoteNumber = -1;
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {
		if (activeNoteNumber != -1) {
			mainNoteChannel.noteOn(activeNoteNumber, 0);
			rhythmChannel.noteOn(rhythmNote, 0);
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
