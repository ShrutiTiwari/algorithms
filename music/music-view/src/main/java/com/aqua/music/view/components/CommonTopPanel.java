/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;

import javax.sound.midi.Instrument;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.example.easymidi.Playable;
import com.aqua.music.view.components.UiButtons.MusicButtons;

public class CommonTopPanel {
	private final CurrentState currentState;
	private final JPanel leftPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
	private final JPanel mainPanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
	private final JButton pauseButton;
	private JPanel rightPanel;

	public CommonTopPanel() {
		this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
		this.rightPanel = UiJPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();

		this.currentState = new CurrentState();

		addToPanel(currentState.currentStateLabel(), rightPanel);
		addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), rightPanel);
		addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), rightPanel);
		addToPanel(pauseButton, rightPanel);

		mainPanel.add(leftPanel);

		leftPanel.add(new JLabel(" Choose one of the practice tabs for practice "));

		mainPanel.add(rightPanel);
	}

	private static void addToPanel(JComponent each, JPanel containerPanel) {
		each.setForeground(Color.BLUE);
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
		private final JLabel currentStateLabel;
		private Instrument mainInstrument;
		private int speed;

		private CurrentState() {
			this.currentStateLabel = new JLabel();
			update();
		}

		public void setCurrentPlayable(String currentPlayable) {
			this.currentPlayable = currentPlayable;
			update();
		}

		public void setMainInstrument(Instrument mainInstrument) {
			this.mainInstrument = mainInstrument;
			update();
		}

		public void setSpeed(int speed) {
			this.speed = speed;
			update();
		}

		@Override
		public String toString() {
			return "Playing[" + currentPlayable + "]     instrument[" + mainInstrument + "]     speed [" + speed + "]";

		}

		private JLabel currentStateLabel() {
			return currentStateLabel;
		}

		private void update() {
			currentStateLabel.setText(this.toString());
		}
	}
}