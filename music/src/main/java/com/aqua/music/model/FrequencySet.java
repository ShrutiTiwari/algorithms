package com.aqua.music.model;

import static com.aqua.music.model.Frequency.ClassicalNote.D;
import static com.aqua.music.model.Frequency.ClassicalNote.D_;
import static com.aqua.music.model.Frequency.ClassicalNote.G;
import static com.aqua.music.model.Frequency.ClassicalNote.G_;
import static com.aqua.music.model.Frequency.ClassicalNote.M;
import static com.aqua.music.model.Frequency.ClassicalNote.M_;
import static com.aqua.music.model.Frequency.ClassicalNote.N;
import static com.aqua.music.model.Frequency.ClassicalNote.N_;
import static com.aqua.music.model.Frequency.ClassicalNote.P;
import static com.aqua.music.model.Frequency.ClassicalNote.R;
import static com.aqua.music.model.Frequency.ClassicalNote.R_;

public interface FrequencySet {
	public String name();

	public String type();

	public Frequency[] ascendNotes();

	public Frequency[] descendNotes();

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

	static abstract class Util {
		static Frequency[] reverse(Frequency... ascendNotes) {
			int count = ascendNotes.length;
			Frequency[] dscendNotes = new Frequency[count];
			for (int i = 0; i < count; i++) {
				dscendNotes[i] = ascendNotes[count - i - 1];
			}
			return dscendNotes;
		}
	}
}