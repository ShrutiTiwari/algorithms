package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;

import open.music.api.PlayApi;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;

/**
 * @author "Shruti Tiwari"
 * 
 */
class QuizPlayActionListener implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(QuizPlayActionListener.class);
	private final Collection<JButton> multipleChoiceSet;
	private final Quiz<CyclicFrequencySet> quizSection;
	private final MusicPanel musicPanelForQuiz;
	private final StateDependentUi stateDependentUi;
	private final PlayApi playApi=SingletonFactory.PLAY_API;

	public QuizPlayActionListener(MusicPanel musicPanelForQuiz, Quiz<CyclicFrequencySet> quizSection, Collection<JButton> multipleChoiceSet, StateDependentUi stateDependentUi) {
		this.musicPanelForQuiz = musicPanelForQuiz;
		this.quizSection = quizSection;
		this.multipleChoiceSet = multipleChoiceSet;
		this.stateDependentUi=stateDependentUi;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CyclicFrequencySet playItem = quizSection.playItem();
		stateDependentUi.updatePlayable(((JButton)arg0.getSource()).getText());
		playApi.play(playItem);

		for (JButton eachMultipleChoiceOption : multipleChoiceSet) {
			for (ActionListener each : eachMultipleChoiceOption.getActionListeners()) {
				eachMultipleChoiceOption.removeActionListener(each);
			}
			eachMultipleChoiceOption.addActionListener(guessActionListeners(eachMultipleChoiceOption, playItem, multipleChoiceSet));
			eachMultipleChoiceOption.setBackground(Color.LIGHT_GRAY);
			eachMultipleChoiceOption.setVisible(true);
			eachMultipleChoiceOption.setEnabled(true);
			musicPanelForQuiz.repaint();
		}
	}

	ActionListener guessActionListeners(final JButton selectedButton, final CyclicFrequencySet playItem,
			final Collection<JButton> guessButtonsSet) {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedButtonText = selectedButton.getText();
				String correctAnswer = playItem.name();
				if (selectedButtonText.equals(correctAnswer)) {
					selectedButton.setBackground(Color.GREEN);
					for (JButton each : guessButtonsSet) {
						each.setEnabled(false);
					}
				} else {
					logger.info(" :( Wrong guess... clickedButton[" + selectedButtonText + "] correctAnswer[" + correctAnswer + "]");
					selectedButton.setBackground(Color.YELLOW);
					selectedButton.setEnabled(false);
				}
			}
		};
		return actionListener;
	}
}
