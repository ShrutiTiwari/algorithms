package com.aqua.music.view.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;

import com.aqua.music.view.components.UiButtons.MusicButtons;

public class HorizontallyAlignedButtons {
	private final int yCoordinate;
	private final int xIncrement;
	private MusicButtons musicButtonCreator;
	private int currentXCorrdinate;
	private final Collection<JButton> allButtons = new ArrayList<JButton>();

	HorizontallyAlignedButtons(int xCoordinate, int yCoordinate, int xIncrement, MusicButtons musicButtonCreator) {
		this.currentXCorrdinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.xIncrement = xIncrement;
		this.musicButtonCreator = musicButtonCreator;
	}

	Collection<JButton> all() {
		return Collections.unmodifiableCollection(allButtons);
	}

	void add(String buttonName) {
		this.allButtons.add(musicButtonCreator.createDynamicNamedButton(buttonName, currentXCorrdinate, yCoordinate));
		this.currentXCorrdinate = currentXCorrdinate + xIncrement;
	}
}
