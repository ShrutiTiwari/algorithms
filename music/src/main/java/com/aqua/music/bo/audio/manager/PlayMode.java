package com.aqua.music.bo.audio.manager;

import java.util.Collection;

import com.aqua.music.model.core.DynamicFrequency;

enum PlayMode {
	Asynchronous {
		@Override
		public void play(DualModeManager dualModePlayer, Collection<? extends DynamicFrequency> frequencyList) {
			dualModePlayer.playAsynchronously(frequencyList);
		}
	},
	Synchronous {
		@Override
		public void play(DualModeManager dualModePlayer, Collection<? extends DynamicFrequency> frequencyList) {
			dualModePlayer.playSynchronously(frequencyList);
		}
	};
	abstract void play(DualModeManager dualModePlayer, final Collection<? extends DynamicFrequency> frequencyList);

	interface DualModeManager {
		public void playSynchronously(Collection<? extends DynamicFrequency> frequencyList);

		public void playAsynchronously(Collection<? extends DynamicFrequency> frequencyList);
	}
}