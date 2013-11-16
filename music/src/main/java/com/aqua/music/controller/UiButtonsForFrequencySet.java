package com.aqua.music.controller;

import static com.aqua.music.controller.UiButtonsCommon.configurableNamedButton;
import static com.aqua.music.controller.UiButtonsCommon.fixedNameButton;

import java.awt.TextArea;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.action.listeners.PlayAllFrequencySetsActionListener;
import com.aqua.music.action.listeners.PlaySingleItemActionListener;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;

public enum UiButtonsForFrequencySet implements UiButtons{
	FREQUENCY_SET_PATTERNED_PLAYER("Play $$", "Click this to play $$", 300) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final CyclicFrequencySet[] frequencySequences) {
			final CyclicFrequencySet frequencySequence = frequencySequences[0];
			JButton button = configurableNamedButton(this, frequencySequence.name());
			button.addActionListener(new PlaySingleItemActionListener(textArea,frequencySequence));
			return button;
		}
	},
	FREQUENCY_SET_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, CyclicFrequencySet[] frequencySequences) {
			final CyclicFrequencySet frequencySequence = frequencySequences[0];
			JButton button = configurableNamedButton(this, frequencySequence.name());
			button.addActionListener(new PlaySingleItemActionListener(textArea,frequencySequence));
			return button;
		}
	},
	PLAY_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final CyclicFrequencySet[] frequencySequences) {
			JButton button = fixedNameButton(this);
			button.addActionListener(new PlayAllFrequencySetsActionListener(textArea, frequencySequences));
			return button;
		}
	};
	private static final Logger logger = LoggerFactory.getLogger(UiButtonsForFrequencySet.class);
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiButtonsForFrequencySet(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	@Override
	public String text() {
		return text;
	}
	
	@Override
	public String tooltip() {
		return tooltip;
	}
	static final int HEIGHT() {
		return BUTTON_HEIGHT;
	}

	private static void setText(final TextArea textArea, final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		textArea.setText(displayText);
	}

	public JButton createButton(TextArea textArea, int yCoordinate, CyclicFrequencySet[] frequencySequences) {
		JButton buttonItem = createInstanceWith(textArea, frequencySequences);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, CyclicFrequencySet[] frequencySequences);
}