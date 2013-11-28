package com.aqua.music.model.raag;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G3;
import static com.aqua.music.model.core.ClassicalNote.M3_;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.model.puzzles.RandomShuffler;

/**
 * @author "Shruti Tiwari"
 *
 */
public class RaagPhrases {
	private final Collection<MusicalPhrase> allPhrases = new HashSet<MusicalPhrase>();
	private final RandomShuffler shuffler;

	public RaagPhrases() {
		/*
		 * allPhrases.add(new MusicalPhrase().n(N1, R, G, M_));
		 * allPhrases.add(new MusicalPhrase().n(M_, D, P, M_));
		 * allPhrases.add(new MusicalPhrase().n(M_, D, N, S3));
		 * allPhrases.add(new MusicalPhrase().n(G, M_, D, N));
		 * allPhrases.add(new MusicalPhrase().n(G, R, G, G)); allPhrases.add(new
		 * MusicalPhrase().n(S, R, G, G)); allPhrases.add(new
		 * MusicalPhrase().n(M1_, D1, N1, S)); allPhrases.add(new
		 * MusicalPhrase().n(M1_, D1, P1, M1_)); allPhrases.add(new
		 * MusicalPhrase().n(N, R3, S3)); allPhrases.add(new
		 * MusicalPhrase().n(N, R3, G3, S3)); allPhrases.add(new
		 * MusicalPhrase().n(G3, R3, G3, G3)); allPhrases.add(new
		 * MusicalPhrase().n(S3, R3, G3, G3)); allPhrases.add(new
		 * MusicalPhrase().n(N, R3, G3, M3_));
		 */
		//allPhrases.add(new MusicalPhrase().n(G, M_, M_, D, P, M_));
		//allPhrases.add(new MusicalPhrase().n(N, R3, G3, M3_));
		//allPhrases.add(new MusicalPhrase().n(M_, G, R_, R_, S));
		this.shuffler = new RandomShuffler<MusicalPhrase>(allPhrases);
	}

	public static void main(String[] args) {
		RandomShuffler<MusicalPhrase> myshuffler = new RaagPhrases().shuffler;

		BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			MusicalPhrase p = myshuffler.nextRandom();
			System.out.println("Listen recording");
			AudioPlayerSettings.SYNCHRONOUS_DYNAMIC_PLAYER.play(p.frequencies());
			System.out.println("what was the phrase?");
			try {
				String userInput = myReader.readLine();
				System.out.println("userInut[" + userInput + "] Played phrase [" + p.printLine() + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
