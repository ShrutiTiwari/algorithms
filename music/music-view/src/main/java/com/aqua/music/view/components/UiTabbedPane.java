package com.aqua.music.view.components;

import static com.aqua.music.view.components.UiLabels.TITLE_PUZZLES;
import static com.aqua.music.view.components.UiLabels.TITLE_SONG_TAB;
import static com.aqua.music.view.components.UiLabels.TITLE_THAAT;
import static com.aqua.music.view.components.UiLabels.TITLE_THAAT_PATTERN;

import javax.swing.JTabbedPane;

import open.music.api.PlayApi;
import open.music.api.StateDependentUi;

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


	public static JTabbedPane getTabbedPane(StateDependentUi stateDependentUi) {
		JTabbedPane mainTabbedPane = new JTabbedPane();
		
		JTabbedPane pracitceTabbedPane = new JTabbedPane();
		pracitceTabbedPane.addTab(TITLE_THAAT, new MusicPanelForPractice(PlayApi.getAllPlainThaat(),UiLabels.PRACTICE_A_THAAT).getPanel());
		pracitceTabbedPane.addTab(TITLE_THAAT_PATTERN, new MusicPanelForPractice(firstThaat,PermuatationsGenerator.PAIR, UiLabels.PRACTICE_A_PATTERN).getPanel());
		pracitceTabbedPane.addTab(TITLE_SONG_TAB, new MusicPanelForPractice(PlayApi.getAllSongs(),UiLabels.PRACTICE_A_SONG).getPanel());
		mainTabbedPane.addTab(UiLabels.TITLE_PRACTICE, pracitceTabbedPane);
		
		mainTabbedPane.addTab(TITLE_PUZZLES, new MusicPanelForQuiz(stateDependentUi,firstQuizLevel).getPanel());

		mainTabbedPane.setOpaque(true);
		pracitceTabbedPane.setOpaque(true);
		return mainTabbedPane;
	}
}
