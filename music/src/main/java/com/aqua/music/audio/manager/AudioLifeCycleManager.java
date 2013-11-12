package com.aqua.music.audio.manager;

import java.util.Collection;

import com.aqua.music.model.Frequency;

public interface AudioLifeCycleManager {
	final AudioLifeCycleManager instance = new AudioLifeCycleManagerImpl();

	void play(final Collection<Frequency> frequencyList, AudioPlayConfig audioModeAndPlayerCombinations);

	<T> void execute(AudioTask<T> audioTask);
}
