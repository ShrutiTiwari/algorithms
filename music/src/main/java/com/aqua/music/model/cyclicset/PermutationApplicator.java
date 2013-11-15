package com.aqua.music.model.cyclicset;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.CyclicSequence;

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