package com.aqua.music.view;

import java.awt.Color;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.controller.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.controller.songs.Song;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class RehearseTabs {
	private static final Logger logger = LoggerFactory.getLogger(RehearseTabs.class);
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	private final JTabbedPane tabbedPane;

	// mutated state
	private JPanel rehearseWithPatternsPanel;

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
		RehearsePanel<CyclicFrequencySet> rehearsePanel = new RehearsePanel<CyclicFrequencySet>() {

			@Override
			protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					YCoordinateTracker yCoordinateTracker) {

				mainTab.add(createThaatDropdown(yCoordinateTracker));

				final Collection<CyclicFrequencySet> allFrequencySequences = new ArrayList<CyclicFrequencySet>();

				List<int[]> allPermutations = PermuatationsGenerator.PAIR.generatePermutations(frequencySet.ascendNotes());

				// add individual pattern button for each set
				for (int[] eachPermutation : allPermutations) {
					CyclicFrequencySet frequencySequence = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
							eachPermutation);
					JButton button = UiButtonsForFrequencySet.FREQUENCY_SET_PATTERNED_PLAYER.createButton(textArea,
							yCoordinateTracker.buttonYcoordinate(), new CyclicFrequencySet[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}

				return allFrequencySequences;
			}

			private JComboBox createThaatDropdown(YCoordinateTracker yCoordinateTracker) {
				final JComboBox box = new JComboBox(FrequencySet.SymmetricalSet.values());
				box.setBackground(Color.RED);
				box.setForeground(Color.GREEN);
				box.setSelectedItem(frequencySet);
				box.setBounds(UiButtonsForFrequencySet.X_COORIDNATE, yCoordinateTracker.yCoordinate, 500, UiButtonsForFrequencySet.HEIGHT());
				box.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JComboBox cbox = (JComboBox) arg0.getSource();
						FrequencySet selectedThaat = (FrequencySet) cbox.getSelectedItem();
						logger.info("selectedThaat [" + selectedThaat.name() + "]");
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
		RehearsePanel<CyclicFrequencySet> rehearsePanel = new RehearsePanel<CyclicFrequencySet>() {
			@Override
			protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					final YCoordinateTracker yCoordinateTracker) {

				final Collection<CyclicFrequencySet> allFrequencySequences = new ArrayList<CyclicFrequencySet>();

				for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
					CyclicFrequencySet frequencySequence = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);

					JButton button = UiButtonsForFrequencySet.FREQUENCY_SET_PLAYER.createButton(textArea,
							yCoordinateTracker.buttonYcoordinate(), new CyclicFrequencySet[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}
				return allFrequencySequences;
			}
		};
		return rehearsePanel.getPanel();
	}

	JPanel songTab() {
		RehearsePanel<Song> rehearsePanel = new RehearsePanel<Song>() {
			@Override
			protected Collection<Song> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					final YCoordinateTracker yCoordinateTracker) {

				final Collection<Song> allFrequencySequences = new ArrayList<Song>();

				for (Song eachSong : Song.values()) {
					JButton button = UiButtonsForSong.SONG_PLAYER.createButton(textArea,
							yCoordinateTracker.buttonYcoordinate(), eachSong);
					allFrequencySequences.add(eachSong);
					mainTab.add(button);
				}
				return allFrequencySequences;
			}
		};
		return rehearsePanel.getPanel();
	}
	
	private abstract class RehearsePanel<T> {
		private final JPanel panel;
		private final TextArea textArea;
		private final YCoordinateTracker yCoordinateTracker;

		RehearsePanel() {
			this.yCoordinateTracker = new YCoordinateTracker();
			this.textArea = createTextArea();
			this.panel = createBlankMainTab();
			Collection<T> allFrequencySequences = addSpecificButtons(panel, textArea, yCoordinateTracker);
			addCommonComponents(allFrequencySequences);
		}

		protected abstract Collection<T> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
				final YCoordinateTracker yCoordinateTracker);

		JPanel getPanel() {
			return panel;
		}

		private void addCommonComponents(Collection<T> allFrequencySequences) {
			// add play all button
			if ((allFrequencySequences.iterator().next()) instanceof CyclicFrequencySet) {
				final CyclicFrequencySet[] freqSeqArr = allFrequencySequences.toArray(new CyclicFrequencySet[allFrequencySequences.size()]);
				panel.add(UiButtonsForFrequencySet.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(), freqSeqArr));
			}else if ((allFrequencySequences.iterator().next()) instanceof Song) {
				final Song[] songArr = allFrequencySequences.toArray(new Song[allFrequencySequences.size()]);
				panel.add(UiButtonsForSong.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(), songArr));
			}
			panel.add(UiButtonsForFrequencySet.QUIT.createButton(null, yCoordinateTracker.buttonYcoordinate(), null));
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
			textArea.setBounds(UiButtonsForFrequencySet.X_COORIDNATE + 600, 60, 600, 600);
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
			yCoordinate += (UiButtonsForFrequencySet.HEIGHT()) + 10;
			return yCoordinate;
		}

		private void reset() {
			this.yCoordinate = 10;
		}
	}
}
