package com.aqua.music.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;

import open.music.api.NoteFragments;
import open.music.api.PlayApi;
import open.music.api.PracticeCustomization;
import open.music.api.SingletonFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.BaseNote.Octave;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

class UiDropdown {
	static JComboBox patternThaatDropDown() {
		return createWith(PermuatationsGenerator.values(), null);
	}

	static JComboBox quizDropdown(Object selectedItem) {
		Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
		return createWith(quizLevels.toArray(), selectedItem);
	}

	static JComboBox thaatDropDown(Object selectedItem) {
		return createWith(SymmetricalSet.values(), selectedItem);
	}

	private static JComboBox createWith(Object[] objects, Object selectedItem) {
		final JComboBox box = new JComboBox(objects);
		box.setBackground(UiColor.ALT_BG_CLR);
		box.setForeground(UiColor.BG_CLR);
		if (selectedItem != null) {
			box.setSelectedItem(selectedItem);
		} else {
			box.setSelectedItem(objects[0]);
		}
		
		return box;
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
		private final MusicPanel musicPanel;
		private FrequencySet frequencySet;
		private PermuatationsGenerator patternItemsCount;
		private final PlayApi playApi=SingletonFactory.PLAY_API;

		ThaatAndPatternDropdownActionListener(MusicPanel musicPanel, FrequencySet frequencySet2,
				PermuatationsGenerator patternItemsCount) {
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
	static class NoteFragementAndOctaveActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(NoteFragementAndOctaveActionListener.class);
		private final MusicPanel musicPanel;
		private NoteFragments noteFragment;
		private Octave octave;
		private final PlayApi playApi=SingletonFactory.PLAY_API;

		NoteFragementAndOctaveActionListener(MusicPanel musicPanel) {
			this.musicPanel = musicPanel;
			this.noteFragment = NoteFragments.ALL_NOTE;
			this.octave = Octave.MAIN_OCTAVE;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			if (obj instanceof NoteFragments) {
				this.noteFragment = (NoteFragments) obj;
			} else {
				this.octave = (Octave) obj;
			}
			musicPanel.refreshSpecificComponentPanel(playApi.getCustomizedThaat(new PracticeCustomization(noteFragment, octave)));
		}
	}
	
	/**
	 * @return
	 */
	public static JComboBox noteFragmentDropDown() {
		return createWith(NoteFragments.values(), NoteFragments.ALL_NOTE);
	}

	/**
	 * @return
	 */
	public static JComboBox octaveDropDown() {
		return createWith(Octave.values(), Octave.MAIN_OCTAVE);
	}
}
