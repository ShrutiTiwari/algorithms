package com.aqua.music.model.core;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface DynamicFrequency {
	public int duration();

	public float frequencyInHz();
	
	public String fileCode();
	public int midiNoteNumber();
	
	class CustomFreqDuration implements DynamicFrequency{
		private final int duration;
		private final Frequency frequency;

		public CustomFreqDuration(Frequency frequency, int duration) {
			this.duration = duration;
			this.frequency = frequency;
		}
		@Override
		public int duration() {
			return duration;
		}

		@Override
		public float frequencyInHz() {
			return frequency.frequencyInHz();
		}
		@Override
		public String fileCode() {
			return frequency.fileCode();
		}
		
		@Override
		public String toString() {
			return frequency.prettyPrint();
		}
		@Override
		public int midiNoteNumber() {
			return frequency.midiNoteNumber();
		}
	}
}
