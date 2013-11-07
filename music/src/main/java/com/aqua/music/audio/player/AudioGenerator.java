package com.aqua.music.audio.player;

import java.util.Collection;

import com.aqua.music.model.Frequency;

public interface AudioGenerator {
	void setCoordinator(AudioPlayCoordinator audioPlayCoordinator);

	void stop();

	void playFrequencies(Collection<Frequency> frequencyList);
}
