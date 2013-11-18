package com.aqua.music.view.components;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiButtons.CommonButtons;

abstract class AbstractMusicPanel {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	private final JPanel panel;
	private final TextArea textArea;
	private final YCoordinateTracker yCoordinateTracker;
	private volatile boolean initialized = false;
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);

	protected AbstractMusicPanel() {
		this.yCoordinateTracker = new YCoordinateTracker();
		this.textArea = createTextArea();
		this.panel = createBlankMainTab();
	}

	private synchronized void initialize() {
		if (!initialized) {
			initialized = true;
			addSpecificButtons(panel, textArea);
			panel.add(CommonButtons.QUIT.createButton(yCoordinateTracker.buttonYcoordinate()));
			panel.setOpaque(true);
		}
	}

	protected abstract void addSpecificButtons(final JPanel mainTab, final TextArea textArea);

	public JPanel getPanel() {
		if (!initialized) {
			initialize();
		}
		return panel;
	}

	private JPanel createBlankMainTab() {
		JPanel mainTab = new JPanel();
		mainTab.setLayout(null);
		mainTab.setPreferredSize(preferredSizeForThaatPanel);
		return mainTab;
	}

	protected JLabel createTextLabel() {
		JLabel jLabel = new JLabel("");
		jLabel.setVisible(true);
		return jLabel;
	}

	private TextArea createTextArea() {
		TextArea textArea = new TextArea("Hello shrutz");
		textArea.setVisible(true);
		textArea.setBounds(UiButtons.X_COORIDNATE + 600, 60, 600, 600);
		return textArea;
	}

	protected final int buttonYcoordinate() {
		return yCoordinateTracker.buttonYcoordinate();
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

		private void reset() {
			this.yCoordinate = 10;
		}
	}
}
