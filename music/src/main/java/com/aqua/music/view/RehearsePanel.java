package com.aqua.music.view;

import java.awt.Dimension;
import java.awt.TextArea;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.controller.songs.Song;

abstract class RehearsePanel<T> {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	private final JPanel panel;
	private final TextArea textArea;
	private final YCoordinateTracker yCoordinateTracker;

	RehearsePanel() {
		this.yCoordinateTracker = new YCoordinateTracker();
		this.textArea = createTextArea();
		this.panel = createBlankMainTab();
		Collection<T> allFrequencySequences = addSpecificButtons(panel, textArea);
		addCommonComponents(allFrequencySequences);
	}

	protected abstract Collection<T> addSpecificButtons(final JPanel mainTab, final TextArea textArea);

	JPanel getPanel() {
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
		panel.add(UiButtonsForFrequencySet.QUIT.createButton(null, yCoordinateTracker.buttonYcoordinate(), null));
		
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
		textArea.setBounds(UiButtonsForFrequencySet.X_COORIDNATE + 600, 60, 600, 600);
		return textArea;
	}

	final int buttonYcoordinate() {
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
			yCoordinate += (UiButtonsForFrequencySet.HEIGHT()) + 10;
			return yCoordinate;
		}

		private void reset() {
			this.yCoordinate = 10;
		}
	}
}
