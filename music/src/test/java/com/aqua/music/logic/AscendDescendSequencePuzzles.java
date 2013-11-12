package com.aqua.music.logic;

import java.util.HashSet;

import com.aqua.music.audio.manager.AudioLifeCycleManager;
import com.aqua.music.audio.manager.AudioPlayConfig;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

public class AscendDescendSequencePuzzles {
	public void playThaat() {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_BILAWAL).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

	public void playMultipleThaats() {
		CyclicSequenceNonPermutating.MultipleSymmetricalFreqSets multipleThaatEnqueuer = new CyclicSequenceNonPermutating.MultipleSymmetricalFreqSets(
				new SymmetricalSet[] { SymmetricalSet.THAAT_BILAWAL, SymmetricalSet.THAAT_ASAVARI });
		System.out.println(multipleThaatEnqueuer.asString());
		AudioLifeCycleManager.instance.play(multipleThaatEnqueuer.allFrequenciesInCycle(), AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
	}

	public void playAllThats() {
		int repeatCount = 2;
		System.out.println("Repeating each item[" + repeatCount + "] \n");
		for (int i = 0; i < 3; i++) {
			// puzzleBuilder.playArohiAvrohi(repeatCount,
			// SecondYearRaag.values());
			playAscendAndDescend(repeatCount, FrequencySet.SymmetricalSet.values());
			System.out.println("round[" + i + "] done \n");
		}
	}

	private void playAscendAndDescend(int count, SymmetricalSet... raags) {
		CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(SymmetricalSet.THAAT_BILAWAL).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
		System.out.println("\n Played [BILAWAL]");
		HashSet<SymmetricalSet> hasheddata = randomize(raags);
		for (SymmetricalSet each : hasheddata) {

			for (int i = 0; i < count; i++) {
				CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(each).play(AudioPlayConfig.SYNCHRONOUS_STATIC_PLAYER);
				System.out.println("\nPlayed [" + each.name() + "] ." + i);
				System.out.println("\n");
			}
		}
	}

	private HashSet<SymmetricalSet> randomize(SymmetricalSet... raag) {
		HashSet<SymmetricalSet> hasheddata = new HashSet<SymmetricalSet>();
		for (SymmetricalSet each : raag) {
			hasheddata.add(each);
		}
		return hasheddata;
	}
}
