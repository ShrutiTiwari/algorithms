package com.aqua.music.view.components;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;

/**
 * @author "Shruti Tiwari"
 * 
 */
class MusicPanelForQuiz extends MusicPanel {
	private final QuizLevel initialQuizLevel;
	private JPanel specificComponentsPanel;

	MusicPanelForQuiz(final QuizLevel initialQuizLevel) {
		super(true);
		this.initialQuizLevel = initialQuizLevel;
		final JComboBox quizDropdown = UiDropdown.quizDropdown(initialQuizLevel);
		quizDropdown.addActionListener(new UiDropdown.QuizDropdownActionListener(this));
		addExtraComponents(quizDropdown);
	}

	protected JPanel createSpecificComponentPanel(final Object selectedObject) {
		specificComponentsPanel = MusicPanels.LEFT_FLOWLAYOUT.createPanel();
		specificComponentsPanel.add(Box.createVerticalStrut(100));

		QuizLevel quizLevel = (QuizLevel) selectedObject;
		if (quizLevel == null) {
			quizLevel = this.initialQuizLevel;
		}
		int i = 1;
		for (Quiz<CyclicFrequencySet> eachQuiz : (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections()) {
			final String quizName = "Quiz " + i;
			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName);

			final Collection<JButton> multipleChoiceButtons = new ArrayList<JButton>();
			for (CyclicFrequencySet eachOption : eachQuiz.quizItems()) {
				JButton b = UiButtons.MusicButtons.CHOICE_OPTIONS.createDynamicNamedButton(eachOption.name());
				multipleChoiceButtons.add(b);
				specificComponentsPanel.add(b);
			}
			quizPlayButton.addActionListener(new QuizPlayActionListener(this, eachQuiz, multipleChoiceButtons));

			specificComponentsPanel.add(quizPlayButton);
			i++;
		}

		return specificComponentsPanel;
	}

	public void repaint() {
		specificComponentsPanel.repaint();
	}
}
