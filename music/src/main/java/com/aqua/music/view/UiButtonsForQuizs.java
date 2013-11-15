package com.aqua.music.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel.QuizSection;

public enum UiButtonsForQuizs implements UiButtons {
	FREQUENCY_SET_QUIZ("Play $$", "Click this to play $$") {
		@Override
		JButton createInstanceWith(final JPanel mainTab, final QuizSection<CyclicFrequencySet> quizSection, int buttonYcoordinate,
				String name) {
			JButton button = UiButtonsCommon.configurableNamedButton(this, name);
			final Collection<JButton> guessButtonsSet = new ArrayList<JButton>();
			int startLocation = X_COORIDNATE + MINI_BUTTON_WIDTH + 10;
			for (CyclicFrequencySet each : quizSection.quizItems()) {
				JButton guessButton = new JButton(each.freqSetNamePermuationAsText());
				guessButton.setBackground(Color.LIGHT_GRAY);

				guessButton.setBounds(startLocation, buttonYcoordinate, MINI_BUTTON_WIDTH, BUTTON_HEIGHT);
				startLocation = startLocation + MINI_BUTTON_WIDTH + 10;
				
				guessButton.setVisible(false);
				guessButton.setOpaque(true);
				mainTab.add(guessButton);
				
				guessButtonsSet.add(guessButton);
			}

			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CyclicFrequencySet playItem = quizSection.playItem();
					playItem.play(AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);

					for (JButton guessButton : guessButtonsSet) {
						for(ActionListener each:guessButton.getActionListeners()){
							guessButton.removeActionListener(each);
						}
						guessButton.addActionListener(guessActionListeners(guessButton, playItem, guessButtonsSet));
						guessButton.setBackground(Color.LIGHT_GRAY);
						guessButton.setVisible(true);
						guessButton.setEnabled(true);
						mainTab.repaint();
					}
				}
			};

			button.addActionListener(actionListener);
			return button;
		}
	};

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

	public void createJComponents(JPanel mainTab, int buttonYcoordinate, QuizSection<CyclicFrequencySet> each, String name) {
		int startLocation = X_COORIDNATE;
		JButton button = createInstanceWith(mainTab, each, buttonYcoordinate, name);
		button.setBounds(startLocation, buttonYcoordinate, MINI_BUTTON_WIDTH, BUTTON_HEIGHT);
		button.setOpaque(true);
		mainTab.add(button);

	}

	abstract JButton createInstanceWith(JPanel mainTab, QuizSection<CyclicFrequencySet> each, int buttonYcoordinate, String name);

	private final String text;
	private final String tooltip;

	private UiButtonsForQuizs(String text, String tooltip) {
		this.text = text;
		this.tooltip = tooltip;
	}

	@Override
	public String text() {
		return text;
	}

	@Override
	public String tooltip() {
		return tooltip;
	}
}
