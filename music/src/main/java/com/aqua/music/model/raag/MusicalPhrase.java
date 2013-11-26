package com.aqua.music.model.raag;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.core.DynamicFrequency.CustomFreqDuration;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.MusicPeriod;

/**
 * @author "Shruti Tiwari"
 *
 */
public class MusicalPhrase {
	public static final int COUPLE_NOTES_DURATION = MusicPeriod.HALF_BEAT.durationInMilliSec();

	public static final int TOUCH_NOTE_DURATION = new MusicPeriod.CustomizedDuration(0.3).durationInMilliSec();
	public static final int TOUCH_MAIN_NOTE_DURATION = new MusicPeriod.CustomizedDuration(0.7).durationInMilliSec();

	private final int beatDivison;
	private int counter = 1;
	// private final String formatLength;
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer lineSummary = new StringBuffer();
	private StringBuffer quadrant = new StringBuffer();

	private final int repeatCount;

	public MusicalPhrase() {
		this(1, 1);
	}

	public MusicalPhrase(int beatDivison) {
		this(beatDivison, 1);
	}

	public MusicalPhrase(int beatDivison, int repeatCount) {
		this.beatDivison = beatDivison;
		this.repeatCount = repeatCount;
		// this.formatLength = "%-" + (beatDivison * 5) + "s";
	}

	public MusicalPhrase add(MusicalPhrase musicalPhrase) {
		for (DynamicFrequency eachFrequency : musicalPhrase.frequencies()) {
			frequencies.add(eachFrequency);
		}
		addToBuffer(musicalPhrase.printLine());
		return this;
	}

	public MusicalPhrase normalWithTouch(Frequency mainNote, Frequency... touchNote) {
		if (touchNote.length == 2) {
			frequencies.add(new CustomFreqDuration(touchNote[0], findDuration(mainNote, touchNote[0])));
			frequencies.add(new CustomFreqDuration(touchNote[1], findDuration(mainNote, touchNote[1])));
			addToBuffer(touchNote[0].toString() + touchNote[1].toString());
		}

		return this;
	}

	private int findDuration(Frequency mainNote, Frequency currentNote) {
		return mainNote == currentNote ? TOUCH_MAIN_NOTE_DURATION : TOUCH_NOTE_DURATION;
	}

	public MusicalPhrase couple(Frequency... notes) {
		int i = 0;
		while( i < notes.length) {
			Frequency note1 = notes[i++];
			Frequency note2 = notes[i++];
			frequencies.add(new CustomFreqDuration(note1, COUPLE_NOTES_DURATION));
			frequencies.add(new CustomFreqDuration(note2, COUPLE_NOTES_DURATION));
			String combinedNote = note1.toString().trim() + note2.toString().trim();
			addToBuffer(combinedNote);
		}
		return this;
	}

	public MusicalPhrase e(Frequency note, int numOfBeats) {
		frequencies.add(new CustomFreqDuration(note, (numOfBeats * MusicPeriod.SINGLE_BEAT.durationInMilliSec())));
		addToBuffer(note.toString());
		for (int i = 1; i < numOfBeats; i++) {
			addToBuffer("-");
		}
		return this;
	}

	public List<DynamicFrequency> frequencies() {
		return frequencies;
	}

	public MusicalPhrase n(DynamicFrequency... dynamicFrequenciess) {
		for (DynamicFrequency each : dynamicFrequenciess) {
			frequencies.add(each);
			addToBuffer(each.toString());
		}
		return this;
	}

	public String printLine() {
		if(quadrant.toString()==""){
			return lineSummary.toString();	
		}else{
			return lineSummary.append(quadrant).toString();
		}
		
		
	}

	public int repeatCount() {
		return repeatCount;
	}

	private void addToBuffer(String text) {
		quadrant.append(text + " ");
		if ((counter % beatDivison) == 0) {
			lineSummary.append(quadrant.toString() + String.format("%-3s", " | "));
			quadrant = new StringBuffer();
		}
		counter++;
	}
}
