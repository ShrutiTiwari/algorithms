package com.aqua.music.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sound.midi.Instrument;

import com.aqua.music.bo.audio.player.MidiWorld;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;

public class PlayApi {
	private static final Collection<Playable> playableSongs = PlaybleType.SONG.playables();
	private static final Instrument[] instruments = new Instrument[1];//MidiWorld.getInstruments();
	private static final Collection<Playable> playablePlainThaats = PlaybleType.PLAIN_THAAT.playables();

	public static Collection<Playable> getAllSongs() {
		return playableSongs;
	}

	public static Collection<Playable> getAllPlainThaat() {
		return playablePlainThaats;
	}

	public static Collection<Playable> getAllPatternedThaat(FrequencySet frequencySet, int bucketSize) {
		List<int[]> allPermutations = PermuatationsGenerator.PAIR.generatePermutations(frequencySet.ascendNotes());

		Collection<Playable> result = new ArrayList<Playable>();
		for (int[] eachPermutation : allPermutations) {
			CyclicFrequencySet playbleItem = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
					eachPermutation);
			result.add(playbleItem);
		}
		return result;
	}

	public static Instrument[] getAllInstruments() {
		return instruments;
	}
}
