package com.aqua.music.model.puzzles;

import java.util.Collection;

/**
 * @author "Shruti Tiwari"
 *
 */
public enum QuizController implements QuizGenerators{
	FrequencyQuiz(new FrequencySetQuizController()),
	FrequencySetQuiz(new FrequencySetQuizController()),
	SongQuiz(new FrequencySetQuizController());

	private final QuizGenerators quizGenerators;
	
	public Collection<QuizLevel> quizLevels()
	{
		return quizGenerators.quizLevels();
	}
	
	private QuizController(QuizGenerators quizGenerators) {
		this.quizGenerators = quizGenerators;
	}
}
