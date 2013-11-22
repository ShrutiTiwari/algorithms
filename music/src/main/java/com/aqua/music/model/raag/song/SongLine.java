package com.aqua.music.model.raag.song;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.core.DynamicFrequency.CustomFreqDuration;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.MusicPeriod;

class SongLine {
	static final int COUPLE_NOTES_DURATION = MusicPeriod.HALF_BEAT.durationInMilliSec();

	private final int beatDivison;
	private final String formatLength;
	private final List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();
	private final StringBuffer lineSummary = new StringBuffer();
	
	private int counter = 1;
	private StringBuffer quadrant = new StringBuffer();

	private final int repeatCount;

	
	int repeatCount(){
		return repeatCount;
	}
	
	public SongLine(int beatDivison, int repeatCount){
		this.beatDivison=beatDivison;
		this.repeatCount=repeatCount;
		this.formatLength = "%-" + (beatDivison * 5)  + "s";
	}
	
	public SongLine(int beatDivison) {
		this(beatDivison,1);
	}

	void addToBuffer(String text) {
		quadrant.append(text + " ");
		if ((counter % beatDivison) == 0) {
			lineSummary.append(String.format(formatLength, quadrant.toString()));
			quadrant=new StringBuffer();
		}
		counter++;
	}

	SongLine couple(Frequency... notes) {
		for (int i = 0; i < notes.length;) {
			Frequency note1 = notes[i++];
			Frequency note2 = notes[i++];
			frequencies.add(new CustomFreqDuration(note1, COUPLE_NOTES_DURATION));
			frequencies.add(new CustomFreqDuration(note2, COUPLE_NOTES_DURATION));
			addToBuffer(note1.toString() + note2.toString());
		}
		return this;
	}

	SongLine extended(Frequency note, int numOfBeats) {
		frequencies.add(new CustomFreqDuration(note, (numOfBeats * MusicPeriod.SINGLE_BEAT.durationInMilliSec())));
		for (int i = 0; i < numOfBeats; i++) {
			addToBuffer(note.toString());
		}
		return this;
	}

	List<DynamicFrequency> frequencies() {
		return frequencies;
	}

	SongLine normal(DynamicFrequency... dynamicFrequenciess) {
		for (DynamicFrequency each : dynamicFrequenciess) {
			frequencies.add(each);
			addToBuffer(each.toString());
		}
		return this;
	}

	
	SongLine add(SongLine songLine) {
		for (DynamicFrequency each : songLine.frequencies()) {
			frequencies.add(each);
			addToBuffer(each.toString());
		}
		return this;
	}

	
	String printLine() {
		return lineSummary.toString();
	}
	
	class Phrase {
		
	}
}
