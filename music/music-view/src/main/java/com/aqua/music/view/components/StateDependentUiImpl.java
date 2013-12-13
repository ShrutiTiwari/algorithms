/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.TextArea;

import javax.sound.midi.Instrument;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import open.music.api.StateDependentUi;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class StateDependentUiImpl implements StateDependentUi {
	private final CommonTopPanel topPanelArea;
	private final TextArea consoleArea;
	private final JButton pauseButton;

	public StateDependentUiImpl() {
		this.topPanelArea = new CommonTopPanel();
		this.pauseButton = topPanelArea.pauseButton();
		this.consoleArea = createConsoleArea();
	}

	@Override
	public void appendToConsole(String text) {
		consoleArea.append(text);
	}

	public TextArea consoleArea() {
		return consoleArea;
	}

	@Override
	public void setPauseToDisplay() {
		final Icon pauseIcon = UiButtons.imageResourceCache.imageIcon(UiButtons.IMAGE_PAUSE);
		pauseButton.setIcon(pauseIcon);
	}

	public JPanel topPanel() {
		return topPanelArea.getPanel();
	}

	@Override
	public void updateConsole(String displayText) {
		consoleArea.setText(displayText);
	}

	@Override
	public void updateInstrument(Instrument instrument) {
		topPanelArea.currentState().setMainInstrument(instrument);
	}

	@Override
	public void updatePlayable(String playableName) {
		topPanelArea.currentState().setCurrentPlayable(playableName);
	}

	@Override
	public void updateTempo(int multipler) {
		topPanelArea.currentState().setSpeed(multipler);
	}

	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Played notes will be displayed here in indian scale....");
		textArea.setEditable(false);
		textArea.setVisible(true);
		return textArea;
	}
}
