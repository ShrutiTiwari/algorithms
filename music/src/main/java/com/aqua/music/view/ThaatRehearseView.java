package com.aqua.music.view;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.controller.AbstractRehearseTabPanels;
import com.aqua.music.controller.UiButtonsForFrequencySet;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;

public class ThaatRehearseView implements SwingView {

	static class MyImpl extends AbstractRehearseTabPanels<CyclicFrequencySet> {
		MyImpl() {
			super();
		}

		@Override
		protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {

			final Collection<CyclicFrequencySet> allFrequencySequences = new ArrayList<CyclicFrequencySet>();

			for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
				CyclicFrequencySet frequencySequence = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);

				JButton button = UiButtonsForFrequencySet.FREQUENCY_SET_PLAYER.createButton(textArea, buttonYcoordinate(),
						new CyclicFrequencySet[] { frequencySequence });
				allFrequencySequences.add(frequencySequence);
				mainTab.add(button);
			}
			return allFrequencySequences;
		}
	};

	@Override
	public JPanel generateView() {
		return new MyImpl().getPanel();
	}
}
