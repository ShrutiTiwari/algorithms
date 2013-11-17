package com.aqua.music.view.helper;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.api.Playable;

class RehearsalUiTab extends AbstractRehearseTabs<Playable> {
	private final Collection<Playable> itemsList;

	RehearsalUiTab(Collection<Playable> itemsList) {
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
		JButton button = RehearsalUiButtons.SINGLE_ITEM_PLAYER.createButton(textArea, buttonYcoordinate,
				new Playable[] { playableItem });
		allPlayableItems.add(playableItem);
		mainTab.add(button);
	}
}