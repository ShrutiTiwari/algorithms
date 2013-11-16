package com.aqua.music.view.helper;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.Playable;
import com.aqua.music.model.cyclicset.SymmetricalSet;

class UiTabForRehearsingPatternedFrequencySets extends AbstractRehearseTabs<Playable> {
	private final FrequencySet frequencySet;
	private final UiTabsFactory rehearseTabs;

	UiTabForRehearsingPatternedFrequencySets(final UiTabsFactory rehearseTabs, final FrequencySet frequencySet) {
		super();
		this.rehearseTabs = rehearseTabs;
		this.frequencySet = frequencySet;
	}

	@Override
	protected Collection<Playable> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {
		mainTab.add(createThaatDropdown());

		final Collection<Playable> allPlayableItems = new ArrayList<Playable>();

		List<int[]> allPermutations = PermuatationsGenerator.PAIR.generatePermutations(frequencySet.ascendNotes());
		for (int[] eachPermutation : allPermutations) {
			CyclicFrequencySet playbleItem = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
					eachPermutation);
			UiTabForRehearsingPlaybles.addButtonForPlayble(mainTab, textArea, allPlayableItems, playbleItem, buttonYcoordinate());
		}

		return allPlayableItems;
	}

	private JComboBox createThaatDropdown() {
		final JComboBox box = new JComboBox(SymmetricalSet.values());
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setSelectedItem(frequencySet);
		box.setBounds(UiButtonsForRehearsing.X_COORIDNATE, buttonYcoordinate(), 500, UiButtonsForRehearsing.HEIGHT());
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cbox = (JComboBox) arg0.getSource();
				FrequencySet selectedThaat = (FrequencySet) cbox.getSelectedItem();
				logger.info("selectedThaat [" + selectedThaat.name() + "]");
				rehearseTabs.reConfigureFrequencySetTab((SymmetricalSet) selectedThaat);
			}
		});
		return box;
	}
}
