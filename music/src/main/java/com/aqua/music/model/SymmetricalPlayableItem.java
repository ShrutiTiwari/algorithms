package com.aqua.music.model;

import java.io.File;
import java.util.Collection;

import com.aqua.music.model.PredefinedFrequencySet.SymmetricalSet;
import com.aqua.music.play.AudioFileListMaker;
import com.aqua.music.play.AudioLibrary;

public class SymmetricalPlayableItem implements PlayableItem {
	private int duration = 1;
	private PatternApplier patternApplier = PatternApplier.NONE;

	private SymmetricalSet symmetricalSet;

	public static SymmetricalPlayableItem forSet(SymmetricalSet symmetricalSet) {
		SymmetricalPlayableItem result = new SymmetricalPlayableItem();
		result.symmetricalSet = symmetricalSet;
		return result;
	}

	public Collection<File> getPlayList() {
		AudioLibrary.initializeWithGivenSeconds(duration);
		if (patternApplier == PatternApplier.NONE) {
			AudioFileListMaker audioFilesEnqueuer = new AudioFileListMaker.SymmetricalSetEnqueueListMaker(symmetricalSet);
			System.out.print("\t[" + audioFilesEnqueuer.printableAudios() + "]");
			return audioFilesEnqueuer.collectedAudioFiles();
		}

		FundamentalFrequency[] middleNotes = symmetricalSet.ascendNotes();
		FundamentalFrequency[] input = new FundamentalFrequency[middleNotes.length + 2];
		input[0] = FundamentalFrequency.ClassicalNote.SA;
		input[input.length - 1] = FundamentalFrequency.ClassicalNote.HIGH_SA;
		int i = 1;
		for (FundamentalFrequency each : middleNotes) {
			input[i++] = each;
		}

		patternApplier.generateAscendAndDescendSequences(input);
		AudioFileListMaker audioFilesEnqueuer = new AudioFileListMaker.SimpleListMaker(patternApplier.allNotes());
		patternApplier.printAscDescPattern();
		return audioFilesEnqueuer.collectedAudioFiles();

	}

	SymmetricalPlayableItem andPattern(PatternApplier pattern) {
		this.patternApplier = pattern;
		return this;
	}

	SymmetricalPlayableItem forDuration(int duration) {
		this.duration = duration;
		return this;
	}
}
