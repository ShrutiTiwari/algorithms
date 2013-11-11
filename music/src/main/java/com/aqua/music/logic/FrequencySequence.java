package com.aqua.music.logic;

import java.util.Collection;

import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public interface FrequencySequence {
	public Collection<Frequency> finalFrequencySequence();

	public String name();

	public String play(AudioPlayConfig audioPlayConfig);

	enum Type {
		ASSYMETRICAL {
			@Override
			public FrequencySequence forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation) {
				PermutationApplicator freqPattern = (permutation == null ? PermutationApplicator.NONE
						: new PermutationApplicatorForSymmetricalSet<Frequency>(permutation));
				return new FrequencySequenceWithAsymmetry(freqSet, freqPattern);
			}
		},
		SYMMETRICAL {
			@Override
			public FrequencySequence forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation) {
				PermutationApplicator freqPattern = (permutation == null ? PermutationApplicator.NONE
						: new PermutationApplicatorForSymmetricalSet<Frequency>(permutation));
				return new FrequencySequenceWithSymmetry(freqSet, freqPattern);
			}
		};

		public FrequencySequence forFrequencySet(FrequencySet freqSet) {
			return forFrequencySetAndPermutation(freqSet, null);
		}

		public abstract FrequencySequence forFrequencySetAndPermutation(FrequencySet freqSet, int[] permutation);
	}
}
