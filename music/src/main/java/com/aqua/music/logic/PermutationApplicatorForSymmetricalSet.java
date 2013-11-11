package com.aqua.music.logic;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.Frequency;

class PermutationApplicatorForSymmetricalSet<T> implements PermutationApplicator<T> {
	private List<T> ascendSequence;
	private List<T> descendSequence;
	private final int[] pattern;
	private String patternName;

	PermutationApplicatorForSymmetricalSet(int[] pattern) {
		this.pattern = pattern;
		this.patternName = displayText();
	}

	public List<T> allNotes() {
		List<T> allNotes = new ArrayList<T>();
		allNotes.addAll(ascendSequence);
		allNotes.addAll(descendSequence);
		return allNotes;
	}

	public void initializeWith(T[] symmetricInput) {
		this.ascendSequence = sequenceForGiven(convertToList(symmetricInput));
		this.descendSequence = sequenceForGiven(reverse(symmetricInput));
	}

	@Override
	public String name() {
		return patternName;
	}

	public String prettyPrintTextForAscDesc() {
		return groupItemsForPrettyPrint(ascendSequence) + SEP + groupItemsForPrettyPrint(descendSequence) + SEP;
	}

	private List<T> convertToList(T[] symmetricInput) {
		List<T> inputList = new ArrayList<T>();
		for (T each : symmetricInput) {
			inputList.add(each);
		}
		return inputList;
	}

	private String groupItemsForPrettyPrint(List<T> noteSequence) {
		int numberItemsToBeGrouped = pattern.length;
		return insertComma(noteSequence, numberItemsToBeGrouped).toString();
	}

	private StringBuffer insertComma(List<T> itemSequence, int numberItemsToBeGrouped) {
		StringBuffer buffer = new StringBuffer();
		int processedItems = 0;
		int i = 1;
		for (T eachItem : itemSequence) {
			String appendText = eachItem instanceof Frequency ? ((Frequency) eachItem).prettyPrint() : eachItem.toString();

			if (i == numberItemsToBeGrouped) {
				i = 1;
				buffer.append(appendText + (processedItems != itemSequence.size() - 1 ? ", " : ""));
			} else {
				buffer.append(appendText);
				i++;
			}
			processedItems++;
		}
		return buffer;
	}

	private List<T> patternAt(List<T> input, int index) {
		List<T> result = new ArrayList<T>();
		try {
			int k = 0;
			for (int i : pattern) {
				T noteForPattern = input.get(index + (i - 1));
				result.add(noteForPattern);
			}
			return result;
		} catch (Exception ex) {
			return null;
		}
	}

	private String patternCode() {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		int i = 1;
		for (int each : pattern) {
			buf.append(each + ((i != pattern.length) ? "-" : ""));
			i++;
		}
		buf.append("]==>");
		return buf.toString();
	}

	private List<T> reverse(T[] inputData) {
		int dataLength = inputData.length;
		List<T> reverseData = new ArrayList<T>();
		for (int i = 0; i < dataLength; i++) {
			reverseData.add(inputData[(dataLength - 1) - i]);
		}
		return reverseData;
	}

	private List<T> sequenceForGiven(List<T> input) {
		List<T> result = new ArrayList<T>();
		for (int index = 0; index < input.size(); index++) {
			List<T> subResult = patternAt(input, index);
			if (subResult == null) {
				break;
			}
			for (T each1 : subResult) {
				result.add(each1);
			}
		}
		return result;
	}
	
	private String displayText() {
		String displayName = "" + pattern[0];
		int i = 0;
		for (int each : pattern) {
			if (i++ != 0) {
				displayName += ("-" + each);
			}
		}
		return displayName;
	}

}
