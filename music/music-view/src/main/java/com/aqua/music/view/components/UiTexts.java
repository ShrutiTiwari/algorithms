/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import open.music.api.SingletonFactory;

/**
 * @author "Shruti Tiwari"
 * 
 */
interface UiTexts {
	String TITLE_PRACTICE = "Practice";
	String TITLE_PUZZLES = "Puzzles";

	enum UiLables {
		CURRENT_CUSTOMIZATION("Playing[S] - [S3]", FONT_SIZE.SMALL),
		INSTRUMENT_LABEL("Change instrument for ", FONT_SIZE.SMALL),
		MESSAGE_TOP("Hello, Great to see you here.", FONT_SIZE.LARGE),
		PRACTICE_A_PATTERN("Choose a pattern or 'play all'", FONT_SIZE.MEDIUM),
		PRACTICE_A_SONG("Choose a song or 'play all'", FONT_SIZE.MEDIUM),
		
		PRACTICE_A_THAAT("Choose a thaat or 'play all'", FONT_SIZE.MEDIUM),
		STATUS_INSTRUMENT("Instrument[ " + SingletonFactory.PLAY_API.defaultInstrument() + "  ]", FONT_SIZE.SMALL),
		STATUS_PLAYABLE("Playing[ - ]", FONT_SIZE.SMALL), STATUS_SPEED("Speed[ 0 ]", FONT_SIZE.SMALL);

		private final JLabel label;
		private final String text;
		private final FONT_SIZE fontSize;

		private UiLables(String text, FONT_SIZE fontSize) {
			this.text=text;
			this.fontSize=fontSize;
			this.label=createLabel(text, fontSize);
		}

		JLabel getLabel() {
			return label;
		}

		JLabel newLabel() {
			return createLabel(this.text, fontSize);
		}

		private JLabel createLabel(String text, FONT_SIZE fontSize) {
			JLabel label1 = new JLabel(text);
			label1.setFont(new Font(null, Font.PLAIN, fontSize.size));
			if(fontSize==FONT_SIZE.SMALL){
				label1.setFont(new Font(null, Font.BOLD, fontSize.size));
			}
			label1.setForeground(Color.WHITE);
			return label1;
		}
		enum FONT_SIZE{
			LARGE(30), MEDIUM(20), SMALL(14);
			private final int size;
			private FONT_SIZE(int size) {
				this.size = size;
			}
		}
	}
}
