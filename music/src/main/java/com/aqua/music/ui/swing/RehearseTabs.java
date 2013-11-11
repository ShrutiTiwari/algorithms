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

import com.aqua.music.items.PatternGenerator;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class RehearseTabs {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	
	private final JTabbedPane tabbedPane;
	private JPanel rehearseWithPatternsPanel ;
	
	public RehearseTabs(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public final void addPatternTab(SymmetricalSet selectedThaat) {
		this.rehearseWithPatternsPanel = patternTabFor(selectedThaat);
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);		
	}

	JPanel patternTabFor(final FrequencySet frequencySet) {
		RehearsePanel rehearsePanel = new RehearsePanel() {
			
			@Override
			protected Collection<JButton> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
					YCoordinateTracker yCoordinateTracker) {
				
				mainTab.add(createThaatDropdown(yCoordinateTracker));
				
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

			private JComboBox createThaatDropdown(YCoordinateTracker yCoordinateTracker) {
				final JComboBox box=new JComboBox(FrequencySet.SymmetricalSet.values());
				box.setSelectedItem(frequencySet);
				box.setBounds(UiPanelButtons.X_COORIDNATE, yCoordinateTracker.yCoordinate, 500, UiPanelButtons.HEIGHT());
				box.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JComboBox cbox=(JComboBox)arg0.getSource();
						FrequencySet selectedThaat= (FrequencySet) cbox.getSelectedItem();
						System.out.println(selectedThaat);
						tabbedPane.remove(rehearseWithPatternsPanel);
						addPatternTab((SymmetricalSet)selectedThaat);
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
			Collection<JButton> allSpecificButtons = addSpecificButtons(panel, textArea, yCoordinateTracker);
			addCommonComponents(allSpecificButtons);
		}

		protected abstract Collection<JButton> addSpecificButtons(final JPanel mainTab, final TextArea textArea,
				final YCoordinateTracker yCoordinateTracker);

		JPanel getPanel() {
			return panel;
		}

		private void addCommonComponents(Collection<JButton> allSpecificButtons) {
			// add play all button
			panel.add(UiPanelButtons.PLAY_ALL.createButton(textArea, yCoordinateTracker.buttonYcoordinate(),
					allSpecificButtons.toArray()));
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
	 * Synchronisation policy: ThreadConfined - Meant for SingleThreaded
	 * use.
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
