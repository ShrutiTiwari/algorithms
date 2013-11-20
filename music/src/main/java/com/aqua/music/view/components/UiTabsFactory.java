package com.aqua.music.view.components;

import javax.swing.JTabbedPane;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiTabsFactory {
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels()
			.iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;
	private UiTabs uiTabs;
	
	public UiTabsFactory(JTabbedPane tabbedPane) {
		this.uiTabs= new UiTabs(tabbedPane);
	}

	public void addTabs() {
		uiTabs.addPlaneThaat(new RehearsePanel(PlayApi.getAllPlainThaat()).getPanel());
		uiTabs.addSong(new RehearsePanel(PlayApi.getAllSongs()).getPanel());
		uiTabs.addPatternedTab(firstThaat, false);
		uiTabs.addQuiz(firstQuizLevel, false);
	}
}