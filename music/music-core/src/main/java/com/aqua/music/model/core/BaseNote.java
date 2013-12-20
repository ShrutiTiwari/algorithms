package com.aqua.music.model.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum BaseNote {
	D(new BaseFrequenciesBuilder("D", "F#", 53, new float[] { 185.00F, 369.99F, 739.99F })),
	D_(new BaseFrequenciesBuilder("D_", "F", 52, new float[] { 174.61F, 349.23F, 698.46F })),
	G(new BaseFrequenciesBuilder("G", "C#", 48, new float[] { 138.59F, 277.18F, 554.37F })),
	G_(new BaseFrequenciesBuilder("G_", "C", 47, new float[] { 130.81F, 261.63F, 523.25F })),
	M(new BaseFrequenciesBuilder("M", "D", 49, new float[] { 146.83F, 293.66F, 587.33F })),
	M_(new BaseFrequenciesBuilder("M_", "D#", 50, new float[] { 155.56F, 311.13F, 622.25F })),
	N(new BaseFrequenciesBuilder("N", "G#", 55, new float[] { 207.65F, 415.30F, 783.99F })),
	N_(new BaseFrequenciesBuilder("N_", "G", 54, new float[] { 196.00F, 392.00F, 830.61F })),
	P(new BaseFrequenciesBuilder("P", "E", 51, new float[] { 164.81F, 329.63F, 659.26F })),
	R(new BaseFrequenciesBuilder("R", "B#", 46, new float[] { 123.47F, 246.94F, 493.88F })),
	R_(new BaseFrequenciesBuilder("R_", "B", 45, new float[] { 116.54F, 233.08F, 466.16F })),
	S(new BaseFrequenciesBuilder("S", "A", 44, new float[] { 110F, 220F, 440F }));

	private AbstractFrequency[] allFrequencies;

	BaseNote(BaseFrequenciesBuilder baseFrequenciesBuilder) {
		this.allFrequencies = baseFrequenciesBuilder.frequenciesArray;
		for(AbstractFrequency each: allFrequencies ){
			each.setBaseNote(this);
		}
	}

	public Frequency getFrequencyObject(Octave octave) {
		return allFrequencies[findIndex(octave)];
	}

	private String camelCase() {
		String lowerCase = name().toLowerCase();
		String camelCase = ("" + lowerCase.charAt(0)).toUpperCase() + lowerCase.substring(1);
		return camelCase;
	}

	private int findIndex(Octave octave) {
		switch (octave) {
		case LOWER_OCTAVE:
			return 0;
		case MAIN_OCTAVE:
			return 1;
		case UPPER_OCTAVE:
			return allFrequencies.length - 1;
		}
		return 0;
	}

	public enum Octave {
		LOWER_OCTAVE,
		MAIN_OCTAVE,
		UPPER_OCTAVE;
	}

	static class AbstractFrequency implements Frequency {
		private String easternNotation;
		private final float frequencyInHz;
		private final int midiNoteNumber;
		private final String westernNotation;
		private BaseNote baseNote;

		public AbstractFrequency(float frequencyInHz, int midiNoteNumber, String westernNotation, String easternNotation) {
			this.frequencyInHz = frequencyInHz;
			this.midiNoteNumber = midiNoteNumber;
			this.westernNotation = westernNotation;
			this.easternNotation= easternNotation;
		}

		public void setBaseNote(BaseNote baseNote){
			this.baseNote=baseNote;
		}
		
		public BaseNote baseNote(){
			return baseNote;
		}
		
		@Override
		public int duration() {
			return MusicPeriod.SINGLE_BEAT.durationInMilliSec();
		}

		@Override
		public String fileCode() {
			return null;
		}

		@Override
		public float frequencyInHz() {
			return frequencyInHz;
		}

		@Override
		public int midiNoteNumber() {
			return midiNoteNumber;
		}

		@Override
		public String prettyPrint() {
			return easternNotation;
		}

		@Override
		public String western() {
			return westernNotation;
		}
	}

	static class BaseFrequenciesBuilder {
		private final AbstractFrequency[] frequenciesArray;

		BaseFrequenciesBuilder(String eNotation, String wNotation, int startMidiNumber, float[] frequencies) {
			List<Frequency> freqList = new ArrayList<Frequency>();
			int i = 0;
			int midiNumber = startMidiNumber;
			for (float each : frequencies) {
				String westernNotation = (i == 1 ? wNotation : (wNotation + (i + 1)));
				String easternNotation = (i == 1 ? eNotation : (eNotation + (i + 1)));
				freqList.add(new AbstractFrequency(each, midiNumber, westernNotation,easternNotation));
				midiNumber += 12; i++;
			}
			this.frequenciesArray = freqList.toArray(new AbstractFrequency[freqList.size()]);
		}
	}
}
