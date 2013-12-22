/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CommonUiTop {
	private final CurrentState currentState;
	private final JPanel firstPanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
	private final JPanel mainPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
	private final JButton pauseButton;
	private JPanel secondPanel=UiJPanelBuilder.BOX_HORIZONTAL.createPanel();

	public CommonUiTop() {
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		
		this.pauseButton = UiButtons.PAUSE.getButton();
		this.currentState = new CurrentState();

		firstPanel.add(UiTexts.UiLables.MESSAGE_TOP.getLabel());
		
		secondPanel.add(Box.createHorizontalGlue());
		addToPanel(currentState.currentPlayableLabel, secondPanel);
		addToPanel(pauseButton, secondPanel);
		secondPanel.add(Box.createHorizontalGlue());
		
		addToPanel(currentState.currentInstrumentLabel, secondPanel);
		secondPanel.add(Box.createHorizontalGlue());
		
		addToPanel(UiButtons.INCREASE_TEMPO.getButton(), secondPanel);
		addToPanel(currentState.currentSpeedLabel, secondPanel);
		addToPanel(UiButtons.DECREASE_TEMPO.getButton(), secondPanel);
		secondPanel.add(Box.createHorizontalGlue());
	}

	private static void addToPanel(JComponent each, JPanel containerPanel) {
		each.setForeground(Color.WHITE);
		containerPanel.add(each);
		each.setAlignmentX(Component.RIGHT_ALIGNMENT);
		containerPanel.add(Box.createVerticalGlue());
	}

	public CurrentState currentState() {
		return currentState;
	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public JButton pauseButton() {
		return pauseButton;
	}

	public static class CurrentState {
		private final JLabel currentInstrumentLabel;
		private final JLabel currentPlayableLabel;
		private final JLabel currentSpeedLabel;

		private String mainInstrument;
		private String playableName;
		private int speed;

		private CurrentState() {
			this.currentPlayableLabel = UiTexts.UiLables.STATUS_PLAYABLE.getLabel();
			this.currentInstrumentLabel = UiTexts.UiLables.STATUS_INSTRUMENT.getLabel();
			this.currentSpeedLabel = UiTexts.UiLables.STATUS_SPEED.getLabel();
		}

		public void setCurrentPlayable(String currentPlayable) {
			this.playableName = currentPlayable;
			String playableName = currentPlayable == null ? "--" : currentPlayable;
			currentPlayableLabel.setText("Playing[ " + playableName + " ]");
		}

		public void setMainInstrument(String instrument) {
			this.mainInstrument = instrument;
			String instrumentName = instrument == null ? "--" : instrument;
			currentInstrumentLabel.setText("Instrument[ " + instrumentName + " ]");
		}

		public void setSpeed(int speed) {
			this.speed = speed;
			currentSpeedLabel.setText("Speed[ " + speed + " ]");
		}
	}
}