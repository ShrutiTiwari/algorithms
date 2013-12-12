package com.aqua.music.example.easymidi;

import java.util.Timer;
import java.util.TimerTask;

import com.aqua.music.example.easymidi.Chord.ChordsList;

public class Ticker {
	public int counter16 = 0;
	int currentChordNum = 0;
	boolean on = false;

	private final int delay16;
	private final ChordsList chordsList;
	private final Timer timer;

	public Ticker(int tempo, ChordsList chordsList) {
		this.timer = new Timer(true);
		this.chordsList = chordsList;
		this.delay16 = (int) (60000.0 / tempo / 4.0);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (on) {
					counter16++;
					tick();
				}
			}
		}, 0, delay16);
	}

	public void restart() {
		counter16 = 0;
		currentChordNum = 0;
		on = true;
		if (currentChordNum >= chordsList.numOfchords()) {
			return;
		}
		chordsList.getChord(currentChordNum).play();
	}

	public void stop() {
		on = false;
		chordsList.stopAll();
	}

	void onFinish() {
		restart();
	}

	private void tick() {
		if (chordsList == null || chordsList.isEmpty()) {
			return;
		}
		if (currentChordNum >= chordsList.numOfchords()) {
			onFinish();
		}
		if (currentChordNum >= chordsList.numOfchords()) {
			return;
		}

		Chord currentChord = chordsList.getChord(currentChordNum);
		currentChord.incrementCounter();
		if (currentChord.counter() >= currentChord.divisionInSixteen()) {
			currentChordNum++;
			if (currentChordNum >= chordsList.chords.size()) {
				onFinish();
			} else {
				chordsList.getChord(currentChordNum).play();
			}
		} else {
			for (int i = 0; i < chordsList.chords.size(); i++) {
				currentChord.tick();
			}
		}
	}
}
