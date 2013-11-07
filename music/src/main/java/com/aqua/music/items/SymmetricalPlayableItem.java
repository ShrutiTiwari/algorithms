package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.player.AudioPlayer;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public class SymmetricalPlayableItem implements PlayableItem {
	/**
	 * caution: this variable shouldn't be used until initialised, properly.
	 */
	private Collection<Frequency> finalFrequencySequence = null;

	private PatternApplicator patternApplicator = PatternApplicator.NONE;
	private final FrequencySet frequencySet;
	private final AudioPlayer audioPlayer;

	SymmetricalPlayableItem(FrequencySet frequencySet, AudioPlayer audioPlayer) {
		this.frequencySet = frequencySet;
		this.audioPlayer = audioPlayer;
	}

	public SymmetricalPlayableItem andPattern(PatternApplicator patternApplicator) {
		this.patternApplicator = patternApplicator;
		intializePlayList();
		return this;
	}

	public void play() {
		System.out.println(patternApplicator.prettyPrintTextForAscDesc());
		audioPlayer.play(this);
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
		System.out.print("\t[ Plain ascend-descend:: " + audioListBuilder.prettyPrintText() + "]");
		this.finalFrequencySequence = audioListBuilder.finalFrequencySequence();
	}

	@Override
	public Collection<Frequency> frequencyList() {
		intializePlayList();
		return finalFrequencySequence;
	}
}