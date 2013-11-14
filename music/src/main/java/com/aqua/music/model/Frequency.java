package com.aqua.music.model;

import java.util.HashMap;
import java.util.Map;

public interface Frequency extends DynamicFrequency{
	public FrequencyRelation freqRel = new FrequencyRelation();

	public String prettyPrint();

	public String western();

	enum ClassicalNote implements Frequency {
		D("F#4", 369.99F),
		D_("F4", 349.23F),
		D1("F#3", 185.00F),
		D1_("F3", 174.61F),
		G("C#4", 277.18F),
		G_("C4", 261.63F),
		G1("C#3", 138.59F),
		G1_("C3", 130.81F),
		G3("C#5", 554.37F),
		G3_("C5", 523.25F),
		M("D4", 293.66F),
		M_("D#4", 311.13F),
		M1("D3", 146.83F),
		M1_("D#3", 155.56F),
		M3("D5", 587.33F),
		M3_("D#5", 622.25F),
		N("G#4", 415.30F),
		N_("G4", 392.00F),
		N1("G#3", 207.65F),
		N1_("G3", 196.00F),
		P("E4", 329.63F),
		P1("E3", 164.81F),
		P3("E5", 659.26F),
		R("B3", 246.94F),
		R_("A#3", 233.08F),
		R1("B2", 123.47F),
		R1_("A#2", 116.54F),
		R3("B4", 493.88F),
		R3_("A#4", 466.16F),
		S("A3", 220F),
		S3("A4", 440F);

		private final String fileCode;
		private final float frequencyInHz;
		private String prettyPrintText;
		private final String western;

		ClassicalNote(String western, float frequencyInHz) {
			this.western = western;
			this.frequencyInHz = frequencyInHz;
			this.fileCode = name().toLowerCase();
			this.prettyPrintText = camelCase();
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
	}
	

	class FrequencyRelation {
		private final Map<ClassicalNote, ClassicalNote[]> baseNotes = new HashMap<ClassicalNote, ClassicalNote[]>();

		private FrequencyRelation() {
			baseNotes.put(ClassicalNote.N, new ClassicalNote[] { ClassicalNote.N1 });
			baseNotes.put(ClassicalNote.N_, new ClassicalNote[] { ClassicalNote.N1_ });
			baseNotes.put(ClassicalNote.D_, new ClassicalNote[] { ClassicalNote.D1_ });
			baseNotes.put(ClassicalNote.D, new ClassicalNote[] { ClassicalNote.D1 });
			baseNotes.put(ClassicalNote.P, new ClassicalNote[] { ClassicalNote.P1, ClassicalNote.P3 });
			baseNotes.put(ClassicalNote.M, new ClassicalNote[] { ClassicalNote.M1, ClassicalNote.M3 });
			baseNotes.put(ClassicalNote.M_, new ClassicalNote[] { ClassicalNote.M1_, ClassicalNote.M3_ });
			baseNotes.put(ClassicalNote.G, new ClassicalNote[] { ClassicalNote.G1, ClassicalNote.G3 });
			baseNotes.put(ClassicalNote.G_, new ClassicalNote[] { ClassicalNote.G1_, ClassicalNote.G3_ });
			baseNotes.put(ClassicalNote.R, new ClassicalNote[] { ClassicalNote.R1, ClassicalNote.R3 });
			baseNotes.put(ClassicalNote.R_, new ClassicalNote[] { ClassicalNote.R1_, ClassicalNote.R3_ });
		}

		public ClassicalNote higher(ClassicalNote refNote) {
			return baseNotes.get(refNote)[1];
		}

		public ClassicalNote lower(ClassicalNote refNote) {
			return baseNotes.get(refNote)[0];
		}
	}
}