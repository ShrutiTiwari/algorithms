package com.aqua.music.view.helper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiTabsFactory {
	private final JTabbedPane tabbedPane;
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);
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
		this.quizWithFrequencySetPanel = new QjuizUiTab(this, quizLevel).getPanel();
		tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
	}

	private final void addPatternTab(SymmetricalSet selectedThaat) {
		final FrequencySet frequencySet = selectedThaat;
		RehearsalUiTab tabBuilder = new RehearsalUiTab(PlayApi.getAllPatternedThaat(frequencySet, 2));
		JComboBox createThaatDropdown = createThaatDropdown(frequencySet, tabBuilder.buttonYcoordinate());
		tabBuilder.getPanel().add(createThaatDropdown);
		this.rehearseWithPatternsPanel = tabBuilder.getPanel();
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}
	
	private JComboBox createThaatDropdown(final FrequencySet frequencySet, int buttonYcoordinate) {
		final JComboBox box = new JComboBox(SymmetricalSet.values());
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setSelectedItem(frequencySet);
		box.setBounds(RehearsalUiButtons.X_COORIDNATE, buttonYcoordinate, 500, RehearsalUiButtons.HEIGHT());
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cbox = (JComboBox) arg0.getSource();
				FrequencySet selectedThaat = (FrequencySet) cbox.getSelectedItem();
				logger.info("selectedThaat [" + selectedThaat.name() + "]");
				reConfigureFrequencySetTab((SymmetricalSet) selectedThaat);
			}
		});
		return box;
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
		return new RehearsalUiTab(PlayApi.getAllPlainThaat()).getPanel();
	}

	public JPanel songTab() {
		return new RehearsalUiTab(PlayApi.getAllSongs()).getPanel();
	}
}
