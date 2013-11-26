package com.aqua.music.model.core;


/**
 * @author "Shruti Tiwari"
 *
 */
public interface FrequencySet {
	public String name();

	public String type();

	public Frequency[] ascendNotes();

	public Frequency[] descendNotes();

	static abstract class Util {
		public static Frequency[] reverse(Frequency... ascendNotes) {
			int count = ascendNotes.length;
			Frequency[] dscendNotes = new Frequency[count];
			for (int i = 0; i < count; i++) {
				dscendNotes[i] = ascendNotes[count - i - 1];
			}
			return dscendNotes;
		}
	}
}