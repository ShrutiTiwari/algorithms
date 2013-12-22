package com.aqua.music.view.components;

import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_END_NOTE;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_NOTE_FRAGEMENT;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_OCTAVE;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_PATTERN_THAAT;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_QUIZ;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_START_NOTE;
import static com.aqua.music.view.components.DropdownActionCommands.COMMAND_THAAT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;

import open.music.api.NoteFragments;
import open.music.api.PlayApi;
import open.music.api.PracticeCustomization;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.BaseNote.Octave;
import com.aqua.music.model.core.ClassicalNote;
import com.aqua.music.model.core.Frequency;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

class UiDropdown {

	public static JComboBox endNoteDropDown(ClassicalNote[] displayClassicalNotes) {
		return createWith(COMMAND_END_NOTE, displayClassicalNotes, ClassicalNote.S3);
	}

	public static JComboBox noteFragmentDropDown() {
		return createWith(COMMAND_NOTE_FRAGEMENT, NoteFragments.values(), NoteFragments.ALL_NOTE);
	}

	public static JComboBox octaveDropDown() {
		return createWith(COMMAND_OCTAVE, Octave.values(), Octave.MAIN_OCTAVE);
	}

	public static JComboBox startNoteDropDown(ClassicalNote[] displayClassicalNotes) {
		return createWith(COMMAND_START_NOTE, displayClassicalNotes, ClassicalNote.S);
	}

	static JComboBox patternThaatDropDown() {
		return createWith(COMMAND_PATTERN_THAAT, PermuatationsGenerator.values(), null);
	}

	static JComboBox quizDropdown(Object selectedItem) {
		Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
		return createWith(COMMAND_QUIZ, quizLevels.toArray(), selectedItem);
	}

	static JComboBox thaatDropDown(Object selectedItem) {
		return createWith(COMMAND_THAAT, SymmetricalSet.values(), selectedItem);
	}

	private static JComboBox createWith(DropdownActionCommands aCommand, Object[] objects, Object selectedItem) {
		final JComboBox box = new JComboBox(objects);
		box.setActionCommand(aCommand.name());
		box.setBackground(UiColor.ALT_BG_CLR);
		box.setForeground(UiColor.BG_CLR);
		if (selectedItem != null) {
			box.setSelectedItem(selectedItem);
		} else {
			box.setSelectedItem(objects[0]);
		}

		return box;
	}

	static class NoteFragementAndOctaveActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(NoteFragementAndOctaveActionListener.class);
		private final MusicPanel musicPanel;
		private NoteFragments noteFragment;
		private Octave octave;
		private Frequency[] startEndPoints = new Frequency[2];
		private final PlayApi playApi = SingletonFactory.PLAY_API;
		private StateDependentUi stateDependentUi;

		NoteFragementAndOctaveActionListener(MusicPanel musicPanel, StateDependentUi stateDependentUi) {
			this.musicPanel = musicPanel;
			this.noteFragment = NoteFragments.ALL_NOTE;
			this.octave = Octave.MAIN_OCTAVE;
			this.startEndPoints = new PracticeCustomization(noteFragment, octave).getStartEndPoint();
			this.stateDependentUi=stateDependentUi;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			DropdownActionCommands actionCommand = DropdownActionCommands.valueOf(cbox.getActionCommand());

			switch (actionCommand) {
			case COMMAND_NOTE_FRAGEMENT:
				this.noteFragment = (NoteFragments) obj;
				this.startEndPoints = new PracticeCustomization(noteFragment, octave).getStartEndPoint();
				break;
			case COMMAND_OCTAVE:
				this.octave = (Octave) obj;
				this.startEndPoints = new PracticeCustomization(noteFragment, octave).getStartEndPoint();
				break;
			case COMMAND_START_NOTE:
				ClassicalNote startNote = (ClassicalNote) obj;
				if (startNote.frequencyInHz() > startEndPoints[1].frequencyInHz()) {
					musicPanel.alertLabel().setText("Invalid combination, startNote cant be less than endNote.");
					musicPanel.alertLabel().setVisible(true);
					return;
				}
				startEndPoints[0] = startNote;
				break;
			case COMMAND_END_NOTE:
				ClassicalNote endNote = (ClassicalNote) obj;
				if (endNote.frequencyInHz() < startEndPoints[0].frequencyInHz()) {
					musicPanel.alertLabel().setText("Invalid combination, startNote cant be less than endNote.");
					musicPanel.alertLabel().setVisible(true);
					return;
				}
				startEndPoints[1] = endNote;
				break;
			default:
				return;
			}
			musicPanel.alertLabel().setVisible(false);
			musicPanel.customizationLabel().setText("Customized play between [" + startEndPoints[0] + "] to [" + startEndPoints[1] + "]");
			stateDependentUi.setStartEndPoints(startEndPoints);
		}
	}

	static class QuizDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(QuizDropdownActionListener.class);
		private final MusicPanel musicPanel;

		public QuizDropdownActionListener(MusicPanel musicPanel) {
			this.musicPanel = musicPanel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			QuizLevel<CyclicFrequencySet> quizLevel = (QuizLevel<CyclicFrequencySet>) obj;
			musicPanel.refreshSpecificComponentPanel(quizLevel);
		}
	}

	static class ThaatAndPatternDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(ThaatAndPatternDropdownActionListener.class);
		private FrequencySet frequencySet;
		private final MusicPanel musicPanel;
		private PermuatationsGenerator patternItemsCount;
		private final PlayApi playApi = SingletonFactory.PLAY_API;

		ThaatAndPatternDropdownActionListener(MusicPanel musicPanel, FrequencySet frequencySet2, PermuatationsGenerator patternItemsCount) {
			this.musicPanel = musicPanel;
			this.frequencySet = frequencySet2;
			this.patternItemsCount = patternItemsCount;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			if (obj instanceof FrequencySet) {
				this.frequencySet = (FrequencySet) obj;
			} else {
				this.patternItemsCount = (PermuatationsGenerator) obj;
			}
			musicPanel.refreshSpecificComponentPanel(playApi.getAllPatternedThaat(frequencySet, patternItemsCount));
		}
	}
}
