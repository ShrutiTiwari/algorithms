package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.PlayApi;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiDropdown {
	public static JComponent instrumentDropDown(Object selectedItem) {
		JComboBox instrumentDropdown = createWith(PlayApi.getAllInstruments(), selectedItem);
		instrumentDropdown.addActionListener(new InstrumentDropdownActionListener());
		return instrumentDropdown;
	}

	public static JComboBox patternThaatDropDown() {
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
		box.setBackground(Color.WHITE);
		box.setForeground(Color.GRAY);
		if (selectedItem != null) {
			box.setSelectedItem(selectedItem);
		} else {
			box.setSelectedItem(objects[0]);
		}
		return box;
	}

	static class InstrumentDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(InstrumentDropdownActionListener.class);

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			AudioPlayerSettings.changeInstrumentTo(obj);
		}
	}

	static class QuizDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(QuizDropdownActionListener.class);
		private final AbstractMusicPanel musicPanel;

		public QuizDropdownActionListener(AbstractMusicPanel musicPanel) {
			this.musicPanel = musicPanel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			QuizLevel<CyclicFrequencySet> quizLevel = (QuizLevel<CyclicFrequencySet>) obj;
			musicPanel.renewSpecificComponentPanel(quizLevel);
		}
	}

	static class ThaatAndPatternDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(ThaatAndPatternDropdownActionListener.class);
		private final AbstractMusicPanel musicPanel;
		private FrequencySet frequencySet ;
		private PermuatationsGenerator patternItemsCount;
		public ThaatAndPatternDropdownActionListener(AbstractMusicPanel musicPanel, FrequencySet frequencySet2, PermuatationsGenerator patternItemsCount) {
			this.musicPanel = musicPanel;
			this.frequencySet=frequencySet2;
			this.patternItemsCount=patternItemsCount;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			if (obj instanceof FrequencySet) {
				this.frequencySet = (FrequencySet) obj;
			} else  {
				this.patternItemsCount = (PermuatationsGenerator) obj;
			}
			musicPanel.renewSpecificComponentPanel(PlayApi.getAllPatternedThaat(frequencySet, patternItemsCount));
		}
	}
}
