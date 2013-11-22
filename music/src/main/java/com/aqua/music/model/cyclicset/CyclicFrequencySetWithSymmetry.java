package com.aqua.music.model.cyclicset;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.model.core.DynamicFrequency;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;

class CyclicFrequencySetWithSymmetry implements CyclicFrequencySet {
	Logger logger = LoggerFactory.getLogger(CyclicFrequencySetWithSymmetry.class);
	private final Collection<Frequency> allFrequencies;
	private final String cycleFrequenciesAsText;
	private final String name;

	public CyclicFrequencySetWithSymmetry(FrequencySet frequencySet, PermutationApplicator permutationApplicator) {
		CyclicSequence cyclicSequence = null;
		if (permutationApplicator == null || permutationApplicator == PermutationApplicator.NONE) {
			cyclicSequence = new CyclicSequenceNonPermutating.SymmetricalFreqSet(frequencySet).cyclicSequence();
		} else {
			cyclicSequence = permutationApplicator.initializeWith(frequencySet.ascendNotes());
		}

		this.cycleFrequenciesAsText = cyclicSequence.asString();
		this.allFrequencies = cyclicSequence.allFrequencies();
		this.name = frequencySet.name() + (permutationApplicator == null ? "" : permutationApplicator.permutationText());
	}

	public String asText() {
		return cycleFrequenciesAsText;
	}
	@Override
	public Collection<? extends DynamicFrequency> frequencies() {
		return allFrequencies;
	}
	@Override
	public String name() {
		return name;
	}

	@Override
	public void playInLoop(AudioPlayerSettings audioPlayConfig) {
	}

	@Override
	public String toString() {
		return name();
	}
}