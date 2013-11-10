package com.aqua.music.ui.swing;

import java.awt.Dimension;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aqua.music.items.PatternGenerator;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class RehearseTabs {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	JPanel patternTabFor(final FrequencySet frequencySet) {
		RehearseTab rehearseTab = new RehearseTab() {
			@Override
			protected Collection<JButton> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					YCoordinateTracker yCoordinateTracker) {

				final Collection<JButton> allSpecificButtons = new ArrayList<JButton>();

				List<int[]> pairPaterrns = PatternGenerator.PAIR.generatePatterns(frequencySet.ascendNotes());

				// add individual pattern button for each set
				for (int[] eachPattern : pairPaterrns) {
					JButton button = UiPanelButtons.FREQUENCY_SET_PATTERNED_PLAYER.createButton(textArea,
							yCoordinateTracker.buttonYcoordinate(), new Object[] { frequencySet, eachPattern });
					allSpecificButtons.add(button);
					mainTab.add(button);
				}

				return allSpecificButtons;
			}
		};
		return rehearseTab.getTab();
	}

	JPanel plainTab() {
		RehearseTab rehearseTab = new RehearseTab() {
			@Override
			protected Collection<JButton> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					final YCoordinateTracker yCoordinateTracker) {

				final Collection<JButton> allSpecificButtons = new ArrayList<JButton>();

				for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
					JButton button = UiPanelButtons.FREQUENCY_SET_PLAYER.createButton(textArea, yCoordinateTracker.buttonYcoordinate(),
							new Object[] { eachFrequencySet });
					allSpecificButtons.add(button);
					mainTab.add(button);
				}
				return allSpecificButtons;
			}
		};
		return rehearseTab.getTab();
	}

	private abstract class RehearseTab {
		private final JPanel mainTab;
		private final TextArea textArea;
		private final YCoordinateTracker yCoordinateTracker;

		RehearseTab() {
			this.yCoordinateTracker = new YCoordinateTracker();
			this.textArea = createTextArea();
			this.mainTab = createBlankMainTab();
			Collection<JButton> allSpecificButtons = addSpecificButtons(mainTab, textArea, yCoordinateTracker);
			addCommonComponents(allSpecificButtons);
		}

		protected abstract Collection<JButton> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
				final YCoordinateTracker yCoordinateTracker);

		JPanel getTab() {
			return mainTab;
		}

		private void addCommonComponents(Collection<JButton> allSpecificButtons) {
			// add play all button
			mainTab.add(UiPanelButtons.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(),
					allSpecificButtons.toArray()));
			mainTab.add(UiPanelButtons.QUIT.createButton(null, yCoordinateTracker.buttonYcoordinate(), null));
			mainTab.add(textArea);
			mainTab.setOpaque(true);
		}

		private JPanel createBlankMainTab() {
			JPanel mainTab = new JPanel();
			mainTab.setLayout(null);
			mainTab.setPreferredSize(preferredSizeForThaatPanel);
			return mainTab;
		}

		private TextArea createTextArea() {
			TextArea textArea = new TextArea("Hello shrutz");
			textArea.setVisible(true);
			textArea.setBounds(UiPanelButtons.X_COORIDNATE + 600, 60, 500, 600);
			return textArea;
		}
	}

	/**
	 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded
	 * use.
	 * */

	private class YCoordinateTracker {
		private static final int START = 10;
		// mutated variable
		private int yCoordinate = START;

		private void reset() {
			this.yCoordinate = 10;
		}

		private int buttonYcoordinate() {
			yCoordinate += (UiPanelButtons.HEIGHT()) + 10;
			return yCoordinate;
		}
	}
}
