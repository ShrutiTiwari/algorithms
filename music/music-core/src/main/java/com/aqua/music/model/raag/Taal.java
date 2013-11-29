package com.aqua.music.model.raag;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum Taal {
	EKTAAL(12, 6),
	RUPAK(7, 2, new int[] { 3, 2, 2 }),
	TEENTAL(16, 4);

	private final int[] beatsSequence;
	private final int numOfInterval;

	private final int totalBeats;

	private Taal(int totalBeats, int numOfInterval) {
		this.totalBeats = totalBeats;
		this.numOfInterval = numOfInterval;
		int beatsPerGroup = totalBeats / numOfInterval;
		this.beatsSequence = new int[numOfInterval];
		for (int i = 0; i < beatsSequence.length; i++) {
			beatsSequence[i] = beatsPerGroup;
		}
	}

	private Taal(int totalBeats, int numOfInterval, int[] nonUniformBeatsSequence) {
		this.totalBeats = totalBeats;
		this.numOfInterval = numOfInterval;
		this.beatsSequence = nonUniformBeatsSequence;
	}
	public int[] beatsSequence() {
		return beatsSequence;
	}

	public int numOfInterval() {
		return numOfInterval;
	}

	public int totalBeats() {
		return totalBeats;
	}
}
