package com.aqua.music.view.components;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.aqua.music.api.PlayApi;
import com.aqua.music.api.Playable;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.view.action.listeners.PlayAllItemsActionListener;
import com.aqua.music.view.action.listeners.PlaySingleItemActionListener;
import com.aqua.music.view.components.UiDropdown.ThaatAndPatternDropdownActionListener;

public class RehearsePanel extends AbstractMusicPanel {
	private final Collection<Playable> itemsList;

	public RehearsePanel(Collection<Playable> itemsList) {
		super();
		this.itemsList = itemsList;
	}

	public RehearsePanel(FrequencySet frequencySet, PermuatationsGenerator patternItemsCount) {
		super();
		this.itemsList = PlayApi.getAllPatternedThaat(frequencySet, patternItemsCount);
		
		final ThaatAndPatternDropdownActionListener thaatPatternListener = new ThaatAndPatternDropdownActionListener(this,frequencySet, patternItemsCount);
		
		final JComboBox thaatDropdown = UiDropdown.thaatDropDown(frequencySet);
		thaatDropdown.addActionListener(thaatPatternListener);

		final JComboBox patternDropdown = UiDropdown.patternThaatDropDown();
		patternDropdown.addActionListener(thaatPatternListener);

		addToCommonComponentPanel(thaatDropdown);
		addToCommonComponentPanel(patternDropdown);
	}

	@Override
	protected JPanel createSpecificComponentPanel(final Object selectedObject) {
		Collection<Playable> itemsList = (Collection<Playable>) selectedObject;
		if (itemsList == null) {
			itemsList = this.itemsList;
		}
		final Collection<JComponent> allButtons = new ArrayList<JComponent>();
		final Collection<Playable> allItems = new ArrayList<Playable>();
		final TextArea consoleArea = createConsoleArea();
		final JButton pauseButton = pauseButton();

		for (Playable eachPlayableItem : itemsList) {
			JButton playSingleItemButton = UiButtons.MusicButtons.SINGLE_ITEM_PLAYER.createDynamicNamedButton(eachPlayableItem.name());
			playSingleItemButton.addActionListener(new PlaySingleItemActionListener(consoleArea, eachPlayableItem, pauseButton));
			allItems.add(eachPlayableItem);
			allButtons.add(playSingleItemButton);
		}

		Playable[] playableItems = allItems.toArray(new Playable[allItems.size()]);
		JButton playAllButton = UiButtons.MusicButtons.PLAYER_FOR_ALL.createStaticNamedButton();
		playAllButton.addActionListener(new PlayAllItemsActionListener(consoleArea, playableItems, pauseButton));
		allButtons.add(playAllButton);

		JPanel result = new JPanel();
		result.setOpaque(true);
		for (JComponent each : allButtons) {
			result.add(each);
		}
		result.add(consoleArea);
		return result;
	}

	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Hello shrutz");
		textArea.setVisible(true);
		return textArea;
	}
}