package com.aqua.music.view.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;
import com.aqua.music.view.action.listeners.QuizPlayActionListener;

enum QuizButtons implements UiButtons {
	FREQUENCY_SET_QUIZ("Play $$", "Click this to play $$") {
		@Override
		JButton createInstanceWith(final JPanel mainTab, final Quiz<CyclicFrequencySet> quizSection, int buttonYcoordinate, String name,
				List<JButton> allPlayButtons) {
			JButton button = CommonButtons.configurableNamedButton(this, name);
			final Collection<JButton> multipleChoiceSet = multipleChoiceSet(mainTab, quizSection, buttonYcoordinate);
			button.addActionListener(new QuizPlayActionListener(mainTab, quizSection, multipleChoiceSet, allPlayButtons));
			return button;
		}

		private Collection<JButton> multipleChoiceSet(final JPanel mainTab, final Quiz<CyclicFrequencySet> quizSection,
				int buttonYcoordinate) {
			final Collection<JButton> multipleChoiceSet = new ArrayList<JButton>();
			int startLocation = X_COORIDNATE + MINI_BUTTON_WIDTH + 10;
			for (CyclicFrequencySet each : quizSection.quizItems()) {
				JButton multipleChoiceButton = new JButton(each.name());
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
	};

	private final String text;

	private final String tooltip;

	private QuizButtons(String text, String tooltip) {
		this.text = text;
		this.tooltip = tooltip;
	}

	public JButton createPlayButtonForEachQuiz(JPanel mainTab, int buttonYcoordinate, Quiz<CyclicFrequencySet> quiz, String quizName,
			List<JButton> allPlayButtons) {
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

	abstract JButton createInstanceWith(JPanel mainTab, Quiz<CyclicFrequencySet> each, int buttonYcoordinate, String name,
			List<JButton> allPlayButtons);

}
