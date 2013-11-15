package com.aqua.music.model.raags;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import static com.aqua.music.model.core.ClassicalNote.*;

/**
 * This set uses different set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum AssymmericalSet implements FrequencySet {
	RAAG2_SHUDH_SARANG(sequence(R, M_, P, N), sequence(N, D, P, M_, P, M, R)),
	RAAG2_YAMAN(sequence(R, G, M_, D, N), sequence(N, D, P, M_, G, R)),
	RAAG2_PURYA_KALYAN(sequence(R_, G, M_, P, M_, D, N), sequence(N, D, P, D, M_, P, G, M_, R_, G, R_)),
	RAAG2_MULTANI(sequence(G_, M_, P, N), sequence(N, D_, P, M_, G_, R_));

	private final Frequency[] ascendNotes;
	private final Frequency[] descendNotes;

	private AssymmericalSet(Frequency... ascendNotes) {
		this.ascendNotes = ascendNotes;
		this.descendNotes = Util.reverse(ascendNotes);
	}

	private AssymmericalSet(Frequency[] ascendNotes, Frequency[] descendNotes) {
		this.ascendNotes = ascendNotes;
		this.descendNotes = descendNotes;
	}

	private static Frequency[] sequence(Frequency... notes) {
		return notes;
	}

	public String type() {
		return "RAAG";
	}

	@Override
	public Frequency[] ascendNotes() {
		return ascendNotes;
	}

	@Override
	public Frequency[] descendNotes() {
		return descendNotes;
	}
}