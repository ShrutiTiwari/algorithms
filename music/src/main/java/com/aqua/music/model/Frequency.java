package com.aqua.music.model;

public interface Frequency {
	public String fileCode();

	public float frequencyInHz();

	public String prettyPrint();

	public String western();

	enum ClassicalNote implements Frequency {
		DHA("F#4", 369.99F),
		DHA_("F4", 349.23F),
		DHA1_("F3", 174.61F),
		DHA3("F#3", 185.00F),
		GA("C#4", 277.18F),
		GA_("C4", 261.63F),
		GA1("C#3", 138.59F),
		GA1_("C3", 130.81F),
		GA3("C#5", 554.37F),
		GA3_("C5", 523.25F),
		MA("D4", 293.66F),
		MA_("D#4", 311.13F),
		MA1("D3", 146.83F),
		MA1_("D#3", 155.56F),
		MA3("D5", 587.33F),
		MA3_("D#5", 622.25F),
		NI("G#4", 415.30F),
		NI_("G4", 392.00F),
		NI1("G#3", 207.65F),
		NI1_("G3", 196.00F),
		PA("E4", 329.63F),
		PA1("E3", 164.81F),
		PA3("E5", 659.26F),
		RE("B3", 246.94F),
		RE_("A#3", 233.08F),
		RE3("B4", 493.88F),
		RE3_("A#4", 466.16F),
		SA("A3", 220F),
		SA3("A4", 440F);

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
	}
}