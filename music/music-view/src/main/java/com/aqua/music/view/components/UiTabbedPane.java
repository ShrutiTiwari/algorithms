package com.aqua.music.view.components;

import static com.aqua.music.view.components.UiTexts.TITLE_PUZZLES;
import static com.aqua.music.view.components.UiTexts.TITLE_SONG_TAB;
import static com.aqua.music.view.components.UiTexts.TITLE_THAAT;
import static com.aqua.music.view.components.UiTexts.TITLE_THAAT_PATTERN;

import javax.swing.JTabbedPane;

import open.music.api.PlayApi;
import open.music.api.StateDependentUi;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.view.components.UiTexts.UiLables;
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
		pracitceTabbedPane.addTab(TITLE_THAAT, new MusicPanelForPractice(PlayApi.getAllPlainThaat(),UiTexts.UiLables.PRACTICE_A_THAAT).getPanel());
		pracitceTabbedPane.addTab(TITLE_THAAT_PATTERN, new MusicPanelForPractice(firstThaat,PermuatationsGenerator.PAIR, UiTexts.UiLables.PRACTICE_A_PATTERN).getPanel());
		pracitceTabbedPane.addTab(TITLE_SONG_TAB, new MusicPanelForPractice(PlayApi.getAllSongs(),UiTexts.UiLables.PRACTICE_A_SONG).getPanel());
		mainTabbedPane.addTab(UiTexts.TITLE_PRACTICE, pracitceTabbedPane);
		
		mainTabbedPane.addTab(TITLE_PUZZLES, new MusicPanelForQuiz(stateDependentUi,firstQuizLevel).getPanel());

		mainTabbedPane.setOpaque(true);
		pracitceTabbedPane.setOpaque(true);
		return mainTabbedPane;
	}
}
