package com.aqua.music.view.components;

import java.awt.Rectangle;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.aqua.music.api.Playable;
import com.aqua.music.view.action.listeners.PlayAllItemsActionListener;
import com.aqua.music.view.action.listeners.PlaySingleItemActionListener;
import com.aqua.music.view.components.UiButtons.MusicButtons;

public class RehearsePanel extends AbstractMusicPanel {
	private final Collection<Playable> itemsList;

	public RehearsePanel(Collection<Playable> itemsList) {
		super(true);
		this.itemsList = itemsList;
	}

	@Override
	protected Collection<JComponent> addSpecificButtons() {
		final Collection<JComponent> result = new ArrayList<JComponent>();

		final Collection<Playable> allItems = new ArrayList<Playable>();
		final TextArea consoleArea = consoleArea();
		final JButton pauseButton = pauseButton();

		for (Playable eachPlayableItem : itemsList) {
			JButton playSingleItemButton = UiButtons.MusicButtons.SINGLE_ITEM_PLAYER.createDynamicNamedButton(eachPlayableItem.name(),
					buttonYcoordinate());
			playSingleItemButton.addActionListener(new PlaySingleItemActionListener(consoleArea, eachPlayableItem, pauseButton));
			allItems.add(eachPlayableItem);
			result.add(playSingleItemButton);
		}

		Playable[] playableItems = allItems.toArray(new Playable[allItems.size()]);
		JButton playAllButton = UiButtons.MusicButtons.PLAYER_FOR_ALL.createStaticNamedButton(buttonYcoordinate());
		playAllButton.addActionListener(new PlayAllItemsActionListener(consoleArea, playableItems, pauseButton));
		result.add(playAllButton);
		
		Rectangle lastButton = playAllButton.getBounds();
		JButton quitButton = MusicButtons.QUIT.createStaticNamedButton(lastButton.x + UiButtons.DEFAULT_BUTTON_WIDTH + 20, lastButton.y);
		result.add(quitButton);
		
		return result;
	}

}