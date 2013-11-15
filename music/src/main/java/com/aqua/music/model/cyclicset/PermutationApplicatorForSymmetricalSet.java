package com.aqua.music.model.cyclicset;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.CyclicSequence;

class PermutationApplicatorForSymmetricalSet implements PermutationApplicator {
	private final int[] permutation;
	private final String permutationText;

	PermutationApplicatorForSymmetricalSet(int[] permutation) {
		this.permutation = permutation;
		this.permutationText = displayText();
	}

	public CyclicSequence initializeWith(Frequency[] symmetricInput) {
		List<Frequency> ascendSeq = sequenceForGiven(convertToList(symmetricInput),false);
		List<Frequency> descendSeq = sequenceForGiven(reverse(symmetricInput), true);
		return new CyclicSequence(ascendSeq, descendSeq, permutation.length);
	}

	@Override
	public String permutationText() {
		return permutationText;
	}

	private List<Frequency> convertToList(Frequency[] symmetricInput) {
		List<Frequency> inputList = new ArrayList<Frequency>();
		for (Frequency each : symmetricInput) {
			inputList.add(each);
		}
		return inputList;
	}

	private String displayText() {
		String displayName = "" + permutation[0];
		int i = 0;
		for (int each : permutation) {
			if (i++ != 0) {
				displayName += ("-" + each);
			}
		}
		return " {" + displayName + " }";
	}

	private List<Frequency> patternAt(List<Frequency> input, int index) {
		List<Frequency> result = new ArrayList<Frequency>();
		try {
			int k = 0;
			for (int i : permutation) {
				Frequency noteForPattern = input.get(index + (i - 1));
				result.add(noteForPattern);
			}
			return result;
		} catch (Exception ex) {
			return null;
		}
	}

	private List<Frequency> reverse(Frequency[] inputData) {
		int dataLength = inputData.length;
		List<Frequency> reverseData = new ArrayList<Frequency>();
		for (int i = 0; i < dataLength; i++) {
			reverseData.add(inputData[(dataLength - 1) - i]);
		}

		if (permutation != null & permutation.length > 1) {
			int extraRange = extraRange(permutation);
			if (extraRange < 0) {
				int abs = Math.abs(extraRange);
				for (int j = 1; abs != 0; j++, abs--) {
					reverseData.add(Frequency.freqRel.lower((ClassicalNote) reverseData.get(j)));
				}
			}
		}
		return reverseData;
	}

	private int extraRange(int[] permutation) {
		int minIndex = 0;
		int maxIndex = 0;
		for (int index=0; index<permutation.length;index++) {
			if(permutation[maxIndex]<permutation[index]){
				maxIndex=index;
			}
			if(permutation[minIndex]>permutation[index]){
				minIndex=index;
			}					
		}
		int diff = permutation[maxIndex] - permutation[minIndex];
		return (minIndex <maxIndex)? diff: -diff;
	}

	private List<Frequency> sequenceForGiven(List<Frequency> input, boolean descend) {
		List<Frequency> result = new ArrayList<Frequency>();
		for (int index = 0; index < input.size(); index++) {
			List<Frequency> subResult = patternAt(input, index);
			if (subResult == null) {
				break;
			}
			for (Frequency each1 : subResult) {
				result.add(each1);
			}
			if(descend && lastIndexIsSa(subResult)){
				break;
			}
		}
		return result;
	}

	private boolean lastIndexIsSa(List<Frequency> subResult) {
		return subResult.get(subResult.size()-1) == ClassicalNote.S;
	}
}
