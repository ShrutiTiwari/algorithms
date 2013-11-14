package com.aqua.music.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.aqua.music.controller.CyclicFrequencySet.CyclicSequence;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.Frequency.ClassicalNote;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

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
			List<Frequency> ascend = CyclicSequence.frequencies(frequencySet.ascendNotes(), ClassicalNote.S, ClassicalNote.S3);
			List<Frequency> descend = CyclicSequence.frequencies(frequencySet.descendNotes(), ClassicalNote.S3, ClassicalNote.S);
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

	static class WithMiddleNotesAndStartEndNotes implements CyclicSequenceNonPermutating {
		private final List<Frequency> frequencyList;
		private String printText;

		WithMiddleNotesAndStartEndNotes(Frequency[] middleNotes, Frequency start, Frequency end) {
			this.frequencyList = CyclicSequence.frequencies(middleNotes, start, end);
			this.printText = CyclicSequence.groupItemsWithSep(frequencyList, 1);
		}

		public Collection<Frequency> allFrequenciesInCycle() {
			return frequencyList;
		}

		@Override
		public String asString() {
			return printText;
		}
	}
}
