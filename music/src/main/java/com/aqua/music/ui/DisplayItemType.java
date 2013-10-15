package com.aqua.music.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.FrequencySet;

public enum DisplayItemType {
	PLAYABLE("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton creteDisplayItem(Object arg) {
			String setName = ((FrequencySet) arg).name();
			JButton playableButton = new JButton(this.text.replace("$$",
					setName));
			playableButton.setToolTipText(tooltip.replace("$$", setName));
			playableButton.addActionListener(new ActionListenerBuilder(arg)
					.playableItemsActionListener());
			return playableButton;
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		public JButton creteDisplayItem(Object arg) {
			JButton quitButton = new JButton(text);
			quitButton.setToolTipText(tooltip);
			quitButton.addActionListener(new ActionListenerBuilder(arg)
					.quitActionListener());
			return quitButton;
		}
	};
	final String text;
	final String tooltip;
	private final int displayWidth;

	DisplayItemType(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;
	}

	public abstract JButton creteDisplayItem(Object arg);

	public int width() {
		return displayWidth;
	}

	public class ActionListenerBuilder {
		private final Object arg;

		private ActionListenerBuilder(Object arg) {
			this.arg = arg;
		}

		private ActionListener playableItemsActionListener() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PlayableItem.factory.forSet((FrequencySet) arg)
							.playWithoutBlocking();
				}
			};
		}

		private ActionListener quitActionListener() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			};
		}
	}

}
