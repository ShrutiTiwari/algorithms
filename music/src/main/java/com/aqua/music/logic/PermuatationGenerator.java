package com.aqua.music.logic;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.Frequency;

public enum PermuatationGenerator {
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
