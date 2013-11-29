package com.aqua.music.view.components;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.aqua.music.api.PlayApi;
import com.aqua.music.api.Playable;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.view.action.listeners.PlayAllItemsActionListener;
import com.aqua.music.view.action.listeners.PlaySingleItemActionListener;
import com.aqua.music.view.components.UiDropdown.ThaatAndPatternDropdownActionListener;

public class MusicPanelForPractice extends MusicPanel {
	private static final float LEFT_ALIGNMENT = 0;
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
		JPanel resultButtonsPanel = new JPanel();
		BoxLayout b = new BoxLayout(resultButtonsPanel, BoxLayout.PAGE_AXIS);
		resultButtonsPanel.setLayout(b);
		resultButtonsPanel.setOpaque(true);
		resultButtonsPanel.add(Box.createVerticalStrut(50));
		
		Collection<Playable> itemsList = (Collection<Playable>) selectedObject;
		if (itemsList == null) {
			itemsList = this.intialItemsList;
		}
		final Collection<JComponent> allButtons = new ArrayList<JComponent>();
		final TextArea consoleArea = createConsoleArea();
		final JButton pauseButton = pauseButton();
		
		Playable[] allPlayableItems = itemsList.toArray(new Playable[itemsList.size()]);

		JList playItemsList=new JList(allPlayableItems);
		playItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playItemsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		playItemsList.setVisibleRowCount(10);
		playItemsList.setFixedCellWidth(200);
		playItemsList.addListSelectionListener(new PlaySingleItemActionListener(playItemsList, consoleArea, allPlayableItems, pauseButton));
		JScrollPane listScroller = new JScrollPane(playItemsList);
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
		resultButtonsPanel.add(listScroller);

		//resultButtonsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
		
		JButton playAllButton = UiButtons.MusicButtons.PLAYER_FOR_ALL.createStaticNamedButton();
		playAllButton.addActionListener(new PlayAllItemsActionListener(consoleArea, allPlayableItems, pauseButton));
		resultButtonsPanel.add(playAllButton);

		JPanel resultPanel = new JPanel(new BorderLayout());
		resultPanel.add(resultButtonsPanel, BorderLayout.CENTER);
		resultPanel.add(consoleArea, BorderLayout.PAGE_END);
		return resultPanel;
	}

	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Played notes will be displayed here in indian scale....");
		textArea.setEditable(false);
		textArea.setVisible(true);
		return textArea;
	}
}