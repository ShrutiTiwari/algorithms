package com.aqua.music.model.cyclicset;

import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;
import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R_;

import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
/**
 * 
 * This set uses same set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum SymmetricalSet implements FrequencySet {
	THAAT_BHAIRAV(S,R_, G, M, P, D_, N,S3),
	THAAT_PURVI(S,R_, G, M_, P, D_, N,S3),
	THAAT_MARWA(S,R_, G, M_, P, D, N,S3),
	THAAT_KALYAN(S,R, G, M_, P, D, N,S3),
	THAAT_BILAWAL(S,R, G, M, P, D, N,S3),
	THAAT_KHAMAJ(S,R, G, M, P, D, N_,S3),
	THAAT_KAFI(S,R, G_, M, P, D, N_,S3),
	THAAT_ASAVARI(S,R, G_, M, P, D_, N_,S3),
	THAAT_BHAIRAVI(S,R_, G_, M, P, D_, N_,S3),
	THAAT_TODI(S,R_, G_, M_, P, D_, N,S3),
	RAAG2_BAIRAGI(S,R_, M, P, N_,S3),
	RAAG2_GUJARI_TODI(S,R_, G_, M_, D_, N,S3);

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