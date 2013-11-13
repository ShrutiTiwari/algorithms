package com.aqua.music.controller;

import static com.aqua.music.controller.PermutationApplicator.NEW_LINE_SEP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public interface CyclicFrequencySet {
	public String freqSetNamePermuationAsText();
	public String cycleFrequenciesAsText();
	public String play(AudioPlayConfig audioPlayConfig);

	enum Type {
		ASSYMETRICAL {
			@Override
			public CyclicFrequencySet forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation) {
				PermutationApplicator freqPattern = (permutation == null ? PermutationApplicator.NONE
						: new PermutationApplicatorForSymmetricalSet(permutation));
				return new CyclicFrequencySetWithAsymmetry(freqSet, freqPattern);
			}
		},
		SYMMETRICAL {
			@Override
			public CyclicFrequencySet forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation) {
				PermutationApplicator freqPattern = (permutation == null ? PermutationApplicator.NONE
						: new PermutationApplicatorForSymmetricalSet(permutation));
				return new CyclicFrequencySetWithSymmetry(freqSet, freqPattern);
			}
		};

		public CyclicFrequencySet forFrequencySet(FrequencySet freqSet) {
			return forFrequencySetAndPermutation(freqSet, null);
		}

		public abstract CyclicFrequencySet forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation);
	}
	
	public enum PermuatationsGenerator {
		PAIR {
			@Override
			public List<int[]> generatePermutations(Frequency[] frequencySet) {
				List<int[]> patternsList = new ArrayList<int[]>();

				int startIndex = 1;

				int numberOfNotes = frequencySet.length;
				for (int index = 2; index <= numberOfNotes + 1; index++) {
					patternsList.add(new int[] { startIndex, index });
					patternsList.add(new int[] { index, startIndex });
				}

				return patternsList;
			}
		};
		public abstract List<int[]> generatePermutations(Frequency[] input);
	}

	class CyclicSequence {
		public static final String GROUP_SEP = "\t";
		static final CyclicSequence NONE = new CyclicSequence();
		private final List<Frequency> ascend;
		private final List<Frequency> descend;
		private final String cycleAsString;

		public CyclicSequence(List<Frequency> ascendSequence, List<Frequency> descendSequence, int groupSize) {
			this.ascend = ascendSequence;
			this.descend = descendSequence;
			this.cycleAsString = groupItemsWithSep(ascendSequence, groupSize) + NEW_LINE_SEP + groupItemsWithSep(descendSequence, groupSize)
					+ NEW_LINE_SEP;
		}

		private CyclicSequence() {
			this.ascend = Collections.EMPTY_LIST;
			this.descend = Collections.EMPTY_LIST;
			this.cycleAsString = "";
		}

		public List<Frequency> allFrequencies() {
			List<Frequency> allFrequencies = new ArrayList<Frequency>();
			allFrequencies.addAll(ascend);
			allFrequencies.addAll(descend);
			return allFrequencies;
		}

		public String asString() {
			return cycleAsString;
		}

		static String groupItemsWithSep(List<Frequency> freqSequence, int groupSize) {
			StringBuffer buffer = new StringBuffer();
			int processedItems = 0;
			int i = 1;
			for (Frequency eachFrequency : freqSequence) {
				String appendText = eachFrequency.prettyPrint();

				if (i == groupSize) {
					i = 1;
					buffer.append(appendText + (processedItems != freqSequence.size() - 1 ? GROUP_SEP : ""));
				} else {
					buffer.append(appendText);
					i++;
				}
				processedItems++;
			}
			return buffer.toString();
		}
		static List<Frequency> frequencies(Frequency[] middleNotes, Frequency start, Frequency end) {
			List<Frequency> frequencyList = new ArrayList<Frequency>();
			frequencyList.add(start);
			frequencyList.addAll(Arrays.asList(middleNotes));
			frequencyList.add(end);
			return frequencyList;
		}
	}
}