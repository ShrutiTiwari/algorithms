/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Dimension;

import javax.sound.midi.Instrument;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.InstrumentRole;
import open.music.api.PlayApi;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class UiScrollPane {
	private final Dimension dimension;
	private final int itemCount;
	private final int maxNameLength;

	public UiScrollPane(int itemCount, int maxNameLength, Dimension dimension) {
		this.itemCount = itemCount;
		this.maxNameLength = maxNameLength;
		this.dimension = dimension;
	}

	UiScrollPane() {
		this(10, 200, null);
	}

	public static JComponent instrumentDisplay(InstrumentRole instrumentRole, Object selectedItem) {
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

	JScrollPane createScrollPane(JList jList) {
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setLayoutOrientation(JList.VERTICAL_WRAP);
		jList.setVisibleRowCount(itemCount);
		jList.setFixedCellWidth(maxNameLength);

		JScrollPane scrollPane = new JScrollPane(jList);
		if (dimension != null) {
			scrollPane.setPreferredSize(dimension);
		}
		return scrollPane;
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
