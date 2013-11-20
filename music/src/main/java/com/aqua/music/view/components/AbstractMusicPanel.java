package com.aqua.music.view.components;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiButtons.MusicButtons;

abstract class AbstractMusicPanel {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);

	private volatile boolean initialized = false;
	private final JPanel panel;
	private final PlayerControlPanel playerControlPanel;

	private final YCoordinateTracker yCoordinateTracker;

	protected AbstractMusicPanel(boolean withConsole) {
		this.playerControlPanel = new PlayerControlPanel(withConsole);
		this.yCoordinateTracker = new YCoordinateTracker();
		this.panel = createBlankMainTab();
		playerControlPanel.addToPanel(panel);
	}

	public TextArea consoleArea() {
		return playerControlPanel.consoleArea;
	}

	public JPanel getPanel() {
		if (!initialized) {
			initialize();
		}
		return panel;
	}

	protected abstract void addSpecificButtons(final JPanel mainPanel);

	protected final int buttonYcoordinate() {
		return yCoordinateTracker.buttonYcoordinate();
	}

	private JPanel createBlankMainTab() {
		JPanel mainTab = new JPanel();
		mainTab.setLayout(null);
		mainTab.setPreferredSize(preferredSizeForThaatPanel);
		return mainTab;
	}

	private synchronized void initialize() {
		if (!initialized) {
			initialized = true;
			addSpecificButtons(panel);
			panel.add(MusicButtons.QUIT.createStaticNamedButton(yCoordinateTracker.buttonYcoordinate()));
			panel.setOpaque(true);
		}
	}

	/**
	 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded use.
	 * */

	class YCoordinateTracker {
		private static final int START = 10;
		// mutated variable
		private int yCoordinate = START;

		private int buttonYcoordinate() {
			yCoordinate += (UiButtons.BUTTON_HEIGHT) + 10;
			return yCoordinate;
		}
	}

	private class PlayerControlPanel {
		private static final int PANEL_X_COORDINATE = UiButtons.X_COORIDNATE + 600;
		private final TextArea consoleArea;
		private final int controlPanelLocation = PANEL_X_COORDINATE;
		private final JButton pauseButton;
		private final JButton resumeButton;
		private final JButton stopButton;
		private final boolean withConsole;

		private PlayerControlPanel(boolean withConsole) {
			this.withConsole = withConsole;
			this.stopButton = MusicButtons.STOP.createStaticNamedButton(controlPanelLocation, 20);
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton(controlPanelLocation + UiButtons.MINI_BUTTON_WIDTH, 20);
			this.resumeButton = MusicButtons.RESUME.createStaticNamedButton(controlPanelLocation + 2 * (UiButtons.MINI_BUTTON_WIDTH), 20);
			this.consoleArea = createTextArea(controlPanelLocation, 60);
		}

		public void addToPanel(JPanel panel) {
			panel.add(stopButton);
			panel.add(pauseButton);
			panel.add(resumeButton);
			if (withConsole) {
				panel.add(consoleArea);
			}
		}

		private TextArea createTextArea(int xCoordinate, int yCoordintate) {
			TextArea textArea = new TextArea("Hello shrutz");
			textArea.setVisible(true);
			textArea.setBounds(xCoordinate, yCoordintate, 550, 550);
			return textArea;
		}
	}
}
