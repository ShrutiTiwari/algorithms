package com.aqua.music.ui;

import javax.swing.JButton;

import com.aqua.music.model.FrequencySet;

public enum DisplayItemType {
	PLAYABLE("Play $$", "Click this to play $$", 200) {
		@Override
		public JButton createInstanceWith(Object arg) {
			return dynamicButton(this, arg, ((FrequencySet) arg).name());
		}
	},
	QUIT("Quit", "Click this to quit!", 400) {
		@Override
		public JButton createInstanceWith(Object arg) {
			staticButton.addActionListener(new DisplayItemFactory.ActionListenerBuilder(null)
			.actionListener(this));
			return this.staticButton;
		}
	};
	private final String text;
	private final String tooltip;
	private final int displayWidth;
	JButton staticButton;

	DisplayItemType(String text, String tooltip, int buttonWidth) {
		this.text = text;
		this.tooltip = tooltip;
		this.displayWidth = buttonWidth;

		if (!text.contains("$$")) {
			this.staticButton = new JButton(text);
			staticButton.setToolTipText(tooltip);
		}
	}

	public abstract JButton createInstanceWith(Object arg);

	private static JButton dynamicButton(DisplayItemType itemType, Object arg,
			String replaceName) {
		JButton resultButton = new JButton(itemType.text.replace("$$",
				replaceName));
		resultButton
				.setToolTipText(itemType.tooltip.replace("$$", replaceName));
		resultButton.addActionListener(new DisplayItemFactory.ActionListenerBuilder(arg)
				.actionListener(itemType));
		return resultButton;
	}

	public int width() {
		return displayWidth;
	}



}
