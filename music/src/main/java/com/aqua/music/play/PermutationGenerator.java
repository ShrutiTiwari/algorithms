package com.aqua.music.play;

import static com.aqua.music.play.Playable.BaseNotes.DHA;
import static com.aqua.music.play.Playable.BaseNotes.DHA_;
import static com.aqua.music.play.Playable.BaseNotes.GA;
import static com.aqua.music.play.Playable.BaseNotes.GA_;
import static com.aqua.music.play.Playable.BaseNotes.MA;
import static com.aqua.music.play.Playable.BaseNotes.MA_;
import static com.aqua.music.play.Playable.BaseNotes.NI;
import static com.aqua.music.play.Playable.BaseNotes.NI_;
import static com.aqua.music.play.Playable.BaseNotes.PA;
import static com.aqua.music.play.Playable.BaseNotes.RE;
import static com.aqua.music.play.Playable.BaseNotes.RE_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationGenerator<T> {
	private int[] pattern;
	private T[] input;

	public PermutationGenerator(int[] pattern, T[] input) {
		this.pattern = pattern;
		this.input = input;
	}

	void playAscendAndDescend() {
		List<T> inputList = new ArrayList<T>();
		for (T each : input) {
			inputList.add(each);
		}
		sequenceForGiven(inputList);
		sequenceForGiven(reverse(input));
	}

	List<T> sequenceForGiven(List<T> input) {
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
		printPattern(result);
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
    public enum AllThaat1 
    {
        BHAIRAV(RE_, GA, MA, PA, DHA_, NI),
        PURVI(RE_, GA, MA_, PA, DHA_, NI),
        MARWA(RE_, GA, MA_, PA, DHA, NI),
        KALYAN(RE, GA, MA_, PA, DHA, NI),
        BILAWAL(RE, GA, MA, PA, DHA, NI),
        KHAMAJ(RE, GA, MA, PA, DHA, NI_),
        KAFI(RE, GA_, MA, PA, DHA, NI_),
        ASAVARI(RE, GA_, MA, PA, DHA_, NI_),
        BHAIRAVI(RE_, GA_, MA, PA, DHA_, NI_),
        TODI(RE_, GA_, MA_, PA, DHA_, NI);
        private final Playable[] ascendNotes;
        
        private AllThaat1( Playable... ascendNotes ) {
        this.ascendNotes=ascendNotes;	
        }

		public Playable[] ascendNotes() {
			return ascendNotes;
		}
    }

}
