package com.aqua.music.view.helper;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.song.Song;

public class UiTabsFactory {
	private final JTabbedPane tabbedPane;

	// mutated state
	private JPanel rehearseWithPatternsPanel;
	private JPanel quizWithFrequencySetPanel;

	public UiTabsFactory(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public void addPatternTab() {
		addPatternTab(SymmetricalSet.THAAT_KAFI);
	}

	public void addQuizSectionTab() {
		QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels()
				.iterator().next();
		addQuizSectionTab(firstQuizLevel);
	}

	final void addQuizSectionTab(QuizLevel<CyclicFrequencySet> quizLevel) {
		this.quizWithFrequencySetPanel = new UiTabForQuizes(this, quizLevel).getPanel();
		tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
	}

	private final void addPatternTab(SymmetricalSet selectedThaat) {
		final FrequencySet frequencySet = selectedThaat;
		this.rehearseWithPatternsPanel = new UiTabForRehearsingPatternedFrequencySets(this, frequencySet).getPanel();
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}

	void reConfigureQuizTab(QuizLevel selectedQuiz) {
		tabbedPane.remove(quizWithFrequencySetPanel);
		addQuizSectionTab(selectedQuiz);
		quizWithFrequencySetPanel.requestFocusInWindow();
		tabbedPane.setSelectedComponent(quizWithFrequencySetPanel);
	}

	void reConfigureFrequencySetTab(SymmetricalSet selectedThaat) {
		tabbedPane.remove(rehearseWithPatternsPanel);
		addPatternTab(selectedThaat);
		rehearseWithPatternsPanel.requestFocusInWindow();
		tabbedPane.setSelectedComponent(rehearseWithPatternsPanel);
	}

	public JPanel plainTab() {
		return new UiTabForRehearsingPlaybles(SymmetricalSet.values()).getPanel();
	}

	public JPanel songTab() {
		return new UiTabForRehearsingPlaybles(Song.values()).getPanel();
	}
}
