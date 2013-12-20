package com.aqua.music.model.core;

/**
 * @author "Shruti Tiwari"
 *
 */
/**
 * @author "Shruti Tiwari"
 * 
 */
public enum BaseNote {
	D(new BaseNoteKeeper("F#", 53, new float[] { 185.00F, 369.99F, 739.99F })),
	D_(new BaseNoteKeeper("F", 52, new float[] { 174.61F, 349.23F, 698.46F })),
	G(new BaseNoteKeeper("C#", 48, new float[] { 138.59F, 277.18F, 554.37F })),
	G_(new BaseNoteKeeper("C", 47, new float[] { 130.81F, 261.63F, 523.25F })),
	M(new BaseNoteKeeper("D", 49, new float[] { 146.83F, 293.66F, 587.33F })),
	M_(new BaseNoteKeeper("D#", 50, new float[] { 155.56F, 311.13F, 622.25F })),
	N(new BaseNoteKeeper("G#", 55, new float[] { 207.65F, 415.30F, 783.99F })),
	N_(new BaseNoteKeeper("G", 54, new float[] { 196.00F, 392.00F, 830.61F })),
	P(new BaseNoteKeeper("E", 51, new float[] { 164.81F, 329.63F, 659.26F })),
	R(new BaseNoteKeeper("B#", 46, new float[] { 123.47F, 246.94F, 493.88F })),
	R_(new BaseNoteKeeper("B", 45, new float[] { 116.54F, 233.08F, 466.16F })),
	S(new BaseNoteKeeper("A", 44, new float[] { 110F, 220F, 440F }));

	private BaseNoteKeeper baseNoteKeeper;

	BaseNote(BaseNoteKeeper baseNoteKeeper) {
		this.baseNoteKeeper = baseNoteKeeper;
	}

	private String camelCase() {
		String lowerCase = name().toLowerCase();
		String camelCase = ("" + lowerCase.charAt(0)).toUpperCase() + lowerCase.substring(1);
		return camelCase;
	}

	public int[] midiNumbers() {
		return baseNoteKeeper.midiNumbers;
	}

	public float[] frequencies() {
		return baseNoteKeeper.frequencies;
	}

	public String westernNotation() {
		return baseNoteKeeper.westernNotation;
	}

	float higherFreq() {
		return baseNoteKeeper.frequencies[2];
	}

	float lowerFreq() {
		return baseNoteKeeper.frequencies[0];
	}

	float middleFreq() {
		return baseNoteKeeper.frequencies[1];
	}

	static class BaseNoteKeeper {
		private final float[] frequencies;
		private final int[] midiNumbers;
		private final String westernNotation;

		BaseNoteKeeper(String westernNotation, int startMidiNumber, float[] frequencies) {
			this.frequencies = frequencies;
			this.westernNotation = westernNotation;
			this.midiNumbers = new int[frequencies.length];

			int i = 0;
			int midiNumber = startMidiNumber;
			for (float each : frequencies) {
				midiNumbers[i++] = midiNumber;
				midiNumber += 12;
			}
		}
	}

	public float getFrequency(Octave octave) {
		return baseNoteKeeper.frequencies[findIndex(octave)];
	}

	public int getMidiNumber(Octave octave) {
		return baseNoteKeeper.midiNumbers[findIndex(octave)];
	}

	private int findIndex(Octave octave) {
		switch (octave) {
		case LOWER_OCTAVE:
			return 0;
		case MAIN_OCTAVE:
			return 1;
		case UPPER_OCTAVE:
			return frequencies().length - 1;
		}
		return 0;
	}

	public enum Octave {
		LOWER_OCTAVE,
		MAIN_OCTAVE,
		UPPER_OCTAVE;
	}
}
