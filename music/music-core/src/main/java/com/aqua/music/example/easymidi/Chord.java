package com.aqua.music.example.easymidi;

import java.util.ArrayList;
import java.util.List;

import com.aqua.music.example.easymidi.AbsractPlayable.Drum;
import com.aqua.music.example.easymidi.AbsractPlayable.Note;
import com.aqua.music.example.easymidi.Playable.Action;

public class Chord {
	private int counter16 = 0;
	private final List<Playable> drums = new ArrayList<Playable>();
	private final List<Playable> notes = new ArrayList<Playable>();
	private final int divisionInSixteen;

	public Chord(int part) {
		this.divisionInSixteen = 16 / part;
	}

	public Chord addDrum(Drum drum) {
		drums.add(drum);
		return this;
	}

	public Chord addNote(Note note) {
		return note(note);
	}
	public Chord addNote(int part, int pitch, int instrument) {
		return note(new Note(part, pitch, instrument));
	}

	public void incrementCounter() {
		counter16++;
	}

	public Chord note(Note note) {
		notes.add(note);
		return this;
	}

	int counter() {
		return counter16;
	}

	int divisionInSixteen() {
		return divisionInSixteen;
	}

	void play() {
		resetCounter();
		Action.PLAY.playAll(drums, notes);
	}

	void stop() {
		resetCounter();
		Action.STOP.playAll(drums, notes);
	}

	void tick() {
		Action.TICK.playAll(drums, notes);
	}

	private void resetCounter() {
		counter16 = 0;
	}

	public static class ChordsList {
		List<Chord> chords = new ArrayList<Chord>();

		public ChordsList() {
		}

		public ChordsList chord(Chord c) {
			chords.add(c);
			return this;
		}

		Chord getChord(int currentChordNum) {
			return chords.get(currentChordNum);
		}

		void stopAll() {
			for (Chord each : chords) {
				each.stop();
			}
		}

		boolean isEmpty() {
			return chords.isEmpty();
		}

		int numOfchords() {
			return chords.size();
		}
	}
}
