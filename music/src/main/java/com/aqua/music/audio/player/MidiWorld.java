package com.aqua.music.audio.player;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class MidiWorld {
	private final Synthesizer synth;
	private final MidiChannel[] mc;

	MidiWorld() {
		this.synth = getSynth();
		this.mc = (synth == null ? null : synth.getChannels());
		Instrument[] instr = (synth == null ? null : synth.getDefaultSoundbank().getInstruments());
		if (synth != null) {
			synth.loadInstrument(instr[90]);
		}
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

	void play(int noteNumber, int velocity) {
		mc[4].noteOn(noteNumber, velocity);
	}

	public static void main(String[] args) {
		MidiWorld midiWorld = new MidiWorld();
		midiWorld.play(60, 127);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}