package com.aqua.music.view.components;

import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiTabbedPane {
	static final String TITLE_QUIZ_TAB = "Quiz with Frequency sets";
	static final String TITLE_REHEARSE_TAB = "Rehearse with pattern";
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;

	private static final String TITLE_PLAIN_THAAT = "Thaat rehearse";
	private static final String TITLE_SONG_TAB = "Song rehearse";

	private final JTabbedPane mainTabbedPane;

	private UiTabbedPane(JTabbedPane mainTabbedPane) {
		this.mainTabbedPane = mainTabbedPane;
	}

	public static JTabbedPane getTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		UiTabbedPane uiTabbedPane = new UiTabbedPane(tabbedPane);
		uiTabbedPane.addThaatTab(new RehearsePanel(PlayApi.getAllPlainThaat()));
		uiTabbedPane.addSongTab(new RehearsePanel(PlayApi.getAllSongs()));
		uiTabbedPane.addReloadablePanels(firstThaat, firstQuizLevel);
		tabbedPane.setOpaque(true);
		return tabbedPane;
	}

	void refreshReloadablePanel(JPanel newPanel, String title) {
		mainTabbedPane.remove(mainTabbedPane.getSelectedComponent());
		mainTabbedPane.addTab(title, newPanel);
		newPanel.requestFocusInWindow();
		mainTabbedPane.setSelectedComponent(newPanel);
	}

	private void addReloadablePanels(SymmetricalSet firstthaat, QuizLevel<CyclicFrequencySet> firstquizlevel) {
		Entry<String, JPanel> reahearsPanel = new ReloadablePanelContainer(this).newPanelWith(firstthaat);
		mainTabbedPane.addTab(reahearsPanel.getKey(), reahearsPanel.getValue());
		
		Entry<String, JPanel> quizPanel = new ReloadablePanelContainer(this).newPanelWith(firstquizlevel);
		mainTabbedPane.addTab(quizPanel.getKey(), quizPanel.getValue());
	}

	private void addSongTab(RehearsePanel rehearsePanel) {
		mainTabbedPane.addTab(TITLE_SONG_TAB, rehearsePanel.getPanel());
	}

	private void addThaatTab(RehearsePanel rehearsePanel) {
		mainTabbedPane.addTab(TITLE_PLAIN_THAAT, rehearsePanel.getPanel());
	}
}
