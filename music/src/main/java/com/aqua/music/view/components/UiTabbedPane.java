package com.aqua.music.view.components;

import javax.swing.JTabbedPane;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

/**
 * @author "Shruti Tiwari"
 */
public class UiTabbedPane {
	private static final String TITLE_QUIZ_TAB = "Quiz with Frequency sets";
	private static final String TITLE_REHEARSE_TAB = "Rehearse with pattern";
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;

	private static final String TITLE_PLAIN_THAAT = "Thaat rehearse";
	private static final String TITLE_SONG_TAB = "Song rehearse";


	public static JTabbedPane getTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(UiTabbedPane.TITLE_PLAIN_THAAT, new RehearsePanel(PlayApi.getAllPlainThaat()).getPanel());
		tabbedPane.addTab(UiTabbedPane.TITLE_SONG_TAB, new RehearsePanel(PlayApi.getAllSongs()).getPanel());
		tabbedPane.addTab(TITLE_REHEARSE_TAB, new RehearsePanel(firstThaat, 2).getPanel());
		tabbedPane.addTab(TITLE_QUIZ_TAB, new QuizPanel(firstQuizLevel).getPanel());
		tabbedPane.setOpaque(true);
		return tabbedPane;
	}
}
