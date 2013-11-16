package com.aqua.music.controller;

import java.awt.TextArea;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.action.listeners.PlayAllSongsActionListener;
import com.aqua.music.action.listeners.PlaySingleItemActionListener;
import com.aqua.music.model.song.Song;

enum UiButtonsForSong {
	SONG_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Song[] songs) {
			final Song song = songs[0];
			final String buttonTitle = song.name();
			JButton button = configurableNamedButton(this, buttonTitle);
			button.addActionListener(new PlaySingleItemActionListener(textArea,song));
			return button;
		}
	},	PLAY_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Song[] songs) {
			JButton button = fixedNameButton(this);
			button.addActionListener(new PlayAllSongsActionListener(textArea,songs));
			return button;
		}
	};
	private static final Logger logger = LoggerFactory.getLogger(UiButtonsForSong.class);
	static final int X_COORIDNATE = 30;
	private static final int BUTTON_HEIGHT = 30;
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiButtonsForSong(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	static final int HEIGHT() {
		return BUTTON_HEIGHT;
	}

	private static JButton configurableNamedButton(UiButtonsForSong itemType, String replaceName) {
		JButton resultButton = new JButton(itemType.text.replace("$$", replaceName));
		resultButton.setToolTipText(itemType.tooltip.replace("$$", replaceName));
		return resultButton;
	}

	private static JButton fixedNameButton(UiButtonsForSong itemType) {
		JButton resultButton = new JButton(itemType.text);
		resultButton.setToolTipText(itemType.tooltip);
		return resultButton;
	}

	private static void setText(final TextArea textArea, final String name) {
		String displayText = "\n\n Playing::" + name;
		logger.info(displayText);
		textArea.setText(displayText);
	}

	public JButton createButton(TextArea textArea, int yCoordinate, Song... song) {
		JButton buttonItem = createInstanceWith(textArea, song);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, Song[] song);
}