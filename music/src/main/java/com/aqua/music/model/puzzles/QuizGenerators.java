package com.aqua.music.model.puzzles;

import java.util.ArrayList;
import java.util.Collection;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;

/**
 * @author "Shruti Tiwari"
 *
 * @param <T>
 */
interface QuizGenerators<T> {
	Collection<QuizLevel<T>> quizLevels();

	static class FrequencySetQuizController implements QuizGenerators<CyclicFrequencySet> {
		private final CyclicFrequencySet[] allItems;
		private final Collection<QuizLevel<CyclicFrequencySet>> quizLevels;

		FrequencySetQuizController() {
			SymmetricalSet[] values = SymmetricalSet.values();
			allItems = new CyclicFrequencySet[values.length];
			int i = 0;
			for (FrequencySet eachFrequencySet : values) {
				allItems[i++] = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);
			}

			quizLevels = generateQuizLevels();
		}

		private Collection<QuizLevel<CyclicFrequencySet>> generateQuizLevels() {
			final Collection<QuizLevel<CyclicFrequencySet>> quizs = new ArrayList<QuizLevel<CyclicFrequencySet>>();
			for (int levelIndex = 1; levelIndex < allItems.length - 1; levelIndex++) {
				QuizLevel<CyclicFrequencySet> quizLevel = new QuizLevel<CyclicFrequencySet>("Level " + (levelIndex), levelIndex + 1, allItems);
				if (quizLevel.quizSections().size() < 2) {
					break;
				} else {
					quizs.add(quizLevel);
				}
			}
			return quizs;
		}

		public Collection<QuizLevel<CyclicFrequencySet>> quizLevels() {
			return quizLevels;
		}
	}
}
