package com.aqua.music.model.core;

import com.aqua.music.model.core.BaseNote.Octave;

/**
 * @author "Shruti Tiwari"
 *
 */
/**
 * @author "Shruti Tiwari"
 * 
 */
public enum ClassicalNote implements Frequency {
	D(BaseNote.D, Octave.MAIN_OCTAVE),
	D_(BaseNote.D_, Octave.MAIN_OCTAVE),
	D1(BaseNote.D, Octave.LOWER_OCTAVE),
	D1_(BaseNote.D_, Octave.LOWER_OCTAVE),
	G(BaseNote.G, Octave.MAIN_OCTAVE),
	G_(BaseNote.G_, Octave.MAIN_OCTAVE),
	G1(BaseNote.G, Octave.LOWER_OCTAVE),
	G1_(BaseNote.G_, Octave.LOWER_OCTAVE),
	G3(BaseNote.G, Octave.UPPER_OCTAVE),
	G3_(BaseNote.G_, Octave.UPPER_OCTAVE),
	M(BaseNote.M, Octave.MAIN_OCTAVE),
	M_(BaseNote.M_, Octave.MAIN_OCTAVE),
	M1(BaseNote.M, Octave.LOWER_OCTAVE),
	M1_(BaseNote.M_, Octave.LOWER_OCTAVE),
	M3(BaseNote.M, Octave.UPPER_OCTAVE),
	M3_(BaseNote.M_, Octave.UPPER_OCTAVE),
	N(BaseNote.N, Octave.MAIN_OCTAVE),
	N_(BaseNote.N_, Octave.MAIN_OCTAVE),
	N1(BaseNote.N, Octave.LOWER_OCTAVE),
	N1_(BaseNote.N_, Octave.LOWER_OCTAVE),
	P(BaseNote.P, Octave.MAIN_OCTAVE),
	P1(BaseNote.P, Octave.LOWER_OCTAVE),
	P3(BaseNote.P, Octave.UPPER_OCTAVE),
	R(BaseNote.R, Octave.MAIN_OCTAVE),
	R_(BaseNote.R_, Octave.MAIN_OCTAVE),
	R1(BaseNote.R, Octave.LOWER_OCTAVE),
	R1_(BaseNote.R_, Octave.LOWER_OCTAVE),
	R3(BaseNote.R, Octave.UPPER_OCTAVE),
	R3_(BaseNote.R, Octave.UPPER_OCTAVE),
	S(BaseNote.S, Octave.MAIN_OCTAVE),
	S3(BaseNote.S, Octave.UPPER_OCTAVE);

	private final String fileCode;
	private final BaseNote baseNote;
	private Frequency frequency;

	ClassicalNote(BaseNote baseNote, Octave octave) {
		this.baseNote = baseNote;
		this.frequency = baseNote.getFrequencyObject(octave);
		this.fileCode = name().toLowerCase();
	}

	@Override
	public int duration() {
		return MusicPeriod.SINGLE_BEAT.durationInMilliSec();
	}

	public String fileCode() {
		return fileCode;
	}

	public float frequencyInHz() {
		return frequency.frequencyInHz();
	}

	@Override
	public String prettyPrint() {
		return frequency.prettyPrint();
	}

	public String western() {
		return frequency.western();
	}

	private String camelCase() {
		String lowerCase = name().toLowerCase();
		String camelCase = ("" + lowerCase.charAt(0)).toUpperCase() + lowerCase.substring(1);
		return camelCase;
	}

	@Override
	public String toString() {
		return frequency.prettyPrint();
	}

	@Override
	public int midiNoteNumber() {
		return frequency.midiNoteNumber();
	}

	@Override
	public BaseNote baseNote() {
		return frequency.baseNote();
	}
}