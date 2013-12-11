package com.aqua.music.view.components;

import javax.swing.JTabbedPane;

import open.music.api.PlayApi;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

/**
 * @author "Shruti Tiwari"
 */
public class UiTabbedPane {
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;

	private static final String TITLE_QUIZ_TAB = "Thaat Puzzles";
	private static final String TITLE_SONG_TAB = "Practice songs";
	private static final String TITLE_PRACTICE_THAAT_PATTERN = "Practice Thaats with pattern";
	private static final String TITLE_PRACTICE_THAAT_PLAIN = "Practice Thaats";

	public static JTabbedPane getTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(UiTabbedPane.TITLE_PRACTICE_THAAT_PLAIN, new MusicPanelForPractice(PlayApi.getAllPlainThaat()).getPanel());
		tabbedPane.addTab(TITLE_PRACTICE_THAAT_PATTERN, new MusicPanelForPractice(firstThaat, PermuatationsGenerator.PAIR).getPanel());
		tabbedPane.addTab(TITLE_QUIZ_TAB, new MusicPanelForQuiz(firstQuizLevel).getPanel());
		tabbedPane.addTab(UiTabbedPane.TITLE_SONG_TAB, new MusicPanelForPractice(PlayApi.getAllSongs()).getPanel());
		tabbedPane.setOpaque(true);
		return tabbedPane;
	}
}
