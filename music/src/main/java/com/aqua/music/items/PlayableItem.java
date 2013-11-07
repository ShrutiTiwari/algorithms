package com.aqua.music.items;

import java.util.Collection;

import com.aqua.music.audio.player.AudioPlayCoordinator;
import com.aqua.music.audio.player.AudioPlayer;
import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public interface PlayableItem {
	public PlayableItem andPattern(PatternApplicator patternApplicator);

	public void play();

	public Collection<Frequency> frequencyList();

	AudioPlayerConfiguration blocking = new AudioPlayerConfiguration();
	
	AudioPlayerConfiguration nonBlockingVlcPlayer = new AudioPlayerConfiguration(false, AudioPlayerType.VLC_BASED);

	AudioPlayerConfiguration blockingFrequencyPlayerConfig = new AudioPlayerConfiguration(true, AudioPlayerType.FREQUENCY_BASED);
	AudioPlayerConfiguration nonBlockingFrequencyPlayerConfig = new AudioPlayerConfiguration(false, AudioPlayerType.FREQUENCY_BASED);

	public static class AudioPlayerConfiguration {
		// blocking play is useful for programmatic or automatic play.
		private final AudioPlayCoordinator audioPlayer;
		
		public AudioPlayCoordinator audioPlayer(){
			return audioPlayer;
		}

		private AudioPlayerConfiguration() {
			this(true, AudioPlayerType.VLC_BASED);
		}

		private AudioPlayerConfiguration(boolean blocking, AudioPlayerType audioPlayerType) {
			this.audioPlayer = blocking? audioPlayerType.blockingPlayer():
	            audioPlayerType.nonBlockingPlayer();
		}

		public PlayableItem forSet(FrequencySet frequencySet) {
			if (frequencySet instanceof SymmetricalSet) {
				return new SymmetricalPlayableItem(frequencySet, audioPlayer);
			}
			return new AsymmetricalPlayableItem(frequencySet, audioPlayer);
		}
	}
}
