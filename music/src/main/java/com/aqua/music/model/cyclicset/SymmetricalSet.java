package com.aqua.music.model.cyclicset;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import static com.aqua.music.model.core.ClassicalNote.*;
/**
 * 
 * This set uses same set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum SymmetricalSet implements FrequencySet {
	THAAT_BHAIRAV(R_, G, M, P, D_, N),
	THAAT_PURVI(R_, G, M_, P, D_, N),
	THAAT_MARWA(R_, G, M_, P, D, N),
	THAAT_KALYAN(R, G, M_, P, D, N),
	THAAT_BILAWAL(R, G, M, P, D, N),
	THAAT_KHAMAJ(R, G, M, P, D, N_),
	THAAT_KAFI(R, G_, M, P, D, N_),
	THAAT_ASAVARI(R, G_, M, P, D_, N_),
	THAAT_BHAIRAVI(R_, G_, M, P, D_, N_),
	THAAT_TODI(R_, G_, M_, P, D_, N),
	RAAG2_BAIRAGI(R_, M, P, N_),
	RAAG2_GUJARI_TODI(R_, G_, M_, D_, N);

	private final Frequency[] ascendNotes;
	private final Frequency[] descendNotes;

	private SymmetricalSet(Frequency... ascendNotes) {
		this.ascendNotes = ascendNotes;
		this.descendNotes = Util.reverse(ascendNotes);
	}

	public String type() {
		return "THAAT";
	}

	public Frequency[] ascendNotes() {
		return ascendNotes;
	}

	public Frequency[] descendNotes() {
		return descendNotes;
	}
}