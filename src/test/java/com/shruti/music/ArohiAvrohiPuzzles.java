package com.shruti.music;

import java.util.HashSet;

import com.shruti.music.ArohiAvrohiPlayer.AllThaat;

public class ArohiAvrohiPuzzles {
	public ArohiAvrohiPuzzles() {
		AudioLibrary.initializeWithGivenSeconds(1);
	}

	void playThats() {
		int repeatCount = 2;
		System.out.println("Repeating each item[" + repeatCount + "] \n");
		for (int i = 0; i < 3; i++) {
			//puzzleBuilder.playArohiAvrohi(repeatCount, SecondYearRaag.values());
			playArohiAvrohi(repeatCount, ArohiAvrohiPlayer
					.AllThaat.values());
			System.out.println("round[" + i + "] done \n");
		}
	}

	private void playArohiAvrohi(int count, ArohiAvrohiPlayer... raags) {
		AllThaat.BILAWAL.playAarohiAvrohi();
		System.out.println("\n Played [BILAWAL]");
		HashSet<ArohiAvrohiPlayer> hasheddata = randomize(raags);
		for (ArohiAvrohiPlayer each : hasheddata) {
			for (int i = 0; i < count; i++) {
				each.playAarohiAvrohi();
				System.out.println("\nPlayed [" + each.name() + "] ." + i);
				System.out.println("\n");
			}
		}
	}

	private HashSet<ArohiAvrohiPlayer> randomize(ArohiAvrohiPlayer... raag) {
		HashSet<ArohiAvrohiPlayer> hasheddata = new HashSet<ArohiAvrohiPlayer>();
		for (ArohiAvrohiPlayer each : raag) {
			hasheddata.add(each);
		}
		return hasheddata;
	}
}
