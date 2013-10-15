package com.aqua.music.ui;

import java.awt.Dimension;

import javax.swing.JButton;

public class DisplayItemFactory {
	static final int HORIZONAL_COORIDNATE = 30;
	static final int BUTTON_WIDTH = 400;
	static final int BUTTON_HEIGHT = 30;
	static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
	static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);

	private int verticalIndex = 10;

	JButton createWith(DisplayItemType buttonType, Object arg) {
		JButton displayItem = buttonType.creteDisplayItem(arg);
		verticalIndex += (BUTTON_HEIGHT) + 10;
		displayItem.setBounds(HORIZONAL_COORIDNATE, verticalIndex,
				buttonType.width(), BUTTON_HEIGHT);
		return displayItem;
	}
}
