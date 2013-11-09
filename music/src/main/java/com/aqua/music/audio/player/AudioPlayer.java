package com.aqua.music.audio.player;

import java.util.Collection;

import com.aqua.music.model.Frequency;

public interface AudioPlayer {
	void setCoordinator(AudioPlayCoordinator audioPlayCoordinator);

	void stop();

	Runnable playTask(final Collection<Frequency> frequencyList);
}
