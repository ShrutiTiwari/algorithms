package com.aqua.music.view.components;

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
		this.quizWithFrequencySetPanel = new QuizPanel(this, quizLevel).getPanel();
		tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
	}

	private final void addPatternTab(final FrequencySet frequencySet) {
		RehearsePanel tabBuilder = new RehearsePanel(PlayApi.getAllPatternedThaat(frequencySet, 2));
		JComboBox thaatDropdown = thaatDropdown(frequencySet, tabBuilder.buttonYcoordinate());
		tabBuilder.getPanel().add(thaatDropdown);
		rehearseWithPatternsPanel = tabBuilder.getPanel();
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}

	private JComboBox thaatDropdown(final FrequencySet frequencySet, int buttonYcoordinate) {
		final JComboBox box = new JComboBox(SymmetricalSet.values());
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setSelectedItem(frequencySet);
		box.setBounds(RehearseButtons.X_COORIDNATE, buttonYcoordinate, 500, RehearseButtons.HEIGHT());
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
		return new RehearsePanel(PlayApi.getAllPlainThaat()).getPanel();
	}

	public JPanel songTab() {
		return new RehearsePanel(PlayApi.getAllSongs()).getPanel();
	}
}
