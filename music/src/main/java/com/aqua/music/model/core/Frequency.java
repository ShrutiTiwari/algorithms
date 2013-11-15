package com.aqua.music.model.core;

import java.util.HashMap;
import java.util.Map;

public interface Frequency extends DynamicFrequency {
	public FrequencyRelation freqRel = new FrequencyRelation();

	public String prettyPrint();

	public String western();

	class FrequencyRelation {
		private final Map<ClassicalNote, ClassicalNote[]> baseNotes = new HashMap<ClassicalNote, ClassicalNote[]>();

		private FrequencyRelation() {
			baseNotes.put(ClassicalNote.N, new ClassicalNote[] { ClassicalNote.N1 });
			baseNotes.put(ClassicalNote.N_, new ClassicalNote[] { ClassicalNote.N1_ });
			baseNotes.put(ClassicalNote.D_, new ClassicalNote[] { ClassicalNote.D1_ });
			baseNotes.put(ClassicalNote.D, new ClassicalNote[] { ClassicalNote.D1 });
			baseNotes.put(ClassicalNote.P, new ClassicalNote[] { ClassicalNote.P1, ClassicalNote.P3 });
			baseNotes.put(ClassicalNote.M, new ClassicalNote[] { ClassicalNote.M1, ClassicalNote.M3 });
			baseNotes.put(ClassicalNote.M_, new ClassicalNote[] { ClassicalNote.M1_, ClassicalNote.M3_ });
			baseNotes.put(ClassicalNote.G, new ClassicalNote[] { ClassicalNote.G1, ClassicalNote.G3 });
			baseNotes.put(ClassicalNote.G_, new ClassicalNote[] { ClassicalNote.G1_, ClassicalNote.G3_ });
			baseNotes.put(ClassicalNote.R, new ClassicalNote[] { ClassicalNote.R1, ClassicalNote.R3 });
			baseNotes.put(ClassicalNote.R_, new ClassicalNote[] { ClassicalNote.R1_, ClassicalNote.R3_ });
		}

		public ClassicalNote higher(ClassicalNote refNote) {
			return baseNotes.get(refNote)[1];
		}

		public ClassicalNote lower(ClassicalNote refNote) {
			return baseNotes.get(refNote)[0];
		}
	}
}