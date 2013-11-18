package com.aqua.music.view.components;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.api.Playable;

class RehearsePanel extends AbstractRehearsePanel<Playable> {
	private final Collection<Playable> itemsList;

	RehearsePanel(Collection<Playable> itemsList) {
		super();
		this.itemsList = itemsList;
	}

	@Override
	protected Collection<Playable> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {
		final Collection<Playable> allItems = new ArrayList<Playable>();
		for (Playable playableItem : itemsList) {
			addButtonForPlayble(mainTab, textArea, allItems, playableItem, buttonYcoordinate());
		}
		return allItems;
	}

	static void addButtonForPlayble(final JPanel mainTab, final TextArea textArea, final Collection<Playable> allPlayableItems,
			Playable playableItem, int buttonYcoordinate) {
		JButton button = RehearseButtons.SINGLE_ITEM_PLAYER.createButton(textArea, buttonYcoordinate,
				new Playable[] { playableItem });
		allPlayableItems.add(playableItem);
		mainTab.add(button);
	}
}