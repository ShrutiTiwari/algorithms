package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);
	private PatternedFrequencySetTab patternedFrequencySetTab;
	private QuizTab quizTab;
	private final JTabbedPane tabbedPane;

	public UiTabsFactory(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
		this.quizTab=new QuizTab(tabbedPane);
		this.patternedFrequencySetTab= new PatternedFrequencySetTab(tabbedPane);
	}

	public void addPatternTab() {
		patternedFrequencySetTab.intializeWith(SymmetricalSet.THAAT_KAFI);
	}

	public void addQuizTab() {
		QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels()
				.iterator().next();
		quizTab.initializeWith(firstQuizLevel);
	}

	public JPanel plainTab() {
		return new RehearsePanel(PlayApi.getAllPlainThaat()).getPanel();
	}

	public JPanel songTab() {
		return new RehearsePanel(PlayApi.getAllSongs()).getPanel();
	}
	
	private class PatternedFrequencySetTab{
		private JPanel rehearseWithPatternsPanel;
		private final JTabbedPane tabbedPane;
		
		PatternedFrequencySetTab(final JTabbedPane tabbedPane){
			this.tabbedPane=tabbedPane;
		}
		
		void reConfigureFrequencySetTab(SymmetricalSet selectedThaat) {
			tabbedPane.remove(rehearseWithPatternsPanel);
			intializeWith(selectedThaat);
			rehearseWithPatternsPanel.requestFocusInWindow();
			tabbedPane.setSelectedComponent(rehearseWithPatternsPanel);
		}

		private final void intializeWith(final FrequencySet frequencySet) {
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
			box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate, UiButtons.LARGE_BUTTON_WIDTH, UiButtons.BUTTON_HEIGHT);
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
	}
	
	private class QuizTab{
		private JPanel quizWithFrequencySetPanel;
		private final JTabbedPane tabbedPane;
		
		private QuizTab(final JTabbedPane tabbedPane){
			this.tabbedPane=tabbedPane;
		}
		
		final void initializeWith(QuizLevel<CyclicFrequencySet> quizLevel) {
			QuizPanel quizTabBuilder = new QuizPanel(quizLevel);
			JComboBox dd = createQuizLevelDropdown(quizLevel, quizTabBuilder.buttonYcoordinate());
			this.quizWithFrequencySetPanel = quizTabBuilder.getPanel();
			quizWithFrequencySetPanel.add(dd);
			tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
		}

		void intializeWith() {
			QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels()
					.iterator().next();
			initializeWith(firstQuizLevel);
		}
		private JComboBox createQuizLevelDropdown(final QuizLevel quizLevel, final int buttonYcoordinate) {
			Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
			final JComboBox box = new JComboBox(quizLevels.toArray());
			box.setBackground(Color.RED);
			box.setForeground(Color.GREEN);
			box.setSelectedItem(quizLevel);
			box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate, 500, UiButtons.BUTTON_HEIGHT);
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JComboBox cbox = (JComboBox) arg0.getSource();
					QuizLevel selectedQuiz = (QuizLevel) cbox.getSelectedItem();
					logger.info("selectedQuiz [" + selectedQuiz.displayText() + "]");
					reConfigureQuizTab(selectedQuiz);
				}
			});
			return box;
		}
		private void reConfigureQuizTab(QuizLevel selectedQuiz) {
			tabbedPane.remove(quizWithFrequencySetPanel);
			initializeWith(selectedQuiz);
			quizWithFrequencySetPanel.requestFocusInWindow();
			tabbedPane.setSelectedComponent(quizWithFrequencySetPanel);
		}
	}
}