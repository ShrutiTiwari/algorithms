package com.aqua.music.logic;

import java.util.Collection;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

class FrequencySequenceWithSymmetry implements FrequencySequence {
	private final AudioLifeCycleManager audioLifeCycleManager;

	/**
	 * caution: this variable shouldn't be used until initialised, properly.
	 */
	private Collection<Frequency> finalFrequencySequence = null;
	private final FrequencySet frequencySet;
	private PermutationApplicator permutationApplicator = PermutationApplicator.NONE;
	private String detailedSequenceText;

	public FrequencySequenceWithSymmetry(FrequencySet frequencySet, PermutationApplicator permutationApplicator) {
		this.frequencySet = frequencySet;
		this.audioLifeCycleManager = AudioLifeCycleManager.instance;
		if (permutationApplicator != null) {
			this.permutationApplicator = permutationApplicator;
		}
		intializePlayList();
	}

	@Override
	public String name() {
		return frequencySet.name() + ((permutationApplicator==PermutationApplicator.NONE)?"":" { " + permutationApplicator.name() + " }");
	}

	public String detailedSequenceText(){
		return detailedSequenceText; 
	}
	
	public String play(AudioPlayConfig audioPlayConfig) {
		System.out.println(detailedSequenceText);
		audioLifeCycleManager.play(this.finalFrequencySequence, audioPlayConfig);
		return detailedSequenceText;
	}

	private void intializePlayList() {
		if (finalFrequencySequence == null) {
			if (permutationApplicator == PermutationApplicator.NONE) {
				plainAscendDescend();
			} else {
				patternedAscendDescend();
			}
		}
	}

	private void patternedAscendDescend() {
		Frequency[] middleNotes = frequencySet.ascendNotes();
		Frequency[] input = new Frequency[middleNotes.length + 2];
		input[0] = Frequency.ClassicalNote.SA;
		input[input.length - 1] = Frequency.ClassicalNote.HIGH_SA;
		int i = 1;
		for (Frequency each : middleNotes) {
			input[i++] = each;
		}

		permutationApplicator.initializeWith(input);

		this.finalFrequencySequence = permutationApplicator.allNotes();
		this.detailedSequenceText = permutationApplicator.prettyPrintTextForAscDesc();
	}

	private void plainAscendDescend() {
		FrequencyListBuilder audioListBuilder = new FrequencyListBuilder.BuilderForSymmetricalSet(frequencySet);
		this.detailedSequenceText = audioListBuilder.prettyPrintText();
		System.out.print("\n\t Plain ascend-descend:: " + detailedSequenceText);
		this.finalFrequencySequence = audioListBuilder.finalFrequencySequence();
	}
}