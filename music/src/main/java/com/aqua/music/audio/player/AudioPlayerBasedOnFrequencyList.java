package com.aqua.music.audio.player;

import java.io.File;

import static com.aqua.music.audio.player.FrequencyListPlayerBasedOnMathSinAngle.PlayMode;
import java.util.Collection;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.Frequency;
public class AudioPlayerBasedOnFrequencyList implements AudioPlayer {
	private final FrequencyListPlayerBasedOnMathSinAngle frequencyListPlayer;
	private final PlayMode playMode;

	AudioPlayerBasedOnFrequencyList() {
		this(true);
	}

	AudioPlayerBasedOnFrequencyList(boolean blockingPlay) {
		this(new FrequencyListPlayerBasedOnMathSinAngle(), blockingPlay);
	}

	AudioPlayerBasedOnFrequencyList(FrequencyListPlayerBasedOnMathSinAngle player, boolean blockingPlay) {
		this.playMode = blockingPlay ? PlayMode.Synchronous : PlayMode.Asynchornous;
		this.frequencyListPlayer = player;
		System.out.println("Configured player[" + playMode + "]");
	}

	public void play(final Collection<Frequency> frequencyList) {
		playMode.play(frequencyList, frequencyListPlayer);
	}

	@Override
	public void play(PlayableItem playableItem) {
		playMode.play(playableItem.frequencyList(), frequencyListPlayer);
	}

	@Override
	public void playList(Collection<File> audioFiles) {

	}
}