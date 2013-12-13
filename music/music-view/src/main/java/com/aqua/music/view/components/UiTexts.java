/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import open.music.api.PlayApi;

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
		PRACTICE_A_THAAT("Choose a thaat or 'play all'", FONT_SIZE.MEDIUM),
		PRACTICE_A_SONG("Choose a song or 'play all'", FONT_SIZE.MEDIUM),
		PRACTICE_A_PATTERN("Choose a pattern or 'play all'", FONT_SIZE.MEDIUM),
		MESSAGE_TOP("Hello, Great to see you here.", FONT_SIZE.LARGE),
		INSTRUMENT_LABEL("Change instrument for ", FONT_SIZE.MEDIUM),
		
		STATUS_INSTRUMENT("Instrument[ " + PlayApi.defaultInstrument().getName() + "  ]", FONT_SIZE.SMALL),
		STATUS_SPEED("Speed[ 0 ]", FONT_SIZE.SMALL),
		STATUS_PLAYABLE("Playing[ - ]", FONT_SIZE.SMALL);

		private final JLabel label;

		private UiLables(String text, FONT_SIZE fontSize) {
			this.label = new JLabel(text);
			label.setFont(new Font(null, Font.PLAIN, fontSize.size));
			if(fontSize==FONT_SIZE.SMALL){
				label.setFont(new Font(null, Font.BOLD, fontSize.size));
			}
			label.setForeground(Color.WHITE);
		}

		JLabel getLabel() {
			return label;
		}
		
		enum FONT_SIZE{
			SMALL(14), MEDIUM(20), LARGE(30);
			private final int size;
			private FONT_SIZE(int size) {
				this.size = size;
			}
		}
	}
}
