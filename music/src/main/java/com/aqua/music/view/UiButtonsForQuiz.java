package com.aqua.music.view;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.aqua.music.bo.audio.manager.AudioPlayConfig;
import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.controller.puzzles.QuizLevel.QuizSection;

public enum UiButtonsForQuiz implements UiButtons{
	FREQUENCY_SET_QUIZ("Play $$", "Click this to play $$", 400) {
		@Override
		JButton createInstanceWith(final JLabel jLabel, QuizSection<CyclicFrequencySet>... quizSections) {
			final QuizSection<CyclicFrequencySet> quizSection = quizSections[0];
			
			StringBuffer buf= new StringBuffer();
			for(CyclicFrequencySet each: quizSection.quizItems()){
				buf.append(each.freqSetNamePermuationAsText()+"::");
			}
			
			ActionListener actionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CyclicFrequencySet playItem = quizSection.playItem();
					playItem.play( AudioPlayConfig.ASYNCHRONOUS_DYNAMIC_PLAYER);
					jLabel.setText(playItem.freqSetNamePermuationAsText());
				}
			};
			JButton button = UiButtonsForFrequencySet.configurableNamedButton(this, buf.toString());
			button.addActionListener(actionListener);

			return button;
		}
	};

	public JButton createButton(JLabel jLabel, int buttonYcoordinate, QuizSection<CyclicFrequencySet> each) {
		JButton buttonItem = createInstanceWith(jLabel, each);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, buttonYcoordinate, BUTTON_WIDTH, BUTTON_HEIGHT);
		//jLabel.setBounds(X_COORIDNATE+BUTTON_WIDTH+100, buttonYcoordinate, BUTTON_WIDTH, BUTTON_HEIGHT);
		jLabel.setBounds(X_COORIDNATE +BUTTON_WIDTH+300, 300, 100, UiButtons.BUTTON_WIDTH);
		return buttonItem;
	}
	
	abstract JButton createInstanceWith(JLabel jLabel, QuizSection<CyclicFrequencySet>... each);
	private final String text;
	private final String tooltip;

	private UiButtonsForQuiz(String text, String tooltip, int buttonWidth) {
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
