package com.aqua.music.view.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;
import com.aqua.music.view.components.UiButtons.MusicButtons;
import com.aqua.music.view.components.UiDropdown.PaneReloadDropdownActionListener;

/**
 * @author "Shruti Tiwari"
 * 
 */
class QuizPanel extends AbstractMusicPanel {
	private final QuizLevel quizLevel;
	private final int increment = UiButtons.MINI_BUTTON_WIDTH + 10;
	private final int optionButtonsXLocation = UiButtons.X_COORIDNATE + increment;

	QuizPanel(final QuizLevel quizLevel) {
		super();
		this.quizLevel = quizLevel;
		final JComboBox quizDropdown = UiDropdown.quizDropdown(quizLevel);
		quizDropdown.addActionListener(new PaneReloadDropdownActionListener(this));
		addToCommonComponentPanel(quizDropdown);
	}

	protected JPanel createSpecificComponentPanel(final Object selectedObject) {
		QuizLevel quizLevel = (QuizLevel) selectedObject;
		if (quizLevel == null) {
			quizLevel = this.quizLevel;
		}
		final Collection<JComponent> allButtonsIncludingQuizAndChoice = new ArrayList<JComponent>();
		int i = 1;
		List<JButton> allQuizPlayButtons = new ArrayList<JButton>();
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName);

			final Collection<JButton> multipleChoiceButtons = new ArrayList<JButton>();
			for (CyclicFrequencySet eachOption : eachQuiz.quizItems()) {
				JButton b = UiButtons.MusicButtons.CHOICE_OPTIONS.createDynamicNamedButton(eachOption.name());
				allButtonsIncludingQuizAndChoice.add(b);
				multipleChoiceButtons.add(b);

			}
			quizPlayButton.addActionListener(new QuizPlayActionListener(eachQuiz, multipleChoiceButtons, allQuizPlayButtons));
			allButtonsIncludingQuizAndChoice.add(quizPlayButton);

			allQuizPlayButtons.add(quizPlayButton);
			i++;
		}

		JButton quitButton = MusicButtons.QUIT.createStaticNamedButton();
		allButtonsIncludingQuizAndChoice.add(quitButton);

		JPanel result = new JPanel();
		// result.setPreferredSize(new Dimension(400, 300));
		for (JComponent each : allButtonsIncludingQuizAndChoice) {
			result.add(each);
		}

		return result;
	}
}
