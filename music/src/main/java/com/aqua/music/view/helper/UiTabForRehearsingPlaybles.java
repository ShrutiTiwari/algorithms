package com.aqua.music.view.helper;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.Playable;

class UiTabForRehearsingPlaybles extends AbstractRehearseTabs<Playable> {
	private final Object[] itemsList;

	UiTabForRehearsingPlaybles(Object[] itemsList) {
		super();
		this.itemsList = itemsList;
	}

	@Override
	protected Collection<Playable> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {
		final Collection<Playable> allItems = new ArrayList<Playable>();
		for (Object currentItem : itemsList) {
			Playable playableItem = getPlyableItem(currentItem);
			addButtonForPlayble(mainTab, textArea, allItems, playableItem, buttonYcoordinate());
		}
		return allItems;
	}

	static void addButtonForPlayble(final JPanel mainTab, final TextArea textArea, final Collection<Playable> allPlayableItems,
			Playable playableItem, int buttonYcoordinate) {
		JButton button = UiButtonsForRehearsing.SINGLE_ITEM_PLAYER.createButton(textArea, buttonYcoordinate,
				new Playable[] { playableItem });
		allPlayableItems.add(playableItem);
		mainTab.add(button);
	}

	private static Playable getPlyableItem(Object eachItem) {
		return (eachItem instanceof Playable) ? (Playable) eachItem
				: (eachItem instanceof FrequencySet) ? CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet((FrequencySet) eachItem) : null;
	}
}