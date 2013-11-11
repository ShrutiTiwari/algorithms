package com.aqua.music.logic;

import static com.aqua.music.model.Frequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

class FrequencySequenceWithAsymmetry implements FrequencySequence {
	private final AudioLifeCycleManager audioLifeCycleManager;
	private Collection<Frequency> frequencies = new ArrayList<Frequency>();

	private final FrequencySet frequencySet;

	public FrequencySequenceWithAsymmetry(FrequencySet frequencySet, PermutationApplicator patternApplicator) {
		this.frequencySet = frequencySet;
		this.audioLifeCycleManager= AudioLifeCycleManager.instance;
	}

	@Override
	public Collection<Frequency> finalFrequencySequence() {
		return frequencies;
	}

	@Override
	public String play(AudioPlayConfig audioPlayConfig) {
		createAudioList(SA, frequencySet.ascendNotes(), HIGH_SA);
		createAudioList(HIGH_SA, frequencySet.descendNotes(), SA);
		// AudioPlayer.BLOCKING_VLC_PLAYER.play( this );
		audioLifeCycleManager.play(this.finalFrequencySequence(), audioPlayConfig);
		return frequencySet.name();
	}

	private void createAudioList(Frequency start, Frequency[] middleNotes, Frequency end) {
		FrequencyListBuilder audioFileListBuilder = new FrequencyListBuilder.WithMiddleNotesAndStartEndNotes(middleNotes, start, end);
		this.frequencies.addAll(audioFileListBuilder.finalFrequencySequence());

	}
	@Override
	public String name() {
		return frequencySet.name();
	}
}