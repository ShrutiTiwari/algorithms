package com.aqua.music.view.components;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import open.music.api.InstrumentRole;

import com.aqua.music.view.components.UiButtons.MusicButtons;

public class CommonBottomPanel {
	private final JPanel bottomPanel;

	public JPanel panel(){
		return bottomPanel;
	}
	
	public CommonBottomPanel() {
		this.bottomPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();

		addInstrument(InstrumentRole.MAIN);
		// addInstrument(InstrumentRole.RHYTHM);

		JPanel quitPanel = UiJPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();
		JButton quitButton = MusicButtons.QUIT.createStaticNamedButton();
		quitPanel.add(quitButton);
		bottomPanel.add(quitPanel);
	}

	private void addInstrument(InstrumentRole instrumentRole) {
		InstrumentDisplay instrumentDisplay = new InstrumentDisplay(instrumentRole);
		bottomPanel.add(instrumentDisplay.instrumentLabel());
		bottomPanel.add(instrumentDisplay.displayPane());
		bottomPanel.add(Box.createVerticalGlue());
	}
}