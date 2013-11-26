package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiButtons.MusicButtons;

abstract class AbstractMusicPanel {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	protected final Logger logger = LoggerFactory.getLogger(AbstractMusicPanel.class);

	private volatile boolean initialized = false;
	private final JPanel panel;
	private final PlayerControlComponents playerControlPanel;

	private final YCoordinateTracker yCoordinateTracker;

	protected AbstractMusicPanel(boolean withConsole) {
		this.playerControlPanel = new PlayerControlComponents(withConsole);
		this.yCoordinateTracker = new YCoordinateTracker();
		this.panel = createBlankMainTab();
		for (JComponent each : playerControlPanel.getAllComponents()) {
			panel.add(each);
		}
		if (withConsole) {
			panel.add(playerControlPanel.consoleArea);
		}
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

	protected abstract Collection<JComponent> addSpecificButtons();

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
			JComponent lastButton=null;
			for (JComponent each : addSpecificButtons()) {
				panel.add(each);
				lastButton=each;
			}
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

	private class PlayerControlComponents {
		private static final int PANEL_X_COORDINATE = UiButtons.X_COORIDNATE + 600;
		private final TextArea consoleArea;
		private final int controlPanelLocation = PANEL_X_COORDINATE;

		private final Collection<JComponent> result = new ArrayList<JComponent>();

		private PlayerControlComponents(boolean withConsole) {
			this.consoleArea = createTextArea(controlPanelLocation, 60);

			result.add(MusicButtons.STOP.createStaticNamedButton(controlPanelLocation, 20));
			result.add(MusicButtons.PAUSE.createStaticNamedButton(controlPanelLocation + UiButtons.MINI_BUTTON_WIDTH, 20));
			result.add(MusicButtons.RESUME.createStaticNamedButton(controlPanelLocation + 2 * (UiButtons.MINI_BUTTON_WIDTH), 20));
		}

		public Collection<JComponent> getAllComponents() {
			return result;
		}

		private TextArea createTextArea(int xCoordinate, int yCoordintate) {
			TextArea textArea = new TextArea("Hello shrutz");
			textArea.setVisible(true);
			textArea.setBounds(xCoordinate, yCoordintate, 550, 550);
			return textArea;
		}
	}
}
