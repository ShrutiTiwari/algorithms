package com.aqua.music.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;

public enum UiButtonsForQuizs implements UiButtons {
	FREQUENCY_SET_QUIZ("Play $$", "Click this to play $$") {
		@Override
		JButton createInstanceWith(final JPanel mainTab, final Quiz<CyclicFrequencySet> quizSection, int buttonYcoordinate,
				String name, List<JButton> allPlayButtons) {
			JButton button = UiButtonsCommon.configurableNamedButton(this, name);
			final Collection<JButton> multipleChoiceSet = multipleChoiceSet(mainTab, quizSection, buttonYcoordinate);
			ActionListener actionListener = playButtonActionListener(mainTab, quizSection, multipleChoiceSet, allPlayButtons);
			button.addActionListener(actionListener);
			return button;
		}

		private Collection<JButton> multipleChoiceSet(final JPanel mainTab, final Quiz<CyclicFrequencySet> quizSection,
				int buttonYcoordinate) {
			final Collection<JButton> multipleChoiceSet = new ArrayList<JButton>();
			int startLocation = X_COORIDNATE + MINI_BUTTON_WIDTH + 10;
			for (CyclicFrequencySet each : quizSection.quizItems()) {
				JButton multipleChoiceButton = new JButton(each.freqSetNamePermuationAsText());
				multipleChoiceButton.setBackground(Color.LIGHT_GRAY);
				multipleChoiceButton.setBounds(startLocation, buttonYcoordinate, MINI_BUTTON_WIDTH, BUTTON_HEIGHT);
				startLocation = startLocation + MINI_BUTTON_WIDTH + 10;
				multipleChoiceButton.setVisible(false);
				multipleChoiceButton.setOpaque(true);
				mainTab.add(multipleChoiceButton);
				multipleChoiceSet.add(multipleChoiceButton);
			}
			return multipleChoiceSet;
		}

		private ActionListener playButtonActionListener(final JPanel mainTab, final Quiz<CyclicFrequencySet> quizSection,
				final Collection<JButton> multipleChoiceSet, final List<JButton> allPlayButtons) {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CyclicFrequencySet playItem = quizSection.playItem();
					playItem.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);

					for (JButton eachMultipleChoiceOption : multipleChoiceSet) {
						for(ActionListener each:eachMultipleChoiceOption.getActionListeners()){
							eachMultipleChoiceOption.removeActionListener(each);
						}
						eachMultipleChoiceOption.addActionListener(guessActionListeners(eachMultipleChoiceOption, playItem, multipleChoiceSet));
						eachMultipleChoiceOption.setBackground(Color.LIGHT_GRAY);
						eachMultipleChoiceOption.setVisible(true);
						eachMultipleChoiceOption.setEnabled(true);
						mainTab.repaint();
					}
				}
			};
		}
	};

	private final String text;

	private final String tooltip;

	private UiButtonsForQuizs(String text, String tooltip) {
		this.text = text;
		this.tooltip = tooltip;
	}

	public JButton createPlayButtonForEachQuiz(JPanel mainTab, int buttonYcoordinate, Quiz<CyclicFrequencySet> quiz, String quizName, List<JButton> allPlayButtons) {
		int startLocation = X_COORIDNATE;
		JButton playButton = createInstanceWith(mainTab, quiz, buttonYcoordinate, quizName, allPlayButtons);
		playButton.setBounds(startLocation, buttonYcoordinate, MINI_BUTTON_WIDTH, BUTTON_HEIGHT);
		playButton.setOpaque(true);
		mainTab.add(playButton);
		return playButton;

	}
	@Override
	public String text() {
		return text;
	}

	@Override
	public String tooltip() {
		return tooltip;
	}

	abstract JButton createInstanceWith(JPanel mainTab, Quiz<CyclicFrequencySet> each, int buttonYcoordinate, String name, List<JButton> allPlayButtons);

	ActionListener guessActionListeners(final JButton selectedButton, final CyclicFrequencySet playItem,final Collection<JButton> guessButtonsSet) {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedButtonText = selectedButton.getText();
				String correctAnswer = playItem.freqSetNamePermuationAsText();
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
