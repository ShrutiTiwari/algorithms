package com.aqua.music.audio.player;

import java.io.File;
import java.util.Collection;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.Frequency;
public class AudioPlayerBasedOnFrequencyList implements AudioPlayer {
	private final AudioPlayCoordinator audioPlayCoordinator;

	AudioPlayerBasedOnFrequencyList() {
		this(true);
	}

	AudioPlayerBasedOnFrequencyList(boolean blockingPlay) {
		this(new AudioPlayCoordinator(blockingPlay));
	}

	AudioPlayerBasedOnFrequencyList(AudioPlayCoordinator audioPlayCoordinator) {
		this.audioPlayCoordinator = audioPlayCoordinator;
	}

	public void play(final Collection<Frequency> frequencyList) {
		audioPlayCoordinator.play(frequencyList);
	}

	@Override
	public void play(PlayableItem playableItem) {
		audioPlayCoordinator.play(playableItem.frequencyList());
	}

	@Override
	public void playList(Collection<File> audioFiles) {

	}
}