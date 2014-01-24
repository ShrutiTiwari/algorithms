package com.aqua.music.model.cyclicset;

import static com.aqua.music.model.cyclicset.PermutationApplicator.NEW_LINE_SEP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import open.music.api.Playable;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface CyclicFrequencySet extends Playable{
	public String name();
	public String asText();
	

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

		public CyclicFrequencySet forFrequencySet(final FrequencySet freqSet, Frequency[] startEndPoints) {
			final Frequency[][] ascDesscNotes = freqSet.ascendDescendNotes(startEndPoints[0], startEndPoints[1]);
			FrequencySet customfrequencySet=new FrequencySet(){
				@Override
				public String name() {
					return freqSet.name();
				}

				@Override
				public String type() {
					return freqSet.type();
				}

				@Override
				public Frequency[] ascendNotes() {
					return ascDesscNotes[0];
				}

				@Override
				public Frequency[] descendNotes() {
					return ascDesscNotes[1];
				}

				@Override
				public Frequency[][] ascendDescendNotes(Frequency startClassicalNote, Frequency endClassicalNote) {
					return ascDesscNotes;
				}
			};
			
			return forFrequencySetAndPermutation(customfrequencySet, null);
		}
		
		public abstract CyclicFrequencySet forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation);
	}
	
	public enum PermuatationsGenerator {
		PAIR {
			@Override
			public List<int[]> generatePermutations(Frequency[] frequencySet) {
				List<int[]> patternsList = new ArrayList<int[]>();
				patternStartingOrEndingWith(frequencySet, patternsList, 1);
				return patternsList;
			}

			private void patternStartingOrEndingWith(Frequency[] frequencySet, List<int[]> patternsList, int startIndex) {
				int numberOfNotes = frequencySet.length;
				for (int index = 2; index <= numberOfNotes - 1; index++) {
					patternsList.add(new int[] { startIndex, index });
					patternsList.add(new int[] { index, startIndex });
				}
			}
		},
		TUPLE {
			@Override
			public List<int[]> generatePermutations(Frequency[] frequencySet) {
				List<int[]> patternsList = new ArrayList<int[]>();
				patternStartingOrEndingWith(frequencySet, patternsList, 1);
				return patternsList;
			}

			private void patternStartingOrEndingWith(Frequency[] frequencySet, List<int[]> patternsList, int startIndex) {
				int numberOfNotes = frequencySet.length;
				for (int index = 3; index <= numberOfNotes - 1; index++) {
					patternsList.add(new int[] { startIndex, index-1, index });
					patternsList.add(new int[] { startIndex, index, index-1 });
					patternsList.add(new int[] { index-1, startIndex, index });
					patternsList.add(new int[] { index-1, index, startIndex });
					patternsList.add(new int[] { index, startIndex, index-1 });
					patternsList.add(new int[] { index, index-1, startIndex });
				}
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