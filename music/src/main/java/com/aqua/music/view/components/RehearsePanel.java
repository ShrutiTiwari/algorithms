package com.aqua.music.view.components;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.api.Playable;

class RehearsePanel extends AbstractMusicPanel {
	private final Collection<Playable> itemsList;

	RehearsePanel(Collection<Playable> itemsList) {
		super();
		this.itemsList = itemsList;
	}

	@Override
	protected void addSpecificButtons(final JPanel mainTab, final TextArea consoleArea) {
		final Collection<Playable> allItems = new ArrayList<Playable>();
		for (Playable playableItem : itemsList) {
			JButton button = RehearseButtons.SINGLE_ITEM_PLAYER.createButton(consoleArea, buttonYcoordinate(),
					new Playable[] { playableItem });
			allItems.add(playableItem);
			mainTab.add(button);
		}
		
		Playable[] playableItems = allItems.toArray(new Playable[allItems.size()]);
		mainTab.add(RehearseButtons.PLAYER_FOR_ALL.createButton(consoleArea, buttonYcoordinate(), playableItems));
	}
	
}