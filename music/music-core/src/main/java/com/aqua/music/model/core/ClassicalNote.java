package com.aqua.music.model.core;

import com.aqua.music.model.core.BaseNote.Octave;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum ClassicalNote implements Frequency {
	D(BaseNote.D),
	D_(BaseNote.D_),
	D1(BaseNote.D),
	D1_(BaseNote.D_),
	D3(BaseNote.D),
	D3_(BaseNote.D_),
	G(BaseNote.G),
	G_(BaseNote.G_),
	G1(BaseNote.G),
	G1_(BaseNote.G_),
	G3(BaseNote.G),
	G3_(BaseNote.G_),
	M(BaseNote.M),
	M_(BaseNote.M_),
	M1(BaseNote.M),
	M1_(BaseNote.M_),
	M3(BaseNote.M),
	M3_(BaseNote.M_),
	N(BaseNote.N),
	N_(BaseNote.N_),
	N1(BaseNote.N),
	N1_(BaseNote.N_),
	N3(BaseNote.N),
	N3_(BaseNote.N_),
	P(BaseNote.P),
	P1(BaseNote.P),
	P3(BaseNote.P),
	R(BaseNote.R),
	R_(BaseNote.R_),
	R1(BaseNote.R),
	R1_(BaseNote.R_),
	R3(BaseNote.R),
	R3_(BaseNote.R),
	S(BaseNote.S),
	S1(BaseNote.S),
	S3(BaseNote.S), S4(BaseNote.S);

	private final BaseNote baseNote;
	private final String fileCode;
	private Frequency frequency;

	ClassicalNote(BaseNote baseNote) {
		this.baseNote = baseNote;
		String name = this.name();
		Octave octave1 = (name.contains("1") ? Octave.LOWER_OCTAVE : (name.contains("3") ? Octave.UPPER_OCTAVE : (name.contains("4") ? Octave.UPPER2_OCTAVE : Octave.MAIN_OCTAVE)));
		this.frequency = baseNote.getFrequencyObject(octave1);
		this.fileCode = name().toLowerCase();
	}

	@Override
	public BaseNote baseNote() {
		return baseNote;
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
	public int midiNoteNumber() {
		return frequency.midiNoteNumber();
	}

	@Override
	public Octave octave() {
		return frequency.octave();
	}

	@Override
	public String prettyPrint() {
		return frequency.prettyPrint();
	}

	@Override
	public String toString() {
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
}