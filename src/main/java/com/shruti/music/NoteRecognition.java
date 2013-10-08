package com.shruti.music;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static com.shruti.music.Playable.BaseNotes.*;

public class NoteRecognition {

	static HashSet<Playable> saTopa = new HashSet<Playable>();
	static HashSet<Playable> paToHighSa = new HashSet<Playable>();
	static int REPEAT_COUNT = 6;
	public static void main(String[] args) {
		initialize();
		//alternatePlay();
		sequentialPay();
	}

	private static void alternatePlay(HashSet<Playable>... saTopa2) {
		Iterator<Playable> iterator1 = saTopa.iterator();
		Iterator<Playable> iterator2 = paToHighSa.iterator();
		List<Playable> playNotes=new ArrayList<Playable>();
		for (int index = 0; index < paToHighSa.size(); index++) {
			playNotes.add(iterator1.next());
			playNotes.add(iterator2.next());
		}
		NotePlayer.play(playNotes);
		playNotes=new ArrayList<Playable>();
		for (int index = paToHighSa.size(); index < saTopa.size(); index++) {
			playNotes.add(iterator1.next());
		}
		NotePlayer.play(playNotes);
	}

	private static void initialize() {
		AudioLibrary.initializeWithGivenSeconds(2);
		populateNotes(saTopa, RE, RE_, GA, GA_, MA, MA_);
		populateNotes(paToHighSa, DHA, DHA_, NI, NI_);
	}

	private static void play(HashSet<Playable> saTopa2) {
		List<Playable> playNotes=new ArrayList<Playable>();
		for (int i = 0; i < REPEAT_COUNT; i++) {
			System.out.println("\n");
			for (Playable each : saTopa2) {
				playNotes.add(each);
			}
		}
		NotePlayer.play(playNotes);
	}

	private static void populateNotes(HashSet<Playable> agreegatator, Playable... notes) {
		for (Playable each : notes) {
			agreegatator.add(each);
		}
	}

	private static void sequentialPay() {
		System.out.println("Sa to pa");
		play(saTopa);
		System.out.println("\n pa to high sa");
		play(paToHighSa);
	}

}
