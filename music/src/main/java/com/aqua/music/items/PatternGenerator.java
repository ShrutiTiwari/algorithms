package com.aqua.music.items;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.model.Frequency;

public enum PatternGenerator {
	PAIR {
		@Override
		public List<int[]> generatePatterns(Frequency[] frequencySet) {
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
	public abstract List<int[]> generatePatterns(Frequency[] input);

}
