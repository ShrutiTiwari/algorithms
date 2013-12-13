package com.aqua.music.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.PlayApi;
import open.music.api.Playable;
import open.music.api.StateDependentUi;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.view.components.UiDropdown.ThaatAndPatternDropdownActionListener;

class MusicPanelForPractice extends MusicPanel {
	private final Collection<Playable> intialItemsList;
	private final String pickTitle;
	private JPanel specificComponentPanel;
	/**
	 * Used for plain rehearsing - of thaat and songs
	 * @param pickTitle TODO
	 */
	public MusicPanelForPractice(StateDependentUi stateDependentUi,Collection<Playable> itemsList, String pickTitle) {
		super(stateDependentUi,false);
		this.pickTitle=pickTitle;
		this.intialItemsList = itemsList;
	}

	/**
	 * Used for patterned rehearse of thaat.
	 * 
	 * @param frequencySet
	 * @param patternItemsCount
	 * @param pickTitle TODO
	 */
	public MusicPanelForPractice(StateDependentUi stateDependentUi,FrequencySet frequencySet, PermuatationsGenerator patternItemsCount, String pickTitle) {
		super(stateDependentUi,true);
		this.pickTitle=pickTitle;
		this.intialItemsList = PlayApi.getAllPatternedThaat(frequencySet, patternItemsCount);

		final ThaatAndPatternDropdownActionListener thaatPatternListener = new ThaatAndPatternDropdownActionListener(this, frequencySet,
				patternItemsCount);

		final JComboBox thaatDropdown = UiDropdown.thaatDropDown(frequencySet);
		thaatDropdown.addActionListener(thaatPatternListener);

		final JComboBox patternDropdown = UiDropdown.patternThaatDropDown();
		patternDropdown.addActionListener(thaatPatternListener);

		addExtraTopControl(thaatDropdown);
		addExtraTopControl(patternDropdown);
	}

	@Override
	public void repaint() {
		specificComponentPanel.repaint();
	}

	@Override
	protected JPanel createMiddlePanel(final Object selectedObject) {
		this.specificComponentPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		specificComponentPanel.setOpaque(true);
		specificComponentPanel.add(Box.createVerticalStrut(50));
		
		JPanel labelPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		labelPanel.add(new JLabel(pickTitle));
		labelPanel.setSize(new Dimension(10,40));
		
		JPanel playAreaPanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
		
		Collection<Playable> itemsList = (Collection<Playable>) selectedObject;
		if (itemsList == null) {
			itemsList = this.intialItemsList;
		}

		final Playable[] allPlayableItems = itemsList.toArray(new Playable[itemsList.size()]);
		JList playItemsList = new JList(allPlayableItems);
		playItemsList.addListSelectionListener(new PlaySingleItemActionListener(playItemsList, allPlayableItems));
		playAreaPanel.add(new UiScrollPane().createScrollPane(playItemsList));
		
		JButton playAllButton = UiButtons.MusicButtons.PLAYER_FOR_ALL.getButton();
		playAllButton.addActionListener(new PlayAllItemsActionListener(allPlayableItems));
		
		specificComponentPanel.add(labelPanel);
		specificComponentPanel.add(playAreaPanel);
		specificComponentPanel.add(Box.createVerticalGlue());
		
		JPanel playAllPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		playAllPanel.add(playAllButton);
		specificComponentPanel.add(playAllPanel);
		return specificComponentPanel;
	}

	class PlayAllItemsActionListener implements ActionListener {
		private final Playable[] playableItems;

		public PlayAllItemsActionListener(final Playable[] playableItems) {
			this.playableItems = playableItems;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			PlayApi.playAllItemsWithInteractiveDisplayInTextArea(playableItems, 5);
		}
	}

	class PlaySingleItemActionListener implements ListSelectionListener {
		private final Playable[] allPlayableItems;
		private JList jlist;

		public PlaySingleItemActionListener(final JList jlist, Playable[] singlePlayableItem) {
			this.jlist = jlist;
			this.allPlayableItems = singlePlayableItem;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				int selectedIndex = jlist.getSelectedIndex();
				if (selectedIndex != -1) {
					PlayApi.playInLoop(allPlayableItems[selectedIndex]);
				}
			}
		}
	}
}