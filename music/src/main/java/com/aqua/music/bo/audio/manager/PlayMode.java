package com.aqua.music.bo.audio.manager;

import java.util.Collection;

import com.aqua.music.model.Frequency;

enum PlayMode {
	Asynchronous {
		@Override
		public void play(DualModeManager dualModePlayer, Collection<Frequency> frequencyList) {
			dualModePlayer.playAsynchronously(frequencyList);
		}
	},
	Synchronous {
		@Override
		public void play(DualModeManager dualModePlayer, Collection<Frequency> frequencyList) {
			dualModePlayer.playSynchronously(frequencyList);
		}
	};
	abstract void play(DualModeManager dualModePlayer, final Collection<Frequency> frequencyList);

	interface DualModeManager {
		public void playSynchronously(Collection<Frequency> frequencyList);

		public void playAsynchronously(Collection<Frequency> frequencyList);
	}
}