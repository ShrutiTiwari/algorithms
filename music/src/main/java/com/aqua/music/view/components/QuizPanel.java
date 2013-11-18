package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;

class QuizPanel extends AbstractMusicPanel {
	private final QuizLevel quizLevel;
	private final JComboBox quizLevelsDD = createQuizLevelDropdown();

	private final UiTabsFactory rehearseTabs;

	QuizPanel(final UiTabsFactory rehearseTabs, final QuizLevel quizLevel) {
		super(false);
		this.rehearseTabs = rehearseTabs;
		this.quizLevel = quizLevel;

	}

	protected void addSpecificButtons(final JPanel mainPanel) {
		mainPanel.add(createQuizLevelDropdown());

		int i = 1;
		List<JButton> allPlayButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			int buttonYcoordinate = buttonYcoordinate();

			JButton playButton = UiButtons.Static.QUIZ_PLAY.createButton(quizName, UiButtons.X_COORIDNATE, buttonYcoordinate);
			final Collection<JButton> multipleChoiceSet = multipleChoiceOptions(eachQuiz, buttonYcoordinate);
			for (JButton each : multipleChoiceSet) {
				mainPanel.add(each);
			}
			playButton.addActionListener(new QuizPlayActionListener(mainPanel, eachQuiz, multipleChoiceSet, allPlayButtons));
			playButton.setOpaque(true);
			mainPanel.add(playButton);

			allPlayButtons.add(playButton);
			i++;
		}
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

	private JButton multipleChoiceOptionButton(int buttonYcoordinate, int startLocation, String name) {
		JButton multipleChoiceButton = new JButton(name);
		multipleChoiceButton.setBackground(Color.LIGHT_GRAY);
		multipleChoiceButton.setBounds(startLocation, buttonYcoordinate, UiButtons.MINI_BUTTON_WIDTH, UiButtons.BUTTON_HEIGHT);
		multipleChoiceButton.setVisible(false);
		multipleChoiceButton.setOpaque(true);
		return multipleChoiceButton;
	}

	private Collection<JButton> multipleChoiceOptions(final Quiz<CyclicFrequencySet> quizSection, int buttonYcoordinate) {
		final Collection<JButton> multipleChoiceSet = new ArrayList<JButton>();
		int startLocation = UiButtons.X_COORIDNATE + UiButtons.MINI_BUTTON_WIDTH + 10;
		for (CyclicFrequencySet eachOption : quizSection.quizItems()) {
			JButton multipleChoiceButton = multipleChoiceOptionButton(buttonYcoordinate, startLocation, eachOption.name());
			multipleChoiceSet.add(multipleChoiceButton);
			startLocation = startLocation + UiButtons.MINI_BUTTON_WIDTH + 10;
		}
		return multipleChoiceSet;
	}
}
