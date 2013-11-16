package com.aqua.music.controller;

import java.awt.Dimension;
import java.awt.TextArea;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.song.Song;

public abstract class AbstractRehearseTabPanels<T> {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	private final JPanel panel;
	private final TextArea textArea;
	private final YCoordinateTracker yCoordinateTracker;
	private volatile boolean initialized = false;
	protected final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);

	protected AbstractRehearseTabPanels() {
		this.yCoordinateTracker = new YCoordinateTracker();
		this.textArea = createTextArea();
		this.panel = createBlankMainTab();
	}

	private synchronized void initialize() {
		if (!initialized) {
			initialized = true;
			Collection<T> allFrequencySequences = addSpecificButtons(panel, textArea);
			addCommonComponents(allFrequencySequences);
		}
	}

	protected abstract Collection<T> addSpecificButtons(final JPanel mainTab, final TextArea textArea);

	public JPanel getPanel() {
		if(!initialized){
			initialize();
		}
		return panel;
	}

	private void addCommonComponents(Collection<T> allFrequencySequences) {
		// add play all button
		if (!allFrequencySequences.isEmpty()) {
			if ((allFrequencySequences.iterator().next()) instanceof CyclicFrequencySet) {
				final CyclicFrequencySet[] freqSeqArr = allFrequencySequences.toArray(new CyclicFrequencySet[allFrequencySequences.size()]);
				panel.add(UiButtonsForFrequencySet.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(), freqSeqArr));
			} else if ((allFrequencySequences.iterator().next()) instanceof Song) {
				final Song[] songArr = allFrequencySequences.toArray(new Song[allFrequencySequences.size()]);
				panel.add(UiButtonsForSong.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(), songArr));
			}
			panel.add(textArea);
		}
		panel.add(UiButtonsCommon.QUIT.createButton(yCoordinateTracker.buttonYcoordinate()));

		panel.setOpaque(true);
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
