package com.aqua.music.play;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator<T> {
	private int[] pattern;
	private T[] input;
	private List<T> ascendSequence;
	private List<T> descendSequence;
	
	public PermutationGenerator(int[] pattern, T[] input) {
		this.pattern = pattern;
		this.input = input;
	}

	void generateAscendAndDescendSequences() {
		List<T> inputList = new ArrayList<T>();
		for (T each : input) {
			inputList.add(each);
		}
		this.ascendSequence = sequenceForGiven(inputList);
		this.descendSequence = sequenceForGiven(reverse(input));
	}
	
	List<T> ascendSequence(){
	    return ascendSequence;
	}
	
	List<T> descendSequence(){
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
			for (int i : pattern) {
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
			if (i == pattern.length) {
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
