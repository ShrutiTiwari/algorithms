package com.aqua.music.view.components;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.view.components.UiDropdown.DropdownActionListener;

class ReloadablePanelContainer  {
	private final Logger logger = LoggerFactory.getLogger(ReloadablePanelContainer.class);
	private final UiTabbedPane uiTabbedPane;

	public ReloadablePanelContainer(UiTabbedPane uiTabbedPane) {
		this.uiTabbedPane=uiTabbedPane;
	}

	public final Map.Entry<String, JPanel> newPanelWith(Object obj) {
		if (obj instanceof QuizLevel) {
			QuizLevel<CyclicFrequencySet> quizLevel = (QuizLevel<CyclicFrequencySet>) obj;
			QuizPanel quizPanel = new QuizPanel(quizLevel);
			
			final JComboBox quizDropdown = UiDropdown.quizDropdown(quizPanel.buttonYcoordinate(), quizLevel);
			quizDropdown.addActionListener(new DropdownActionListener(uiTabbedPane,this));
			final JPanel quizWithFrequencySetPanel = quizPanel.getPanel();
			quizWithFrequencySetPanel.add(quizDropdown);
			
			return createMapEntry(UiTabbedPane.TITLE_QUIZ_TAB,quizWithFrequencySetPanel);
		} else {
			final FrequencySet frequencySet = (FrequencySet) obj;
			RehearsePanel rehearsePanel = new RehearsePanel(PlayApi.getAllPatternedThaat(frequencySet, 2));
			
			final JComboBox thaatDropdown = UiDropdown.thaatDropDown(rehearsePanel.buttonYcoordinate(), frequencySet);
			thaatDropdown.addActionListener(new DropdownActionListener(uiTabbedPane,this));				
			rehearsePanel.getPanel().add(thaatDropdown);
			
			
			return createMapEntry(UiTabbedPane.TITLE_REHEARSE_TAB,rehearsePanel.getPanel());
		}
	}

	private Entry<String, JPanel> createMapEntry(final String title, final JPanel quizWithFrequencySetPanel) {
		return new Map.Entry<String, JPanel>() {
			@Override
			public String getKey() {
				return title;
			}

			@Override
			public JPanel getValue() {
				return quizWithFrequencySetPanel;
			}

			@Override
			public JPanel setValue(JPanel value) {
				return quizWithFrequencySetPanel;
			}
		};
	}
}