package com.aqua.music.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.items.PlayableItem;
import com.aqua.music.model.FrequencySet;

public class DisplayItemFactory {
	static final int HORIZONAL_COORIDNATE = 30;
	static final int BUTTON_WIDTH = 400;
	static final int BUTTON_HEIGHT = 30;
	static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
	static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);

	private int verticalIndex = 10;

	JButton createWith(DisplayItemType buttonType, Object arg) {
		JButton displayItem = buttonType.createInstanceWith(arg);
		displayItem.setBounds(HORIZONAL_COORIDNATE, verticalIndex(),
				buttonType.width(), BUTTON_HEIGHT);
		return displayItem;
	}

	private int verticalIndex() {
		verticalIndex += (BUTTON_HEIGHT) + 10;
		return verticalIndex;
	}
	
	static class ActionListenerBuilder {
		private final Object arg;

		ActionListenerBuilder(Object arg) {
			this.arg = arg;
		}

		ActionListener actionListener(DisplayItemType displayItemType) {
			switch (displayItemType) {
			case PLAYABLE:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						PlayableItem.factory.forSet((FrequencySet) arg)
								.playWithoutBlocking();
					}
				};
			case QUIT:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				};
			}
			return null;
		}
	}
}
