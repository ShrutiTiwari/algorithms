package com.aqua.music.model.raag.song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.core.DynamicFrequency.CustomFreqDuration;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.raag.MusicalPhrase;

/**
 * @author "Shruti Tiwari"
 *
 */
public class Taan {
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer lineSummary = new StringBuffer();

	Taan couple(Frequency... notes) {
		for (int i = 0; i < notes.length;) {
			Frequency note1 = notes[i++];
			frequencies.add(new CustomFreqDuration(note1, MusicalPhrase.COUPLE_NOTES_DURATION));
			lineSummary.append(note1.toString());
			if (i < notes.length) {
				Frequency note2 = notes[i++];
				frequencies.add(new CustomFreqDuration(note2, MusicalPhrase.COUPLE_NOTES_DURATION));
				lineSummary.append(note2.toString() + " ");
			}
		}
		return this;
	}

	Taan stress() {
		lineSummary.append("\t");
		return this;
	}

	public String printText() {
		return lineSummary.toString();
	}

	public Collection<? extends DynamicFrequency> frequencies() {
		return frequencies;
	}
}
