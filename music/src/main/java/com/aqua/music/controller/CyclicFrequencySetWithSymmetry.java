package com.aqua.music.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.bo.audio.manager.AudioLifeCycleManager;
import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.Frequency.ClassicalNote;
import com.aqua.music.model.FrequencySet;

class CyclicFrequencySetWithSymmetry implements CyclicFrequencySet {
	Logger logger = LoggerFactory.getLogger(CyclicFrequencySetWithSymmetry.class);
	private final AudioLifeCycleManager audioLifeCycleManager;
	private final String cycleFrequenciesAsText;
	private final Collection<Frequency> allFrequencies;
	private final String name;

	public CyclicFrequencySetWithSymmetry(FrequencySet frequencySet, PermutationApplicator permutationApplicator) {
		this.audioLifeCycleManager = AudioLifeCycleManager.instance;

		CyclicSequence cyclicSequence = null;
		if (permutationApplicator == null || permutationApplicator == PermutationApplicator.NONE) {
			cyclicSequence = new CyclicSequenceNonPermutating.SymmetricalFreqSet(frequencySet).cyclicSequence();
		} else {
			List<Frequency> s = CyclicSequence.frequencies(frequencySet.ascendNotes(), ClassicalNote.SA, ClassicalNote.SA3);
			cyclicSequence = permutationApplicator.initializeWith(s.toArray(new Frequency[s.size()]));
		}

		this.cycleFrequenciesAsText = cyclicSequence.asString();
		this.allFrequencies = cyclicSequence.allFrequencies();
		this.name = frequencySet.name() + (permutationApplicator == null ? "" : permutationApplicator.permutationText());
	}

	public String cycleFrequenciesAsText() {
		return cycleFrequenciesAsText;
	}

	@Override
	public String freqSetNamePermuationAsText() {
		return name;
	}

	public String play(AudioPlayConfig audioPlayConfig) {
		audioLifeCycleManager.play(allFrequencies, audioPlayConfig);
		return cycleFrequenciesAsText;
	}
}