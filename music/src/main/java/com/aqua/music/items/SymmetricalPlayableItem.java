package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.player.AudioPlayCoordinator;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public class SymmetricalPlayableItem implements PlayableItem {
	private final AudioPlayCoordinator audioCooridnator;

	/**
	 * caution: this variable shouldn't be used until initialised, properly.
	 */
	private Collection<Frequency> finalFrequencySequence = null;
	private final FrequencySet frequencySet;
	private PatternApplicator patternApplicator = PatternApplicator.NONE;
	private String prettyText;

	public SymmetricalPlayableItem(FrequencySet frequencySet, AudioPlayCoordinator audioPlayer) {
		this.frequencySet = frequencySet;
		this.audioCooridnator = audioPlayer;
	}

	public SymmetricalPlayableItem andPattern(PatternApplicator patternApplicator) {
		this.patternApplicator = patternApplicator;
		intializePlayList();
		return this;
	}

	@Override
	public Collection<Frequency> frequencyList() {
		intializePlayList();
		return finalFrequencySequence;
	}

	public String play() {
		System.out.println(prettyText);
		audioCooridnator.play(this.frequencyList());
		return prettyText;
	}

	@Override
	public String text() {
		return prettyText;
	}

	private void intializePlayList() {
		if (finalFrequencySequence == null) {
			if (patternApplicator == PatternApplicator.NONE) {
				plainAscendDescend();
			} else {
				patternedAscendDescend();
			}
		}
	}

	private void patternedAscendDescend() {
		Frequency[] middleNotes = frequencySet.ascendNotes();
		Frequency[] input = new Frequency[middleNotes.length + 2];
		input[0] = Frequency.ClassicalNote.SA;
		input[input.length - 1] = Frequency.ClassicalNote.HIGH_SA;
		int i = 1;
		for (Frequency each : middleNotes) {
			input[i++] = each;
		}

		patternApplicator.initializeWith(input);

		this.finalFrequencySequence = patternApplicator.allNotes();
	}

	private void plainAscendDescend() {
		FrequencyListBuilder audioListBuilder = new FrequencyListBuilder.BuilderForSymmetricalSet(frequencySet);
		this.prettyText=audioListBuilder.prettyPrintText();
		System.out.print("\t Plain ascend-descend:: " + prettyText);
		this.finalFrequencySequence = audioListBuilder.finalFrequencySequence();
	}
}