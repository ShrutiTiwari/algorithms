package com.aqua.music.controller;

import static com.aqua.music.model.Frequency.ClassicalNote.SA3;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

class CyclicFrequencySetWithAsymmetry implements CyclicFrequencySet {
	private final AudioLifeCycleManager audioLifeCycleManager;
	private final Collection<Frequency> frequencies = new ArrayList<Frequency>();
	private final FrequencySet frequencySet;

	public CyclicFrequencySetWithAsymmetry(FrequencySet frequencySet, PermutationApplicator patternApplicator) {
		this.frequencySet = frequencySet;
		this.audioLifeCycleManager = AudioLifeCycleManager.instance;
		this.frequencies.addAll(new CyclicSequenceNonPermutating.WithMiddleNotesAndStartEndNotes(frequencySet.ascendNotes(), SA, SA3)
				.allFrequenciesInCycle());
		this.frequencies.addAll(new CyclicSequenceNonPermutating.WithMiddleNotesAndStartEndNotes(frequencySet.descendNotes(), SA3, SA)
				.allFrequenciesInCycle());
	}

	public Collection<Frequency> finalFrequencySequence() {
		return frequencies;
	}

	@Override
	public String play(AudioPlayConfig audioPlayConfig) {
		audioLifeCycleManager.play(this.frequencies, audioPlayConfig);
		return frequencySet.name();
	}

	@Override
	public String freqSetNamePermuationAsText() {
		return frequencySet.name();
	}

	@Override
	public String cycleFrequenciesAsText() {
		return "";
	}
}