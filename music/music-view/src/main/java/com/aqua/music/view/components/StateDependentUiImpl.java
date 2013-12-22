/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.model.core.Frequency;

import open.music.api.StateDependentUi;

/**
 * @author "Shruti Tiwari"
 * 
 */
class StateDependentUiImpl implements StateDependentUi {
	private final CommonUiTop topPanelArea;
	private final TextArea consoleArea;
	private final JButton pauseButton;
	private final List<StartEndPointChangeListener> listeners= new ArrayList<StartEndPointChangeListener>();

	public StateDependentUiImpl() {
		this.topPanelArea = new CommonUiTop();
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
	public void updateInstrument(String instrument) {
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
		textArea.setBackground(UiColor.LOW_PRIORITY_CLR);
		return textArea;
	}

	public void setStartEndPoints(Frequency[] startEndPoints) {
		for(StartEndPointChangeListener each: listeners){
			each.changedEndPoints(startEndPoints);
		}
	}

	@Override
	public void registerStartEndPointChangeListener(StartEndPointChangeListener listener) {
		this.listeners.add(listener);
	}
}
