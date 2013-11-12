package com.aqua.music.logic;

import com.aqua.music.logic.CyclicFrequencySet.CyclicSequence;
import com.aqua.music.model.Frequency;

interface PermutationApplicator {
	static final String NEW_LINE_SEP ="\n";

	PermutationApplicator NONE = new PermutationApplicator() {
		@Override
		public CyclicSequence initializeWith(Frequency[] commonAscDescInput) {
			return CyclicSequence.NONE;
		}

		@Override
		public String permutationText() {
			return "";
		}
	};

	CyclicSequence initializeWith(Frequency[] commonAscDescInput);

	String permutationText();
}