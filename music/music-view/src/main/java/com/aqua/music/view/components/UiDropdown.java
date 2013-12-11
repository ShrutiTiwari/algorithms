package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.sound.midi.Instrument;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import open.music.api.AudioPlayerSettings;
import open.music.api.PlayApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet.PermuatationsGenerator;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiDropdown {
	public static JComponent instrumentDropDown(Object selectedItem) {
		Instrument[] allInstruments = PlayApi.getAllInstruments();

		String[] instrumentNames = new String[allInstruments.length];
		int i = 0;
		int maxNameLength = 150;
		for (Instrument each : allInstruments) {
			String name = each.getName().trim();
			instrumentNames[i++] = name;
			if (maxNameLength < name.length()) {
				maxNameLength = name.length();
			}
		}

		JList jList = new JList(instrumentNames);
		InstrumentDropdownActionListener listener = new InstrumentDropdownActionListener(jList, allInstruments);

		return createScrollPane(jList, listener, 10, maxNameLength);
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

	static class InstrumentDropdownActionListener implements ActionListener, ListSelectionListener {
		Logger logger = LoggerFactory.getLogger(InstrumentDropdownActionListener.class);
		private final JList jList;
		private Instrument[] allInstruments;

		/**
		 * @param jList
		 * @param allInstruments
		 */
		public InstrumentDropdownActionListener(JList jList, Instrument[] allInstruments) {
			this.jList = jList;
			this.allInstruments = allInstruments;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			AudioPlayerSettings.changeInstrumentTo(obj);
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting() == false) {
				int selectedIndex = jList.getSelectedIndex();
				if (selectedIndex != -1) {
					AudioPlayerSettings.changeInstrumentTo(allInstruments[selectedIndex]);
				}
			}
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
		private final MusicPanel musicPanel;
		private FrequencySet frequencySet;
		private PermuatationsGenerator patternItemsCount;

		public ThaatAndPatternDropdownActionListener(MusicPanel musicPanel, FrequencySet frequencySet2,
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
			musicPanel.refreshSpecificComponentPanel(PlayApi.getAllPatternedThaat(frequencySet, patternItemsCount));
		}
	}

	static JScrollPane createScrollPane(JList jList, ListSelectionListener listener, int itemCount, int maxNameLength) {
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setLayoutOrientation(JList.VERTICAL_WRAP);
		jList.setVisibleRowCount(itemCount);
		jList.setFixedCellWidth(maxNameLength);
		jList.addListSelectionListener(listener);
		JScrollPane scrollPane = new JScrollPane(jList);
		scrollPane.setAutoscrolls(true);
		// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scrollPane;
	}
}
