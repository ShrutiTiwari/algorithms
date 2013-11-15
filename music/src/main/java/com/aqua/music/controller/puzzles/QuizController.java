package com.aqua.music.controller.puzzles;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

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

	static class FrequencySetQuizController implements QuizGenerators<CyclicFrequencySet>{
		private final CyclicFrequencySet[] allItems;
		private final Collection<QuizLevel<CyclicFrequencySet>> quizLevels;

		FrequencySetQuizController() {
			SymmetricalSet[] values = SymmetricalSet.values();
			allItems=new CyclicFrequencySet[values.length];
			int i=0;
			for (FrequencySet eachFrequencySet : values) {
				allItems[i++]=CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);
			}
			
			quizLevels = generateQuizLevels();
		}

		private Collection<QuizLevel<CyclicFrequencySet>> generateQuizLevels() {
			final Collection<QuizLevel<CyclicFrequencySet>> quizs = new ArrayList<QuizLevel<CyclicFrequencySet>>();
			for (int i = 0; i < allItems.length - 2; i++) {
				QuizLevel<CyclicFrequencySet> quizLevel = new QuizLevel<CyclicFrequencySet>("Level " + i, i + 2, allItems);
				if(quizLevel.quizSections().size()<2){
					break;
				}else{
					quizs.add(quizLevel);
				}
			}
			return quizs;
		}
		
		public Collection<QuizLevel<CyclicFrequencySet>> quizLevels(){
			return quizLevels;
		}
	}
}
