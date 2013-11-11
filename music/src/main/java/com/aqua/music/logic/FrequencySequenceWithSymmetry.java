package com.aqua.music.logic;

import java.util.Collection;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

class FrequencySequenceWithSymmetry implements FrequencySequence {
	private final AudioLifeCycleManager audioLifeCycleManager;

	/**
	 * caution: this variable shouldn't be used until initialised, properly.
	 */
	private Collection<Frequency> finalFrequencySequence = null;
	private final FrequencySet frequencySet;
	private PermutationApplicator permutationApplicator = PermutationApplicator.NONE;
	private String prettyText;

	public FrequencySequenceWithSymmetry(FrequencySet frequencySet, PermutationApplicator permutationApplicator) {
		this.frequencySet = frequencySet;
		this.audioLifeCycleManager = AudioLifeCycleManager.instance;
		if (permutationApplicator != null) {
			this.permutationApplicator = permutationApplicator;
		}
		intializePlayList();
	}

	@Override
	public Collection<Frequency> finalFrequencySequence() {
		intializePlayList();
		return finalFrequencySequence;
	}

	@Override
	public String name() {
		return frequencySet.name() + ((permutationApplicator==PermutationApplicator.NONE)?"":" { " + permutationApplicator.name() + " }");
	}

	public String play(AudioPlayConfig audioPlayConfig) {
		System.out.println(prettyText);
		audioLifeCycleManager.play(this.finalFrequencySequence(), audioPlayConfig);
		return prettyText;
	}

	private void intializePlayList() {
		if (finalFrequencySequence == null) {
			if (permutationApplicator == PermutationApplicator.NONE) {
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

		permutationApplicator.initializeWith(input);

		this.finalFrequencySequence = permutationApplicator.allNotes();
		this.prettyText = permutationApplicator.prettyPrintTextForAscDesc();
	}

	private void plainAscendDescend() {
		FrequencyListBuilder audioListBuilder = new FrequencyListBuilder.BuilderForSymmetricalSet(frequencySet);
		this.prettyText = audioListBuilder.prettyPrintText();
		System.out.print("\n\t Plain ascend-descend:: " + prettyText);
		this.finalFrequencySequence = audioListBuilder.finalFrequencySequence();
	}
}