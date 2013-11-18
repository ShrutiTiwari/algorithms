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
	private final YCoordinateTracker yCoordinateTracker;
	private volatile boolean initialized = false;
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);
	//private final static JButton stopButton = ;
	//private static final TextArea textArea = ;

	protected AbstractMusicPanel() {
		this.yCoordinateTracker = new YCoordinateTracker();
		this.panel = createBlankMainTab();
	}

	private synchronized void initialize() {
		if (!initialized) {
			initialized = true;
			panel.add(CommonButtons.STOP.createButton(UiButtons.X_COORIDNATE + 600, 20));
			TextArea textArea=createTextArea(UiButtons.X_COORIDNATE + 600, 60);
			panel.add(textArea);
			addSpecificButtons(panel, textArea);
			panel.add(CommonButtons.QUIT.createButton(UiButtons.X_COORIDNATE, yCoordinateTracker.buttonYcoordinate()));
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

	private static TextArea createTextArea(int xCoordinate, int yCoordintate) {
		TextArea textArea = new TextArea("Hello shrutz");
		textArea.setVisible(true);
		textArea.setBounds(xCoordinate, yCoordintate, 550, 550);
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
