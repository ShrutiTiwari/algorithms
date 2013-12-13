/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.sound.midi.Instrument;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import open.music.api.PlayApi;

class CommonUiTop {
	private final CurrentState currentState;
	private final JPanel leftPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
	private final JPanel mainPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
	private final JButton pauseButton;
	private JPanel rightPanel;

	public CommonUiTop() {
		mainPanel.add(leftPanel);
		
		this.pauseButton = UiButtons.PAUSE.getButton();
		this.rightPanel = UiJPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();
		this.currentState = new CurrentState();

		addToPanel(currentState.currentPlayableLabel, rightPanel);
		addToPanel(pauseButton, rightPanel);
		rightPanel.add(new JLabel("  "));
		
		addToPanel(currentState.currentInstrumentLabel, rightPanel);

		rightPanel.add(new JLabel("  "));
		addToPanel(currentState.currentSpeedLabel, rightPanel);
		addToPanel(UiButtons.INCREASE_TEMPO.getButton(), rightPanel);
		addToPanel(UiButtons.DECREASE_TEMPO.getButton(), rightPanel);

		
		JLabel lable = UiTexts.UiLables.MESSAGE_TOP.getLabel();
		lable.setFont(new Font("", Font.PLAIN, 30));
		leftPanel.add(lable);

		mainPanel.add(rightPanel);
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
		private String currentPlayable;

		private final JLabel currentPlayableLabel;
		private final JLabel currentInstrumentLabel;
		private final JLabel currentSpeedLabel;

		private Instrument mainInstrument;
		private int speed;

		private CurrentState() {
			this.currentPlayableLabel = new JLabel("Playing[ -  ]");
			this.currentInstrumentLabel = new JLabel("Playing[ " + PlayApi.defaultInstrument().getName() + "  ]");
			this.currentSpeedLabel = new JLabel("Speed[ 0  ]");
		}

		public void setCurrentPlayable(String currentPlayable) {
			this.currentPlayable = currentPlayable;
			String playableName = currentPlayable == null ? "--" : currentPlayable;
			currentPlayableLabel.setText("Playing[" + playableName + "]");
		}

		public void setMainInstrument(Instrument mainInstrument) {
			this.mainInstrument = mainInstrument;
			String instrumentName = mainInstrument == null ? "--" : mainInstrument.getName();
			currentInstrumentLabel.setText("Instrument[" + instrumentName + "]");
		}

		public void setSpeed(int speed) {
			this.speed = speed;
			currentSpeedLabel.setText("Speed [" + speed + "]");
		}
	}
}