package com.aqua.music.view;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.controller.CyclicFrequencySet;
import com.aqua.music.controller.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.controller.puzzles.QuizController;
import com.aqua.music.controller.puzzles.QuizLevel;
import com.aqua.music.controller.puzzles.QuizLevel.QuizSection;
import com.aqua.music.controller.songs.Song;
import com.aqua.music.model.FrequencySet;
import com.aqua.music.model.FrequencySet.SymmetricalSet;

class RehearseTabs {
	private static final Logger logger = LoggerFactory.getLogger(RehearseTabs.class);
	private final JTabbedPane tabbedPane;

	// mutated state
	private JPanel rehearseWithPatternsPanel;
	private JPanel quizWithFrequencySetPanel;

	public RehearseTabs(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	void addPatternTab() {
		addPatternTab(SymmetricalSet.THAAT_KAFI);
	}
	
	void addQuizSectionTab() {
		addQuizSectionTab((QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz.quizLevels().iterator().next());
	}

	private final void addQuizSectionTab(QuizLevel<CyclicFrequencySet> quizLevel) {
		this.quizWithFrequencySetPanel = quizTabForFrequenciesQuizLevel(quizLevel);
		tabbedPane.addTab("Quiz with Frequency sets", quizWithFrequencySetPanel);
	}
	
	private final void addPatternTab(SymmetricalSet selectedThaat) {
		this.rehearseWithPatternsPanel = patternTabFor(selectedThaat);
		tabbedPane.addTab("Rehearse with pattern", rehearseWithPatternsPanel);
	}

	JPanel quizTabForFrequenciesQuizLevel(final QuizLevel<CyclicFrequencySet> quizLevel) {
		
		RehearsePanel<CyclicFrequencySet> rehearsePanel = new RehearsePanel<CyclicFrequencySet>() {
			@Override
			protected Collection<CyclicFrequencySet> addSpecificButtons(final JPanel mainTab,final TextArea textArea) {
				mainTab.add(createQuizLevelDropdown());
				final JLabel jLabel=createTextLabel();
				mainTab.add(jLabel);
				
				Collection<QuizSection<CyclicFrequencySet>> quizSections = quizLevel.quizSections();
				for( QuizSection<CyclicFrequencySet> each:quizSections){
					for(CyclicFrequencySet each1:each.quizItems()){
						JRadioButton radioButton= new JRadioButton(each1.freqSetNamePermuationAsText());
						mainTab.add(radioButton);
					}
					JButton button = UiButtonsForQuiz.FREQUENCY_SET_QUIZ.createButton(jLabel, buttonYcoordinate(),
							each );
					mainTab.add(button);
				}
				return Collections.EMPTY_LIST;
			}

			private JComboBox createQuizLevelDropdown() {
				Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
				
				final JComboBox box = new JComboBox(quizLevels.toArray());
				box.setBackground(Color.RED);
				box.setForeground(Color.GREEN);
				box.setSelectedItem(quizLevel);
				box.setBounds(UiButtonsForFrequencySet.X_COORIDNATE, buttonYcoordinate(), 500, UiButtonsForFrequencySet.HEIGHT());
				box.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JComboBox cbox = (JComboBox) arg0.getSource();
						QuizLevel selectedQuiz = (QuizLevel) cbox.getSelectedItem();
						logger.info("selectedQuiz [" + selectedQuiz.displayText() + "]");
						tabbedPane.remove(quizWithFrequencySetPanel);
						addQuizSectionTab(selectedQuiz);
						quizWithFrequencySetPanel.requestFocusInWindow();
						tabbedPane.setSelectedComponent(quizWithFrequencySetPanel);
					}
				});
				return box;
			}
		};
		return rehearsePanel.getPanel();
	}
	
	
	JPanel patternTabFor(final FrequencySet frequencySet) {
		RehearsePanel<CyclicFrequencySet> rehearsePanel = new RehearsePanel<CyclicFrequencySet>() {
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
				final JComboBox box = new JComboBox(FrequencySet.SymmetricalSet.values());
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
		RehearsePanel<CyclicFrequencySet> rehearsePanel = new RehearsePanel<CyclicFrequencySet>() {
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
		RehearsePanel<Song> rehearsePanel = new RehearsePanel<Song>() {
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
