/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.sound.midi.Instrument;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.InstrumentRole;
import open.music.api.PlayApi;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class InstrumentDisplay {

	private final InstrumentRole instrumentRole;

	InstrumentDisplay(InstrumentRole instrumentRole) {
		this.instrumentRole = instrumentRole;
	}

	public JPanel instrumentLabel() {
		String label = UiLabels.INSTRUMENT_LABEL + instrumentRole.name().toLowerCase() + " play:";
		JPanel instrumentLabelPanel = MusicPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		instrumentLabelPanel.add(new JLabel(label));
		instrumentLabelPanel.setSize(new Dimension(10, 40));
		return instrumentLabelPanel;
	}

	public Component displayPane() {
		Instrument[] allInstruments = PlayApi.getAllInstruments();
		
		String[] instrumentNames = new String[allInstruments.length];
		int i = 0;
		int maxNameLength = 150;
		for (Instrument each : allInstruments) {
			String name = each.getName().trim();
			instrumentNames[i++] = name;
			if (maxNameLength < name.length()) {
				maxNameLength = name.length();
			}
		}
		JList jList = new JList(instrumentNames);
		jList.addListSelectionListener(new InstrumentDropdownActionListener(jList, allInstruments, instrumentRole));
		
		return new UiScrollPane(3, maxNameLength, new Dimension(900, 50)).createScrollPane(jList);
	}

	private static class InstrumentDropdownActionListener implements ListSelectionListener {
		private final JList jList;
		private Instrument[] allInstruments;
		private InstrumentRole instrumentRole;

		public InstrumentDropdownActionListener(JList jList, Instrument[] allInstruments, InstrumentRole instrumentRole) {
			this.jList = jList;
			this.allInstruments = allInstruments;
			this.instrumentRole = instrumentRole;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				int selectedIndex = jList.getSelectedIndex();
				if (selectedIndex != -1) {
					instrumentRole.setTo(allInstruments[selectedIndex]);
				}
			}
		}
	}
}
