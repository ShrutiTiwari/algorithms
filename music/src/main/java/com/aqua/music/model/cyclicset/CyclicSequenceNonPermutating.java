package com.aqua.music.model.cyclicset;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.CyclicSequence;

interface CyclicSequenceNonPermutating {
	Collection<Frequency> allFrequenciesInCycle();

	String asString();

	static class MultipleSymmetricalFreqSets implements CyclicSequenceNonPermutating {
		final Collection<File> collectedAudioFile = new ArrayList<File>();
		final Collection<Frequency> allFrequencies = new ArrayList<Frequency>();
		final StringBuffer printText = new StringBuffer();

		MultipleSymmetricalFreqSets(SymmetricalSet[] multipleSets) {
			for (SymmetricalSet each : multipleSets) {
				processEach(each);
			}
		}

		public Collection<Frequency> allFrequenciesInCycle() {
			return allFrequencies;
		}

		public String asString() {
			return printText.toString();
		}

		private void processEach(SymmetricalSet set) {
			CyclicSequence listMaker = new SymmetricalFreqSet(set).cyclicSeq;
			allFrequencies.addAll(listMaker.allFrequencies());
			printText.append("\n" + listMaker.asString());
		}
	}

	static class SymmetricalFreqSet implements CyclicSequenceNonPermutating {
		private CyclicSequence cyclicSeq;

		SymmetricalFreqSet(FrequencySet frequencySet) {
			List<Frequency> ascend = Arrays.asList(frequencySet.ascendNotes());
			List<Frequency> descend = Arrays.asList(frequencySet.descendNotes());
			this.cyclicSeq = new CyclicSequence(ascend, descend, 1);
		}

		public Collection<Frequency> allFrequenciesInCycle() {
			return cyclicSeq.allFrequencies();
		}

		public String asString() {
			return cyclicSeq.asString();
		}

		public CyclicSequence cyclicSequence() {
			return cyclicSeq;
		}
	}
}
