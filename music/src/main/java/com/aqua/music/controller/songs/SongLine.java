package com.aqua.music.controller.songs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aqua.music.model.DynamicFrequency;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.MusicPeriod;
import com.aqua.music.model.DynamicFrequency.CustomFreqDuration;

class SongLine {
	private static final int COUPLE_NOTES_DURATION = MusicPeriod.HALF_BEAT.durationInMilliSec();

	List<DynamicFrequency> frequencies = new ArrayList<DynamicFrequency>();

	SongLine() {
	}

	SongLine(DynamicFrequency... dynamicFrequenciess) {
		frequencies.addAll(Arrays.asList(dynamicFrequenciess));
	}

	void addMoreFrquencies(DynamicFrequency... dynamicFrequenciess) {
		frequencies.addAll(Arrays.asList(dynamicFrequenciess));
	}

	void addCoupleNotes(Frequency note1, Frequency note2) {
		frequencies.add(new CustomFreqDuration(note1, COUPLE_NOTES_DURATION));
		frequencies.add(new CustomFreqDuration(note2, COUPLE_NOTES_DURATION));
	}

	void addExtendedNotes(Frequency note1, int numOfBeats) {
		frequencies.add(new CustomFreqDuration(note1, (numOfBeats * MusicPeriod.SINGLE_BEAT.durationInMilliSec())));
	}
}
