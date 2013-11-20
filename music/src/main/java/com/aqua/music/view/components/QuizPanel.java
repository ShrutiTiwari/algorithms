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
	private final int increment = UiButtons.MINI_BUTTON_WIDTH + 10;
	private final int optionButtonsXLocation = UiButtons.X_COORIDNATE + increment;

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
			
			final HorizontallyAlignedButtons optionButtonsBuilder = new HorizontallyAlignedButtons(optionButtonsXLocation, buttonYcoordinate,
					increment, UiButtons.MusicButtons.CHOICE_OPTIONS);
			for (CyclicFrequencySet eachOption : eachQuiz.quizItems()) {
				optionButtonsBuilder.add(eachOption.name());
			}
			final Collection<JButton> multipleChoiceButtons = optionButtonsBuilder.all();
			for (JButton each : multipleChoiceButtons) {
				mainPanel.add(each);
			}
			
			quizPlayButton.addActionListener(new QuizPlayActionListener(mainPanel, eachQuiz, multipleChoiceButtons,
					allPlayButtons));
			mainPanel.add(quizPlayButton);

			allPlayButtons.add(quizPlayButton);
			i++;
		}
	}
}
