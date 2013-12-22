package com.aqua.music.view.components;

import static com.aqua.music.view.components.UiTexts.TITLE_PUZZLES;

import javax.swing.JTabbedPane;

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
		pracitceTabbedPane.addTab(MusicPracticePanelType.THAAT.title(), new MusicPanelForPractice(MusicPracticePanelType.THAAT,stateDependentUi).getPanel());
		pracitceTabbedPane.addTab(MusicPracticePanelType.PATTERN.title(), new MusicPanelForPractice(MusicPracticePanelType.PATTERN,stateDependentUi,firstThaat, PermuatationsGenerator.PAIR).getPanel());
		pracitceTabbedPane.addTab(MusicPracticePanelType.SONG.title(), new MusicPanelForPractice(MusicPracticePanelType.SONG,stateDependentUi).getPanel());
		mainTabbedPane.addTab(UiTexts.TITLE_PRACTICE, pracitceTabbedPane);
		
		mainTabbedPane.addTab(TITLE_PUZZLES, new MusicPanelForQuiz(stateDependentUi,firstQuizLevel).getPanel());

		mainTabbedPane.setOpaque(true);
		pracitceTabbedPane.setOpaque(true);
		return mainTabbedPane;
	}
}
