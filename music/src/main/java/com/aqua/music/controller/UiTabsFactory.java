package com.aqua.music.controller;

import java.awt.Color;
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

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.song.Song;

class UiTabsFactory {
	private static final Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);
	private final JTabbedPane tabbedPane;

	// mutated state
	private JPanel rehearseWithPatternsPanel;
	private JPanel quizWithFrequencySetPanel;

	public UiTabsFactory(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	void addPatternTab() {
		addPatternTab(SymmetricalSet.THAAT_KAFI);
	}

	void addQuizSectionTab() {
		QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels().iterator().next();
		addQuizSectionTab(firstQuizLevel);
	}

	final void addQuizSectionTab(QuizLevel<CyclicFrequencySet> quizLevel) {
		this.quizWithFrequencySetPanel = new UiTabForQuizes(this, quizLevel).getPanel();
		tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
	}

	private final void addPatternTab(SymmetricalSet selectedThaat) {
		this.rehearseWithPatternsPanel = patternTabFor(selectedThaat);
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}

	void reConfigureQuizTab(QuizLevel selectedQuiz) {
		tabbedPane.remove(quizWithFrequencySetPanel);
		addQuizSectionTab(selectedQuiz);
		quizWithFrequencySetPanel.requestFocusInWindow();
		tabbedPane.setSelectedComponent(quizWithFrequencySetPanel);
	}
	
	JPanel patternTabFor(final FrequencySet frequencySet) {
		AbstractRehearseTabPanels<CyclicFrequencySet> rehearsePanel = new AbstractRehearseTabPanels<CyclicFrequencySet>() {
			@Override
			protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {

				mainTab.add(createThaatDropdown());

				final Collection<CyclicFrequencySet> allFrequencySequences = new ArrayList<CyclicFrequencySet>();

				List<int[]> allPermutations = PermuatationsGenerator.PAIR.generatePermutations(frequencySet.ascendNotes());

				// add individual pattern button for each set
				for (int[] eachPermutation : allPermutations) {
					CyclicFrequencySet frequencySequence = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySetAndPermutation(frequencySet,
							eachPermutation);
					JButton button = UiButtonsForFrequencySet.FREQUENCY_SET_PATTERNED_PLAYER.createButton(textArea, buttonYcoordinate(),
							new CyclicFrequencySet[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}

				return allFrequencySequences;
			}

			private JComboBox createThaatDropdown() {
				final JComboBox box = new JComboBox(SymmetricalSet.values());
				box.setBackground(Color.RED);
				box.setForeground(Color.GREEN);
				box.setSelectedItem(frequencySet);
				box.setBounds(UiButtonsForFrequencySet.X_COORIDNATE, buttonYcoordinate(), 500, UiButtonsForFrequencySet.HEIGHT());
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
		AbstractRehearseTabPanels<CyclicFrequencySet> rehearsePanel = new AbstractRehearseTabPanels<CyclicFrequencySet>() {
			@Override
			protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {

				final Collection<CyclicFrequencySet> allFrequencySequences = new ArrayList<CyclicFrequencySet>();

				for (FrequencySet eachFrequencySet : SymmetricalSet.values()) {
					CyclicFrequencySet frequencySequence = CyclicFrequencySet.Type.SYMMETRICAL.forFrequencySet(eachFrequencySet);

					JButton button = UiButtonsForFrequencySet.FREQUENCY_SET_PLAYER.createButton(textArea, buttonYcoordinate(),
							new CyclicFrequencySet[] { frequencySequence });
					allFrequencySequences.add(frequencySequence);
					mainTab.add(button);
				}
				return allFrequencySequences;
			}
		};
		return rehearsePanel.getPanel();
	}

	JPanel songTab() {
		AbstractRehearseTabPanels<Song> rehearsePanel = new AbstractRehearseTabPanels<Song>() {
			@Override
			protected Collection<Song> addSpecificButtons(final JPanel mainTab, final TextArea textArea) {

				final Collection<Song> allFrequencySequences = new ArrayList<Song>();

				for (Song eachSong : Song.values()) {
					JButton button = UiButtonsForSong.SONG_PLAYER.createButton(textArea, buttonYcoordinate(), eachSong);
					allFrequencySequences.add(eachSong);
					mainTab.add(button);
				}
				return allFrequencySequences;
			}
		};
		return rehearsePanel.getPanel();
	}

}
