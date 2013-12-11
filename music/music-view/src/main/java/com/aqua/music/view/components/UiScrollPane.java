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

import open.music.api.AudioPlayerSettings;
import open.music.api.PlayApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	public static JComponent instrumentDisplay(Object selectedItem) {
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
		jList.addListSelectionListener(new InstrumentDropdownActionListener(jList, allInstruments));
		return new UiScrollPane(5, maxNameLength, new Dimension(900, 100)).createScrollPane(jList);
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

	static class InstrumentDropdownActionListener implements ListSelectionListener {
		Logger logger = LoggerFactory.getLogger(InstrumentDropdownActionListener.class);
		private final JList jList;
		private Instrument[] allInstruments;

		/**
		 * @param jList
		 * @param allInstruments
		 */
		public InstrumentDropdownActionListener(JList jList, Instrument[] allInstruments) {
			this.jList = jList;
			this.allInstruments = allInstruments;
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				int selectedIndex = jList.getSelectedIndex();
				if (selectedIndex != -1) {
					AudioPlayerSettings.changeInstrumentTo(allInstruments[selectedIndex]);
				}
			}
		}
	}
}
