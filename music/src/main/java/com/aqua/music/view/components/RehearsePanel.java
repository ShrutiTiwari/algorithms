package com.aqua.music.view.components;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.api.Playable;
import com.aqua.music.view.action.listeners.PlayAllItemsActionListener;
import com.aqua.music.view.action.listeners.PlaySingleItemActionListener;

class RehearsePanel extends AbstractMusicPanel {
	private final Collection<Playable> itemsList;

	RehearsePanel(Collection<Playable> itemsList) {
		super(true);
		this.itemsList = itemsList;
	}

	@Override
	protected void addSpecificButtons(final JPanel mainTab) {
		final Collection<Playable> allItems = new ArrayList<Playable>();
		final TextArea consoleArea = consoleArea();

		for (Playable eachPlayableItem : itemsList) {
			JButton playSingleItemButton = UiButtons.Static.SINGLE_ITEM_PLAYER.createButton(eachPlayableItem.name(),UiButtons.X_COORIDNATE,buttonYcoordinate());
			playSingleItemButton.addActionListener(new PlaySingleItemActionListener(consoleArea, eachPlayableItem));
			allItems.add(eachPlayableItem);
			mainTab.add(playSingleItemButton);
		}

		Playable[] playableItems = allItems.toArray(new Playable[allItems.size()]);
		JButton playAllButton = UiButtons.Static.PLAYER_FOR_ALL.createButton(UiButtons.X_COORIDNATE, buttonYcoordinate());
		playAllButton.addActionListener(new PlayAllItemsActionListener(consoleArea, playableItems));
		mainTab.add(playAllButton);
	}

}