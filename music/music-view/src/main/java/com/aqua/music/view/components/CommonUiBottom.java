package com.aqua.music.view.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.InstrumentRole;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

class CommonUiBottom {
	private final JPanel bottomPanel;
	private final StateDependentUi stateDependentUi;

	public JPanel panel() {
		return bottomPanel;
	}

	CommonUiBottom(StateDependentUi stateDependentUi) {
		this.bottomPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		this.stateDependentUi = stateDependentUi;
		addInstrument(InstrumentRole.MAIN);
		// addInstrument(InstrumentRole.RHYTHM);

		JPanel quitPanel = UiJPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();
		JButton quitButton = UiButtons.QUIT.getButton();
		quitPanel.add(quitButton);
		bottomPanel.add(quitPanel);
	}

	private void addInstrument(InstrumentRole instrumentRole) {
		InstrumentDisplay instrumentDisplay = new InstrumentDisplay(instrumentRole);
		
		bottomPanel.add(instrumentDisplay.instrumentLabel());
		bottomPanel.add(instrumentDisplay.displayPane());
		bottomPanel.add(Box.createVerticalGlue());
	}

	public class InstrumentDisplay {
		private final InstrumentRole instrumentRole;
		private InstrumentDisplay(InstrumentRole instrumentRole) {
			this.instrumentRole = instrumentRole;
		}

		public JPanel instrumentLabel() {
			JLabel label = UiTexts.UiLables.INSTRUMENT_LABEL.getLabel();
			label.setText(label.getText() + instrumentRole.name().toLowerCase() + " play:");
			JPanel instrumentLabelPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
			instrumentLabelPanel.add(label);
			instrumentLabelPanel.setSize(new Dimension(10, 40));
			
			return instrumentLabelPanel;
		}

		public Component displayPane() {
			String[] instrumentNames = SingletonFactory.PLAY_API.getAllInstruments();
			JList jList = new JList(instrumentNames);
			jList.addListSelectionListener(new InstrumentDropdownActionListener(jList, instrumentNames, instrumentRole));

			jList.setBackground(UiColor.LOW_PRIORITY_CLR);
			JScrollPane instrumentPane = new UiScrollPane(3, 150, new Dimension(900, 50)).createScrollPane(jList);
			instrumentPane.setBackground(UiColor.BG_CLR);
			return instrumentPane;
		}

		private class InstrumentDropdownActionListener implements ListSelectionListener {
			private final JList jList;
			private String[] allInstruments;
			private InstrumentRole instrumentRole;

			public InstrumentDropdownActionListener(JList jList, String[] instrumentNames, InstrumentRole instrumentRole) {
				this.jList = jList;
				this.allInstruments = instrumentNames;
				this.instrumentRole = instrumentRole;
			}

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIndex = jList.getSelectedIndex();
					if (selectedIndex != -1) {
						String instrument = allInstruments[selectedIndex];
						instrumentRole.setTo(instrument);
						stateDependentUi.updateInstrument(instrument);
					}
				}
			}
		}
	}
}