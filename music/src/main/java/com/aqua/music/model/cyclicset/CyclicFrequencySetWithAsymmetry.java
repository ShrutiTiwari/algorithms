package com.aqua.music.model.cyclicset;

import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
class CyclicFrequencySetWithAsymmetry implements CyclicFrequencySet {
	private final Collection<Frequency> frequencies = new ArrayList<Frequency>();
	private final FrequencySet frequencySet;

	public CyclicFrequencySetWithAsymmetry(FrequencySet frequencySet, PermutationApplicator patternApplicator) {
		this.frequencySet = frequencySet;
		this.frequencies.addAll(new CyclicSequenceNonPermutating.WithMiddleNotesAndStartEndNotes(frequencySet.ascendNotes(), S, S3)
				.allFrequenciesInCycle());
		this.frequencies.addAll(new CyclicSequenceNonPermutating.WithMiddleNotesAndStartEndNotes(frequencySet.descendNotes(), S3, S)
				.allFrequenciesInCycle());
	}

	public Collection<Frequency> finalFrequencySequence() {
		return frequencies;
	}

	
	public Collection<Frequency> frequencies() {
		return frequencies;
	}

	@Override
	public String name() {
		return frequencySet.name();
	}

	@Override
	public String asText() {
		return "";
	}

	@Override
	public void playInLoop(AudioPlayConfig audioPlayConfig) {
	}
}