package com.aqua.music.ui.desktop;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded use.
 * */

class UiComponents {
	private static final int HORIZONAL_COORIDNATE = 30;
	private static final int BUTTON_WIDTH = 400;
	private static final int BUTTON_HEIGHT = 30;

	static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
	static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);

	//mutated variable
	private int verticalIndex = 10;

	void reset(){
		this.verticalIndex = 10;
	}
	
	JButton buttonInstance(GuiButtons buttonType, Object[] arg) {
		JButton buttonItem = buttonType.createButton(arg);
		// set bounds
		buttonItem.setBounds(HORIZONAL_COORIDNATE, nextVerticalIndex(), buttonType.width(), BUTTON_HEIGHT);
		return buttonItem;
	}

	private int nextVerticalIndex() {
		verticalIndex += (BUTTON_HEIGHT) + 10;
		return verticalIndex;
	}
}
