package com.aqua.music.audio.player;


public interface AudioPlayUnit {
	void setCoordinator(AudioPlayCoordinator audioPlayCoordinator);

	void stop();

	Runnable playTask(final Object frequencyList);
}
