package com.aqua.music.view.action.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.UIMainPanel;

public class QuizPlayActionListener implements ActionListener {
	private final List<JButton> allPlayButtons;
	private final Collection<JButton> multipleChoiceSet;
	private final Quiz<CyclicFrequencySet> quizSection;
	
	public QuizPlayActionListener(Quiz<CyclicFrequencySet> quizSection, Collection<JButton> multipleChoiceSet, List<JButton> allPlayButtons) {
		this.quizSection = quizSection;
		this.multipleChoiceSet = multipleChoiceSet;
		this.allPlayButtons = allPlayButtons;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CyclicFrequencySet playItem = quizSection.playItem();
		AudioPlayerSettings.ASYNCHRONOUS_DYNAMIC_PLAYER.play(playItem.frequencies());

		for (JButton eachMultipleChoiceOption : multipleChoiceSet) {
			for (ActionListener each : eachMultipleChoiceOption.getActionListeners()) {
				eachMultipleChoiceOption.removeActionListener(each);
			}
			eachMultipleChoiceOption.addActionListener(guessActionListeners(eachMultipleChoiceOption, playItem, multipleChoiceSet));
			eachMultipleChoiceOption.setBackground(Color.LIGHT_GRAY);
			eachMultipleChoiceOption.setVisible(true);
			eachMultipleChoiceOption.setEnabled(true);
			UIMainPanel.repaintSelectedTab();
		}
	}
	
	ActionListener guessActionListeners(final JButton selectedButton, final CyclicFrequencySet playItem,final Collection<JButton> guessButtonsSet) {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedButtonText = selectedButton.getText();
				String correctAnswer = playItem.name();
				if (selectedButtonText == correctAnswer) {
					System.out.println(" :) Correct guess!!! clickedButton[" + selectedButtonText + "] correctAnswer[" + correctAnswer + "]");
					selectedButton.setBackground(Color.GREEN);
					for(JButton each:guessButtonsSet){
							each.setEnabled(false);
					}
				} else {
					System.out.println(" :( Wrong guess... clickedButton[" + selectedButtonText + "] correctAnswer[" + correctAnswer + "]");
					selectedButton.setBackground(Color.YELLOW);
					selectedButton.setEnabled(false);
				}
			}
		};
		return actionListener;
	}
}
