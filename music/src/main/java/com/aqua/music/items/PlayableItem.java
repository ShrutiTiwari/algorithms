package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.player.AudioLifeCycleManager;
import com.aqua.music.audio.player.StandardAudioLifeCycleManagers;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public interface PlayableItem {
	AudioLifeCycleManager blockingPlayer = StandardAudioLifeCycleManagers.FREQUENCY_BASED.player(true);

	AudioLifeCycleManager nonBlockingPlayer = StandardAudioLifeCycleManagers.FREQUENCY_BASED.player(false);

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
