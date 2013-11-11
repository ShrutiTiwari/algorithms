package com.aqua.music.audio.manager;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import com.aqua.music.model.Frequency;

public interface AudioLifeCycleManager {
	public final ExecutorService executor = AudioExecutor.executor;

	final AudioLifeCycleManager instance=new AudioLifeCycleManagerImpl();

	void awaitStop();

	boolean isPlayInProgress();

	void issueStop();

	void play(final Collection<Frequency> frequencyList, AudioPlayConfig audioModeAndPlayerCombinations);

	void execute(Runnable audioTask);
}
