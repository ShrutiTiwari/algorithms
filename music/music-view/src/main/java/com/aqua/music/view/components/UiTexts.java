/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * @author "Shruti Tiwari"
 * 
 */
interface UiTexts {
	String TITLE_PRACTICE = "Practice";
	String TITLE_PUZZLES = "Puzzles";
	String TITLE_SONG_TAB = "Songs";
	String TITLE_THAAT_PATTERN = "Patterns";
	String TITLE_THAAT = "Thaats";

	enum UiLables {
		PRACTICE_A_THAAT("Choose a thaat or 'play all'"),
		PRACTICE_A_SONG("Choose a song or 'play all'"),
		PRACTICE_A_PATTERN("Choose a pattern or 'play all'"),
		MESSAGE_TOP("Hello, Great to see you here."),
		INSTRUMENT_LABEL("Change instrument for ");

		private final JLabel label;

		private UiLables(String text) {
			this.label = new JLabel(text);
			label.setFont(new Font(null, Font.PLAIN, 20));
			label.setForeground(Color.WHITE);
		}

		JLabel getLabel() {
			return label;

		}
	}
}
