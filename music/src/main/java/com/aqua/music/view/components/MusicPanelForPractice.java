package com.aqua.music.view.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
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

public class MusicPanelForPractice extends MusicPanel {
	private final Collection<Playable> intialItemsList;

	/**
	 * Used for plain rehearsing - of thaat and songs  
	 */
	public MusicPanelForPractice(Collection<Playable> itemsList) {
		super(false);
		this.intialItemsList = itemsList;
	}

	/**
	 * Used for patterned rehearse of thaat.
	 * @param frequencySet
	 * @param patternItemsCount
	 */
	public MusicPanelForPractice(FrequencySet frequencySet, PermuatationsGenerator patternItemsCount) {
		super(true);
		this.intialItemsList = PlayApi.getAllPatternedThaat(frequencySet, patternItemsCount);

		final ThaatAndPatternDropdownActionListener thaatPatternListener = new ThaatAndPatternDropdownActionListener(this, frequencySet,
				patternItemsCount);

		final JComboBox thaatDropdown = UiDropdown.thaatDropDown(frequencySet);
		thaatDropdown.addActionListener(thaatPatternListener);

		final JComboBox patternDropdown = UiDropdown.patternThaatDropDown();
		patternDropdown.addActionListener(thaatPatternListener);

		addToExtraComponentPanel(thaatDropdown);
		addToExtraComponentPanel(patternDropdown);
	}

	@Override
	protected JPanel createSpecificComponentPanel(final Object selectedObject) {
		Collection<Playable> itemsList = (Collection<Playable>) selectedObject;
		if (itemsList == null) {
			itemsList = this.intialItemsList;
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

		JPanel resultPanel = new JPanel();
		resultPanel.setOpaque(true);
		
		for (JComponent each : allButtons) {
			resultPanel.add(each,BorderLayout.CENTER);
		}

		resultPanel.add(consoleArea, BorderLayout.AFTER_LAST_LINE);
		return resultPanel;
	}

	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Hello shrutz");
		textArea.setVisible(true);
		return textArea;
	}
}