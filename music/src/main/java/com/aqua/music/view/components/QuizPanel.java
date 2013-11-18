package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;

class QuizPanel extends AbstractRehearsePanel<CyclicFrequencySet> {
	private final QuizLevel quizLevel;
	private final UiTabsFactory rehearseTabs;

	private final JComboBox quizLevelsDD = createQuizLevelDropdown();

	QuizPanel(final UiTabsFactory rehearseTabs, final QuizLevel quizLevel) {
		super();
		this.rehearseTabs = rehearseTabs;
		this.quizLevel = quizLevel;

	}

	protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {
		mainTab.add(createQuizLevelDropdown());

		int i = 1;
		List<JButton> allPlayButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			JButton playButton = QuizButtons.FREQUENCY_SET_QUIZ.createPlayButtonForEachQuiz(mainTab, buttonYcoordinate(), eachQuiz,
					quizName, allPlayButtons);
			allPlayButtons.add(playButton);
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
