package com.aqua.music.model.puzzles;

import java.util.Collection;

public interface QuizGenerators<T> {
	Collection<QuizLevel<T>> quizLevels();
}
