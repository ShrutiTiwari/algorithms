package com.aqua.music.api;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.raag.song.Song;

public enum PlaybleType {
	PLAIN_THAAT {
		@Override
		Collection<Playable> playables() {
			Collection<Playable> result = new ArrayList<Playable>();
			for (FrequencySet each : SymmetricalSet.values()) {
				CyclicFrequencySet symmetricalSet = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet((FrequencySet) each);
				result.add(symmetricalSet);
			}
			return result;
		}
	},
	SONG {
		@Override
		Collection<Playable> playables() {
			Collection<Playable> result = new ArrayList<Playable>();
			for (Song each : Song.values()) {
				result.add(each);
			}
			return result;
		}
	};
	abstract Collection<Playable> playables();
}
