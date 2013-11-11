package com.aqua.music.ui.swing;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.logic.FrequencySequence;
import com.aqua.music.logic.PermuatationGenerator;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class RehearseTabs {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);

	private JPanel rehearseWithPatternsPanel;
	private final JTabbedPane tabbedPane;

	public RehearseTabs(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	void addPatternTab() {
		addPatternTab(SymmetricalSet.THAAT_KAFI);
	}

	private final void addPatternTab(SymmetricalSet selectedThaat) {
		this.rehearseWithPatternsPanel = patternTabFor(selectedThaat);
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}

	JPanel patternTabFor(final FrequencySet frequencySet) {
		RehearsePanel rehearsePanel = new RehearsePanel() {

			@Override
			protected Collection<FrequencySequence> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					YCoordinateTracker yCoordinateTracker) {

				mainTab.add(createThaatDropdown(yCoordinateTracker));

				final Collection<FrequencySequence> allFrequencySequences = new ArrayList<FrequencySequence>();

				List<int[]> allPermutations = PermuatationGenerator.PAIR.generatePermutations(frequencySet.ascendNotes());

				// add individual pattern button for each set
				for (int[] eachPermutation : allPermutations) {
					FrequencySequence frequencySequence = FrequencySequence.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet, eachPermutation);
					JButton button = UiPanelButtons.FREQUENCY_SET_PATTERNED_PLAYER.createButton(textArea,
							yCoordinateTracker.buttonYcoordinate(), new Object[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}

				return allFrequencySequences;
			}

			private JComboBox createThaatDropdown(YCoordinateTracker yCoordinateTracker) {
				final JComboBox box = new JComboBox(FrequencySet.SymmetricalSet.values());
				box.setSelectedItem(frequencySet);
				box.setBounds(UiPanelButtons.X_COORIDNATE, yCoordinateTracker.yCoordinate, 500, UiPanelButtons.HEIGHT());
				box.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JComboBox cbox = (JComboBox) arg0.getSource();
						FrequencySet selectedThaat = (FrequencySet) cbox.getSelectedItem();
						System.out.println(selectedThaat);
						tabbedPane.remove(rehearseWithPatternsPanel);
						addPatternTab((SymmetricalSet) selectedThaat);
						rehearseWithPatternsPanel.requestFocusInWindow();
						tabbedPane.setSelectedComponent(rehearseWithPatternsPanel);
					}
				});
				return box;
			}
		};
		return rehearsePanel.getPanel();
	}

	JPanel plainTab() {
		RehearsePanel rehearseTab = new RehearsePanel() {
			@Override
			protected Collection<FrequencySequence> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					final YCoordinateTracker yCoordinateTracker) {

				final Collection<FrequencySequence> allFrequencySequences = new ArrayList<FrequencySequence>();

				for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
					FrequencySequence frequencySequence = FrequencySequence.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);
					
					JButton button = UiPanelButtons.FREQUENCY_SET_PLAYER.createButton(textArea, yCoordinateTracker.buttonYcoordinate(),
							new Object[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}
				return allFrequencySequences;
			}
		};
		return rehearseTab.getPanel();
	}

	private abstract class RehearsePanel {
		private final JPanel panel;
		private final TextArea textArea;
		private final YCoordinateTracker yCoordinateTracker;

		RehearsePanel() {
			this.yCoordinateTracker = new YCoordinateTracker();
			this.textArea = createTextArea();
			this.panel = createBlankMainTab();
			Collection<FrequencySequence> allFrequencySequences = addSpecificButtons(panel, textArea, yCoordinateTracker);
			addCommonComponents(allFrequencySequences);
		}

		protected abstract Collection<FrequencySequence> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
				final YCoordinateTracker yCoordinateTracker);

		JPanel getPanel() {
			return panel;
		}

		private void addCommonComponents(Collection<FrequencySequence> allFrequencySequences) {
			// add play all button
			panel.add(UiPanelButtons.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(), allFrequencySequences.toArray()));
			panel.add(UiPanelButtons.QUIT.createButton(null, yCoordinateTracker.buttonYcoordinate(), null));
			panel.add(textArea);
			panel.setOpaque(true);
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
	 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded use.
	 * */

	private class YCoordinateTracker {
		private static final int START = 10;
		// mutated variable
		private int yCoordinate = START;

		private int buttonYcoordinate() {
			yCoordinate += (UiPanelButtons.HEIGHT()) + 10;
			return yCoordinate;
		}

		private void reset() {
			this.yCoordinate = 10;
		}
	}
}
