package com.aqua.music.model.raag;

import static com.aqua.music.model.core.ClassicalNote.D;
import static com.aqua.music.model.core.ClassicalNote.D_;
import static com.aqua.music.model.core.ClassicalNote.G;
import static com.aqua.music.model.core.ClassicalNote.G_;
import static com.aqua.music.model.core.ClassicalNote.M;
import static com.aqua.music.model.core.ClassicalNote.M_;
import static com.aqua.music.model.core.ClassicalNote.N;
import static com.aqua.music.model.core.ClassicalNote.N1;
import static com.aqua.music.model.core.ClassicalNote.N_;
import static com.aqua.music.model.core.ClassicalNote.P;
import static com.aqua.music.model.core.ClassicalNote.R;
import static com.aqua.music.model.core.ClassicalNote.R_;
import static com.aqua.music.model.core.ClassicalNote.S;
import static com.aqua.music.model.core.ClassicalNote.S3;

import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;

/**
 * This set uses different set of notes in ascend and descend
 * 
 * @author shruti.tiwari
 * 
 */
public enum RaagArohiAvrohi implements FrequencySet {
	AHIR_BHAIRAV(sequence(S, R_, G, M, P, D, N_, S3)),
	BAIRAGI(sequence(S, R_, M, P, N_, S3)),
	BHAIRAV(sequence(S, R_, G, M, P, D_, N, S3)),
	BHIMPALASI(sequence(S, G_, M, P, N_, S3), sequence(S3, N_, D, P, M, P, G_, M, G_, R, S)),
	BHOPALI(sequence(S, R, G, P, D, S3)),
	GUJARI_TODI(sequence(S, R_, G_, M_, D_, N, S3)),
	JAUNPURI(sequence(S, R_, M, P, D, N, S3), sequence(S3, N_, D_, P, M, P, D_, M, P, G_, R, S)),
	KHAMAJ(sequence(S, G, M, P, N, S3), sequence(S3, N_, D, P, M, G, R, S)),
	MULTANI(sequence(N1, S, G_, M_, P, N, S3), sequence(S3, N, D_, P, M_, G_, R_, S)),
	PURYA_KALYAN(sequence(N1, R_, G, M_, P, M_, D, N, S3), sequence(S3, N, D, P, D, M_, P, G, M_, R_, G, R_, S)),
	SHUDH_SARANG(sequence(N1, S, R, M_, P, N, S3), sequence(S3, N, D, P, M_, P, M, R, S)),
	YAMAN(sequence(N1, R, G, M_, D, N, S3), sequence(S3, N, D, P, M_, G, R, S));

	private final Frequency[] ascendNotes;
	private final Frequency[] descendNotes;

	private RaagArohiAvrohi(Frequency... ascendNotes) {
		this.ascendNotes = ascendNotes;
		this.descendNotes = Util.reverse(ascendNotes);
	}

	private RaagArohiAvrohi(Frequency[] ascendNotes, Frequency[] descendNotes) {
		this.ascendNotes = ascendNotes;
		this.descendNotes = descendNotes;
	}

	private static Frequency[] sequence(Frequency... notes) {
		return notes;
	}

	@Override
	public Frequency[] ascendNotes() {
		return ascendNotes;
	}

	@Override
	public Frequency[] descendNotes() {
		return descendNotes;
	}

	public String type() {
		return "RAAG";
	}

	@Override
	public Frequency[][] ascendDescendNotes(ClassicalNote startClassicalNote, ClassicalNote endClassicalNote) {
		return null;
	}
}