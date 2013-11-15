package com.aqua.music.view;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.QuizSection;

public class UiTabForQuizes extends AbstractRehearseTabPanels<CyclicFrequencySet> {
	private final QuizLevel quizLevel;
	private final UiTabsFactory rehearseTabs;

	private final JComboBox quizLevelsDD = createQuizLevelDropdown();

	UiTabForQuizes(final UiTabsFactory rehearseTabs, final QuizLevel quizLevel) {
		super();
		this.rehearseTabs = rehearseTabs;
		this.quizLevel = quizLevel;

	}

	protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {
		mainTab.add(createQuizLevelDropdown());
		final JLabel jLabel = createTextLabel();
		mainTab.add(jLabel);
		jLabel.setBounds(UiButtons.X_COORIDNATE + 700, 300, 100, UiButtons.BUTTON_WIDTH);
		
		int i=0;
		for (QuizSection<CyclicFrequencySet> each : (Collection<QuizSection<CyclicFrequencySet>>) quizLevel.quizSections()) {
			UiButtonsForQuizs.FREQUENCY_SET_QUIZ.createJComponents(mainTab, buttonYcoordinate(), each, "Quiz "+i);
			i++;
		}
		return Collections.EMPTY_LIST;
	}

	private JComboBox createQuizLevelDropdown() {
		Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
		final JComboBox box = new JComboBox(quizLevels.toArray());
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setSelectedItem(quizLevel);
		box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate(), 500, UiButtons.BUTTON_HEIGHT);
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cbox = (JComboBox) arg0.getSource();
				QuizLevel selectedQuiz = (QuizLevel) cbox.getSelectedItem();
				logger.info("selectedQuiz [" + selectedQuiz.displayText() + "]");
				rehearseTabs.reConfigureQuizTab(selectedQuiz);
			}
		});
		return box;
	}
}
