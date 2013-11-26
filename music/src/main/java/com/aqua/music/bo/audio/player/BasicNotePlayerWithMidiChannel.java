package com.aqua.music.bo.audio.player;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import com.aqua.music.model.core.DynamicFrequency;

public class BasicNotePlayerWithMidiChannel implements BasicNotePlayer {
	private final MidiChannel[] mc;
	private final Synthesizer synth;
	private final Instrument[] allInstruments;
	private final MidiChannel midiChannel;
	
	public BasicNotePlayerWithMidiChannel() {
		this.synth = getSynth();
		this.mc = (synth == null ? null : synth.getChannels());
		this.allInstruments = (synth == null ? null : synth.getDefaultSoundbank().getInstruments());
		synth.loadAllInstruments(synth.getDefaultSoundbank());
		this.midiChannel=mc[10];
		this.notifyInstrumentChange(allInstruments[0]);
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void play(DynamicFrequency each, int duration) {
		int eachNoteNumber=each.midiNoteNumber();
		midiChannel.noteOn(eachNoteNumber, 127);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		midiChannel.noteOn(eachNoteNumber, 0);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tidyup() {
		// TODO Auto-generated method stub

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
	
	private void notifyInstrumentChange(Instrument instrument) {
		midiChannel.programChange(instrument.getPatch().getProgram());
	}

}
