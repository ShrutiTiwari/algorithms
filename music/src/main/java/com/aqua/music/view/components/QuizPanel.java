package com.aqua.music.view.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;

class QuizPanel extends AbstractMusicPanel {
	private final QuizLevel quizLevel;

	QuizPanel(final QuizLevel quizLevel) {
		super(false);
		this.quizLevel = quizLevel;
	}

	protected void addSpecificButtons(final JPanel mainPanel) {
		int i = 1;
		List<JButton> allPlayButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			int buttonYcoordinate = buttonYcoordinate();

			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName, buttonYcoordinate);
			final Collection<JButton> groupOfButtonsForMultipleChoices = groupOfButtonsForMultipleChoices(eachQuiz, buttonYcoordinate);
			for (JButton each : groupOfButtonsForMultipleChoices) {
				mainPanel.add(each);
			}
			quizPlayButton.addActionListener(new QuizPlayActionListener(mainPanel, eachQuiz, groupOfButtonsForMultipleChoices, allPlayButtons));
			mainPanel.add(quizPlayButton);

			allPlayButtons.add(quizPlayButton);
			i++;
		}
	}

	private Collection<JButton> groupOfButtonsForMultipleChoices(final Quiz<CyclicFrequencySet> quizSection, int buttonYcoordinate) {
		final Collection<JButton> multipleChoiceSet = new ArrayList<JButton>();
		int startLocation = UiButtons.X_COORIDNATE + UiButtons.MINI_BUTTON_WIDTH + 10;
		for (CyclicFrequencySet eachOption : quizSection.quizItems()) {
			JButton multipleChoiceButton = UiButtons.MusicButtons.CHOICE_OPTIONS.createDynamicNamedButton(eachOption.name(), startLocation,
					buttonYcoordinate);
			multipleChoiceSet.add(multipleChoiceButton);
			startLocation = startLocation + UiButtons.MINI_BUTTON_WIDTH + 10;
		}
		return multipleChoiceSet;
	}
}
