package com.aqua.music.bo.audio.player;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class MidiWorld {
	private final MidiChannel[] mc;
	private final Synthesizer synth;
	private Instrument instrument;
	private final Instrument[] allInstruments; 

	
	public Instrument[] allInstruments(){
		return allInstruments; 
	}
	
	public static Instrument[] getInstruments(){
		return new MidiWorld().allInstruments;
	}
	
	
	MidiWorld() {
		this.synth = getSynth();
		this.mc = (synth == null ? null : synth.getChannels());
		this.allInstruments = (synth == null ? null : synth.getDefaultSoundbank().getInstruments());
		/*for (Instrument each : allInstruments) {
			System.out.println(each);
		}*/
		synth.loadAllInstruments(synth.getDefaultSoundbank());
	}

	public static void main(String[] args) {
		MidiWorld midiWorld = new MidiWorld();
		int[] noteNumbers = { 56, 58, 60, 61, 63, 65, 67, 68 };
		midiWorld.playAscendDescendWith(10, noteNumbers);
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

	private void playAscendDescendWith(int midiChannelNum, int... noteNumbers) {
		System.out.println("Playing with [midiChannelNum]=" + midiChannelNum);
		MidiChannel midiChannel = mc[midiChannelNum];
		//notifyInstrumentChange(midiChannelNum);
		playAscendDescend(midiChannel, noteNumbers);
		notifyInstrumentChange(midiChannelNum);
		playAscendDescend(midiChannel, noteNumbers);
	}

	private void playAscendDescend(MidiChannel midiChannel, int... noteNumbers) {
		for (int eachNoteNumber : noteNumbers) {
			midiChannel.noteOn(eachNoteNumber, 127);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			midiChannel.noteOn(eachNoteNumber, 0);
		}
		
		for (int j= noteNumbers.length-1; j>=0; j--) {
			midiChannel.noteOn(noteNumbers[j], 127);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			midiChannel.noteOn(noteNumbers[j], 0);
		}
	}

	private void notifyInstrumentChange(int midiChannelNum) {
		MidiChannel midiChannel = mc[midiChannelNum];
		midiChannel.programChange(instrument.getPatch().getProgram());
		/*try {
			Sequence sequence = new Sequence(Sequence.PPQ, 1);
			Track track = sequence.createTrack();
			ShortMessage sm = new ShortMessage();
			int instrumentNum=16;
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, midiChannelNum, instrumentNum, 0);
			track.add(new MidiEvent(sm, 0));
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}*/
	}
}