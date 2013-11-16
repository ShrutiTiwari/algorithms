package com.aqua.music.view.helper;

import static com.aqua.music.view.helper.UiButtonsCommon.configurableNamedButton;
import static com.aqua.music.view.helper.UiButtonsCommon.fixedNameButton;

import java.awt.TextArea;

import javax.swing.JButton;

import com.aqua.music.controllers.PlayAllItemsActionListener;
import com.aqua.music.controllers.PlaySingleItemActionListener;
import com.aqua.music.model.cyclicset.Playable;

enum UiButtonsForRehearsing implements UiButtons{
	SINGLE_ITEM_PLAYER("Play $$", "Click this to play $$", 200) {
		@Override
		JButton createInstanceWith(final TextArea textArea, Playable[] playables) {
			final Playable playable = playables[0];
			JButton button = configurableNamedButton(this, playable.name());
			button.addActionListener(new PlaySingleItemActionListener(textArea,playable));
			return button;
		}
	},
	PLAYER_FOR_ALL("PLAY_ALL", "Click this to play all!", 400) {
		@Override
		JButton createInstanceWith(final TextArea textArea, final Playable[] playables) {
			JButton button = fixedNameButton(this);
			button.addActionListener(new PlayAllItemsActionListener(textArea, playables));
			return button;
		}
	};
	private final int displayWidth;
	private final String text;
	private final String tooltip;

	private UiButtonsForRehearsing(String text, String tooltip, int buttonWidth) {
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

	public JButton createButton(TextArea textArea, int yCoordinate, Playable... playableItems) {
		JButton buttonItem = createInstanceWith(textArea, playableItems);
		buttonItem.setOpaque(true);
		buttonItem.setBounds(X_COORIDNATE, yCoordinate, displayWidth, BUTTON_HEIGHT);
		return buttonItem;
	}

	abstract JButton createInstanceWith(TextArea textArea, Playable[] playables);
}