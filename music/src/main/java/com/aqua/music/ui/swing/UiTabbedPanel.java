package com.aqua.music.ui.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.items.PatternGenerator;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class UiTabbedPanel extends JPanel {
	static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);

	private final YCoordinateTracker yCoordinateTracker = new YCoordinateTracker();
	
	public UiTabbedPanel() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		final JPanel plainTab = createPlainTab();
		final JPanel patternTab = createPatternsTab();

		tabbedPane.addTab("Rehearse plain items", plainTab);
		tabbedPane.addTab("Rehearse kafi with pattern", patternTab);

		tabbedPane.setOpaque(true);
		add(tabbedPane);
	}

	private void addQuitButton(JPanel... panels) {
		for (JPanel each : panels) {
			each.add(UiPanelButtons.QUIT.createButton(null, yCoordinateTracker.yCoordinate(), null));
		}
	}

	private JPanel createPlainTab() {
		yCoordinateTracker.reset();
		final JPanel plainTab = createTab();
		
		TextArea textArea = createTextArea(yCoordinateTracker);
		
		final Collection<JButton> allButtons= new ArrayList<JButton>();
		// add individual frequency-set buttons
		for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
			JButton button = UiPanelButtons.FREQUENCY_SET_PLAYER.createButton(textArea, yCoordinateTracker.yCoordinate(), new Object[] { eachFrequencySet });
			allButtons.add(button);
			plainTab.add(button);
		}
		// add play all button
		addPlayAllButton(plainTab, textArea, allButtons);

		addQuitButton(plainTab);
		
		plainTab.add(textArea);
		
		plainTab.setOpaque(true);
		return plainTab;
	}

	private void addPlayAllButton(final JPanel givenTab, TextArea textArea, final Collection<JButton> allButtons) {
		givenTab.add(UiPanelButtons.PLAY_ALL.createButton(textArea, yCoordinateTracker.yCoordinate(), allButtons.toArray()));
	}

	private JPanel createPatternsTab() {
		yCoordinateTracker.reset();
		JPanel patternTab = createTab();
		
		TextArea textArea = createTextArea(yCoordinateTracker);

		FrequencySet frequencySet = SymmetricalSet.THAAT_KAFI;
		List<int[]> pairPaterrns = PatternGenerator.PAIR.generatePatterns(frequencySet.ascendNotes());

		final Collection<JButton> allButtons= new ArrayList<JButton>();
		
		// add individual pattern button for each set
		for (int[] eachPattern : pairPaterrns) {
			JButton button = UiPanelButtons.FREQUENCY_SET_PATTERNED_PLAYER.createButton(textArea, yCoordinateTracker.yCoordinate(), new Object[] { frequencySet, eachPattern });
			allButtons.add(button);
			patternTab.add(button);
		}
		
		addPlayAllButton(patternTab, textArea, allButtons);
		
		patternTab.add(textArea);
		
		addQuitButton(patternTab);
		return patternTab;
	}

	private JPanel createTab() {
		JPanel playablePanel = new JPanel();
		playablePanel.setLayout(null);
		playablePanel.setPreferredSize(preferredSizeForThaatPanel);
		return playablePanel;
	}
	
	
	private TextArea createTextArea(YCoordinateTracker yCoordinateTracker2){
		TextArea textArea = new TextArea("Hello shrutz");
		textArea.setVisible(true);
		textArea.setBounds(UiPanelButtons.X_COORIDNATE+600, 60, 500, 600);
		return textArea;
	}
	
	/**
	 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded use.
	 * */
	
	private class YCoordinateTracker {
		private static final int START = 10;
		//mutated variable
		private int yCoordinate = START;

		private void reset(){
			this.yCoordinate = 10;
		}
		
		private int yCoordinate() {
			yCoordinate += (UiPanelButtons.HEIGHT()) + 10;
			return yCoordinate;
		}
	}
}
