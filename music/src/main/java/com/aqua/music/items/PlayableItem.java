package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioLifeCycleManagers;
import com.aqua.music.audio.manager.DualModeManager.PlayMode;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public interface PlayableItem {
	AudioLifeCycleManager blockingPlayer = AudioLifeCycleManagers.FREQUENCY_BASED.player(PlayMode.Synchronous);

	AudioLifeCycleManager nonBlockingPlayer = AudioLifeCycleManagers.FREQUENCY_BASED.player(PlayMode.Asynchornous);

	public PlayableItem andPattern(PatternApplicator patternApplicator);

	public Collection<Frequency> frequencyList();

	public String play();

	static class Factory {
		public static PlayableItem forPlayerAndSet(AudioLifeCycleManager audioManager, FrequencySet frequencySet) {
			if (frequencySet instanceof SymmetricalSet) {
				return new SymmetricalPlayableItem(frequencySet, audioManager);
			}
			return new AsymmetricalPlayableItem(frequencySet, audioManager);
		}
	}
}
