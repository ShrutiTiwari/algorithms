package com.aqua.music.ui.desktop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.aqua.music.audio.player.AudioPlayer.AudioPlayerType;
import com.aqua.music.items.PlayableItem;
import com.aqua.music.items.PlayableItem.AudioPlayerConfiguration;
import com.aqua.music.items.SymmetricalPatternApplicator;
import com.aqua.music.model.Frequency;
import com.aqua.music.model.FrequencySet;

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

	JButton buttonInstance(GuiItemType buttonType, Object[] arg) {
		JButton buttonItem = buttonType.createInstanceWith(arg);

		// set listener
		ActionListener actionListener = new UiComponents.ActionListenerBuilder(arg).actionListener(buttonType);
		buttonItem.addActionListener(actionListener);
		buttonItem.setOpaque(true);
		// set bounds
		buttonItem.setBounds(HORIZONAL_COORIDNATE, nextVerticalIndex(), buttonType.width(), BUTTON_HEIGHT);

		return buttonItem;
	}

	private int nextVerticalIndex() {
		verticalIndex += (BUTTON_HEIGHT) + 10;
		return verticalIndex;
	}

	static class ActionListenerBuilder {
		private final Object[] arg;

		ActionListenerBuilder(Object[] arg) {
			this.arg = arg;
		}

		ActionListener actionListener(GuiItemType displayItemType) {
			switch (displayItemType) {
			case PLAY_ALL_TO_INFINITY:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for (Object each : arg) {
							FrequencySet freqSet = (FrequencySet) each;
							System.out.println("Playing::" + freqSet.name());
							PlayableItem.blockingFrequencyPlayerConfig.forSet(freqSet).play();
						}
					}
				};
			case PLAYABLE:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						FrequencySet freqSet = (FrequencySet) arg[0];
						System.out.println("Playing::" + freqSet.name());
						PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet).play();
					}
				};
			case QUIT:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				};
			case PLAYABLE_PATTERN:
				return new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						FrequencySet freqSet = (FrequencySet) arg[0];
						SymmetricalPatternApplicator<Frequency> pattern = new SymmetricalPatternApplicator<Frequency>((int[]) arg[1]);
						System.out.println("Playing::" + freqSet.name());
						PlayableItem.nonBlockingFrequencyPlayerConfig.forSet(freqSet).andPattern(pattern).play();
					}
				};
			}
			return null;
		}
	}
}
