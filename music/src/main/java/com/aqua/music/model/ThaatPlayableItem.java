package com.aqua.music.model;

import java.io.File;
import java.util.Collection;

import com.aqua.music.model.PredefinedFrequencySet.Thaat;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public class ThaatPlayableItem implements PlayableItem {
	private int duration = 1;
	private PatternApplier patternApplier = PatternApplier.NONE;

	private Thaat thaat;

	public static ThaatPlayableItem forThaat(Thaat thaat) {
		ThaatPlayableItem result = new ThaatPlayableItem();
		result.thaat = thaat;
		return result;
	}

	public Collection<File> getPlayList() {
		AudioLibrary.initializeWithGivenSeconds(duration);
		if (patternApplier == PatternApplier.NONE) {
			AudioFileListMaker audioFilesEnqueuer = new AudioFileListMaker.ThaatEnqueueListMaker(thaat);
			System.out.print("\t[" + audioFilesEnqueuer.printableAudios() + "]");
			return audioFilesEnqueuer.collectedAudioFiles();
		}

		PredefinedFrequency[] middleNotes = thaat.ascendNotes();
		PredefinedFrequency[] input = new PredefinedFrequency[middleNotes.length + 2];
		input[0] = PredefinedFrequency.FundamentalNote.SA;
		input[input.length - 1] = PredefinedFrequency.FundamentalNote.HIGH_SA;
		int i = 1;
		for (PredefinedFrequency each : middleNotes) {
			input[i++] = each;
		}

		patternApplier.generateAscendAndDescendSequences(input);
		AudioFileListMaker audioFilesEnqueuer = new AudioFileListMaker.SimpleListMaker(patternApplier.allNotes());
		patternApplier.printAscDescPattern();
		return audioFilesEnqueuer.collectedAudioFiles();

	}

	ThaatPlayableItem andPattern(PatternApplier pattern) {
		this.patternApplier = pattern;
		return this;
	}

	ThaatPlayableItem forDuration(int duration) {
		this.duration = duration;
		return this;
	}
}
