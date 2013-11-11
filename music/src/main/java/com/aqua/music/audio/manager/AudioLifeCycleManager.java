package com.aqua.music.audio.manager;

import java.util.Collection;

import com.aqua.music.model.Frequency;

public interface AudioLifeCycleManager {
	void awaitStop();
	boolean isPlayInProgress();
	void issueStop();
	
	void play(final Collection<Frequency> frequencyList); 
}
