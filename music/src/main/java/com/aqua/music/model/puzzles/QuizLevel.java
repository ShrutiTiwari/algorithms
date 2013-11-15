package com.aqua.music.model.puzzles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuizLevel<T> {
	private final Collection<QuizSection<T>> quizSections;
	private final String displayText;

	public QuizLevel(String displayText, int bucketSize, T[] items) {
		this.displayText = displayText;
		Collection<List<T>> bucketedPuzzles = new PuzzleBuilder<T>(items, bucketSize).allBuckets();
		quizSections = new ArrayList<QuizSection<T>>(bucketedPuzzles.size());
		for (List<T> each : bucketedPuzzles) {
			quizSections.add(new QuizSection<T>(each, new RandomShuffler<T>(each)));
		}
	}

	@Override
	public String toString() {
		return displayText;
	}
	public static class QuizSection<T> {
		private final List<T> quizItems;
		private final RandomShuffler<T> randomShuffler;

		public QuizSection(List<T> quizItems, RandomShuffler<T> randomShuffler) {
			this.quizItems = quizItems;
			this.randomShuffler = randomShuffler;
		}
		
		public List<T> quizItems() {
			return quizItems;
		}
		public T playItem(){
			return randomShuffler.nextRandom();
		}
	}
	
	public Collection<QuizSection<T>> quizSections() {
		return quizSections;
	}
	
	public String displayText(){
		return displayText;
	}
}