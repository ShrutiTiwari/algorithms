package com.aqua.music.items;

import static com.aqua.music.model.Frequency.ClassicalNote.HIGH_SA;
import static com.aqua.music.model.Frequency.ClassicalNote.SA;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.audio.player.AudioPlayCoordinator;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

public class AsymmetricalPlayableItem implements PlayableItem {
	private final FrequencySet frequencySet;
	private Collection<Frequency> frequencies = new ArrayList<Frequency>();

	private final AudioPlayCoordinator audioPlayer;

	AsymmetricalPlayableItem(FrequencySet frequencySet, AudioPlayCoordinator audioPlayer) {
		this.frequencySet = frequencySet;
		this.audioPlayer = audioPlayer;
	}

	@Override
	public void play() {
		createAudioList(SA, frequencySet.ascendNotes(), HIGH_SA);
		createAudioList(HIGH_SA, frequencySet.descendNotes(), SA);
		// AudioPlayer.BLOCKING_VLC_PLAYER.play( this );
		audioPlayer.play(this.frequencyList());
	}

	private void createAudioList(Frequency start, Frequency[] middleNotes, Frequency end) {
		FrequencyListBuilder audioFileListBuilder = new FrequencyListBuilder.WithMiddleNotesAndStartEndNotes(middleNotes, start, end);
		this.frequencies.addAll(audioFileListBuilder.finalFrequencySequence());

	}

	@Override
	public PlayableItem andPattern(PatternApplicator patternApplicator) {
		return this;
	}

	@Override
	public Collection<Frequency> frequencyList() {
		return frequencies;
	}
}