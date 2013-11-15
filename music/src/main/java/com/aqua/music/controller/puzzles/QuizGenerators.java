package com.aqua.music.controller.puzzles;

import java.util.Collection;

public interface QuizGenerators<T> {
	Collection<QuizLevel<T>> quizLevels();
}
