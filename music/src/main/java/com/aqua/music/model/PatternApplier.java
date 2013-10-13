package com.aqua.music.model;

import java.util.Collections;
import java.util.List;

public interface PatternApplier<T> {
	List<T> ascendSequence();

	List<T> descendSequence();
	
	List<T> allNotes();
	void printAscDescPattern();
	void generateAscendAndDescendSequences(T[] commonAscDescInput);

	PatternApplier NONE = new PatternApplier() {
		@Override
		public List ascendSequence() {
			return Collections.EMPTY_LIST;
		}

		@Override
		public List descendSequence() {
			return Collections.EMPTY_LIST;
		}

		@Override
		public List allNotes() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void printAscDescPattern() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void generateAscendAndDescendSequences(Object[] commonAscDescInput) {
			// TODO Auto-generated method stub
			
		}
	};
}
