package com.aqua.music.view.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;
import com.aqua.music.view.components.UiButtons.MusicButtons;

class QuizPanel extends AbstractMusicPanel {
	private final QuizLevel quizLevel;
	private final int increment = UiButtons.MINI_BUTTON_WIDTH + 10;
	private final int optionButtonsXLocation = UiButtons.X_COORIDNATE + increment;

	QuizPanel(final QuizLevel quizLevel) {
		super(false);
		this.quizLevel = quizLevel;
	}

	protected Collection<JComponent> addSpecificButtons() {
		final Collection<JComponent> result = new ArrayList<JComponent>();
		int i = 1;
		List<JButton> allButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			int buttonYcoordinate = buttonYcoordinate();

			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName, buttonYcoordinate);

			final HorizontallyAlignedButtons optionButtonsBuilder = new HorizontallyAlignedButtons(optionButtonsXLocation,
					buttonYcoordinate, increment, UiButtons.MusicButtons.CHOICE_OPTIONS);
			for (CyclicFrequencySet eachOption : eachQuiz.quizItems()) {
				optionButtonsBuilder.add(eachOption.name());
			}
			final Collection<JButton> multipleChoiceButtons = optionButtonsBuilder.all();
			for (JButton each : multipleChoiceButtons) {
				result.add(each);
			}

			quizPlayButton.addActionListener(new QuizPlayActionListener(eachQuiz, multipleChoiceButtons, allButtons));
			result.add(quizPlayButton);

			allButtons.add(quizPlayButton);
			i++;
		}
		
		JButton quitButton = MusicButtons.QUIT.createStaticNamedButton(buttonYcoordinate());
		result.add(quitButton);
		
		return result;
	}
}
