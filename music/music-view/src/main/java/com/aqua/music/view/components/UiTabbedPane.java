package com.aqua.music.view.components;

import javax.swing.JTabbedPane;

import open.music.api.PlayApi;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import static com.aqua.music.view.components.UiLabels.*;
/**
 * @author "Shruti Tiwari"
 */
public class UiTabbedPane {
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;


	public static JTabbedPane getTabbedPane(CommonPanelComponents commonPanelComponents) {
		JTabbedPane mainTabbedPane = new JTabbedPane();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(TITLE_THAAT, new MusicPanelForPractice(commonPanelComponents,PlayApi.getAllPlainThaat(), UiLabels.PRACTICE_A_THAAT).getPanel());
		tabbedPane.addTab(TITLE_THAAT_PATTERN, new MusicPanelForPractice(commonPanelComponents,firstThaat, PermuatationsGenerator.PAIR, UiLabels.PRACTICE_A_PATTERN).getPanel());
		tabbedPane.addTab(TITLE_SONG_TAB, new MusicPanelForPractice(commonPanelComponents,PlayApi.getAllSongs(), UiLabels.PRACTICE_A_SONG).getPanel());
		
		mainTabbedPane.addTab(UiLabels.TITLE_PRACTICE, tabbedPane);
		mainTabbedPane.addTab(TITLE_PUZZLES, new MusicPanelForQuiz(commonPanelComponents,firstQuizLevel).getPanel());
		mainTabbedPane.setOpaque(true);
		tabbedPane.setOpaque(true);
		return mainTabbedPane;
	}
}
