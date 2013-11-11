package com.aqua.music.logic;

import java.util.Collections;
import java.util.List;

interface PermutationApplicator<T> {
	static final String SEP = "\n";

	List<T> allNotes();

	String name();
	
	String prettyPrintTextForAscDesc();

	void initializeWith(T[] commonAscDescInput);

	PermutationApplicator NONE = new PermutationApplicator() {
		@Override
		public List allNotes() {
			return Collections.EMPTY_LIST;
		}

		@Override
		public String prettyPrintTextForAscDesc() {
			return "";
		}

		@Override
		public void initializeWith(Object[] commonAscDescInput) {
		}

		@Override
		public String name() {
			return "";
		}
	};
}
