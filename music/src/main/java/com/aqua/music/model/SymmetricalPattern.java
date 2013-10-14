package com.aqua.music.model;

import java.util.ArrayList;
import java.util.List;

public class SymmetricalPattern<T> implements PatternApplier<T> {
	private final int[] commonAscDescPattern;
	private List<T> ascendSequence;
	private List<T> descendSequence;

	public SymmetricalPattern(int[] commonAscDescPattern) {
		this.commonAscDescPattern = commonAscDescPattern;
	}

	public void generateAscendAndDescendSequences(T[] commonAscDescInput) {
		List<T> inputList = new ArrayList<T>();
		for (T each : commonAscDescInput) {
			inputList.add(each);
		}
		this.ascendSequence = sequenceForGiven(inputList);
		this.descendSequence = sequenceForGiven(reverse(commonAscDescInput));
	}

	
	public List<T> allNotes() {
		List<T> allNotes = new ArrayList<T>();
		allNotes.addAll(ascendSequence);
		allNotes.addAll(descendSequence);
		return allNotes;
	}

	public void printAscDescPattern(){
		printPattern(ascendSequence);
		printPattern(descendSequence);
	}
	
	public List<T> ascendSequence() {
		return ascendSequence;
	}

	public List<T> descendSequence() {
		return descendSequence;
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

	private List<T> patternAt(List<T> input, int index) {
		List<T> result = new ArrayList<T>();
		try {
			int k = 0;
			for (int i : commonAscDescPattern) {
				T noteForPattern = input.get(index + (i - 1));
				result.add(noteForPattern);
			}
			return result;
		} catch (Exception ex) {
			return null;
		}
	}

	void printPattern(List<T> result) {
		StringBuffer buffer = new StringBuffer();
		int i = 1;
		for (T each : result) {
			if (i == commonAscDescPattern.length) {
				i = 1;
				buffer.append(each + ", ");
			} else {
				buffer.append(each);
				i++;
			}
		}
		System.out.println(buffer.toString());
	}

	private List<T> reverse(T[] inputData) {
		int dataLength = inputData.length;
		List<T> reverseData = new ArrayList<T>();
		for (int i = 0; i < dataLength; i++) {
			reverseData.add(inputData[(dataLength - 1) - i]);
		}
		return reverseData;
	}
}
