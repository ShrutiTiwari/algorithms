package com.aqua.music.model.core;

public enum ClassicalNote implements Frequency {
	D("F#4", 369.99F,65),
	D_("F4", 349.23F,64),
	D1("F#3", 185.00F,53),
	D1_("F3", 174.61F,52),
	G("C#4", 277.18F,60),
	G_("C4", 261.63F,59),
	G1("C#3", 138.59F,48),
	G1_("C3", 130.81F,47),
	G3("C#5", 554.37F,72),
	G3_("C5", 523.25F,71),
	M("D4", 293.66F,61),
	M_("D#4", 311.13F,62),
	M1("D3", 146.83F,39),
	M1_("D#3", 155.56F,40),
	M3("D5", 587.33F,73),
	M3_("D#5", 622.25F,74),
	N("G#4", 415.30F,67),
	N_("G4", 392.00F,66),
	N1("G#3", 207.65F,55),
	N1_("G3", 196.00F,54),
	P("E4", 329.63F,63),
	P1("E3", 164.81F,51),
	P3("E5", 659.26F,77),
	R("B3", 246.94F,58),
	R_("A#3", 233.08F,57),
	R1("B2", 123.47F,46),
	R1_("A#2", 116.54F,45),
	R3("B4", 493.88F,70),
	R3_("A#4", 466.16F,69),
	S("A3", 220F, 56),
	S3("A4", 440F,68);

	private final String fileCode;
	private final float frequencyInHz;
	private String prettyPrintText;
	private final String western;
	private final int midiNoteNumber;

	ClassicalNote(String western, float frequencyInHz,int midiNoteNumber) {
		this.western = western;
		this.frequencyInHz = frequencyInHz;
		this.fileCode = name().toLowerCase();
		this.prettyPrintText = camelCase();
		this.midiNoteNumber=midiNoteNumber;
	}

	@Override
	public int duration() {
		return MusicPeriod.SINGLE_BEAT.durationInMilliSec();
	}

	public String fileCode() {
		return fileCode;
	}

	public float frequencyInHz() {
		return frequencyInHz;
	}

	@Override
	public String prettyPrint() {
		return prettyPrintText;
	}

	public String western() {
		return western;
	}

	private String camelCase() {
		String lowerCase = name().toLowerCase();
		String camelCase = ("" + lowerCase.charAt(0)).toUpperCase() + lowerCase.substring(1);
		return camelCase;
	}

	@Override
	public String toString() {
		return prettyPrintText;
	}

	@Override
	public int midiNoteNumber() {
		return midiNoteNumber;
	}
}