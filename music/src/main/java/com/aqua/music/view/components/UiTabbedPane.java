package com.aqua.music.view.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.core.FrequencySet;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiTabbedPane {
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;
	private static final String TITLE_PLAIN_THAAT = "Thaat rehearse";
	private static final String TITLE_QUIZ_TAB = "Quiz with Frequency sets";

	private static final String TITLE_REHEARSE_TAB = "Rehearse with pattern";
	private static final String TITLE_SONG_TAB = "Song rehearse";

	private final JTabbedPane mainTabbedPane;

	private UiTabbedPane(JTabbedPane mainTabbedPane) {
		this.mainTabbedPane = mainTabbedPane;
	}

	public static JTabbedPane getTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		UiTabbedPane uiTabbedPane = new UiTabbedPane(tabbedPane);
		uiTabbedPane.addThaatTab(new RehearsePanel(PlayApi.getAllPlainThaat()));
		uiTabbedPane.addSongTab(new RehearsePanel(PlayApi.getAllSongs()));
		uiTabbedPane.addReloadablePanels(firstThaat, firstQuizLevel);
		tabbedPane.setOpaque(true);
		return tabbedPane;
	}

	private void addSongTab(RehearsePanel rehearsePanel) {
		mainTabbedPane.addTab(TITLE_SONG_TAB, rehearsePanel.getPanel());
	}

	private void addThaatTab(RehearsePanel rehearsePanel) {
		mainTabbedPane.addTab(TITLE_PLAIN_THAAT, rehearsePanel.getPanel());
	}

	private void addReloadablePanels(SymmetricalSet firstthaat, QuizLevel<CyclicFrequencySet> firstquizlevel) {
		mainTabbedPane.addTab(TITLE_REHEARSE_TAB, new PatternedFrequencySetPanel().newPanelWith(firstthaat));
		mainTabbedPane.addTab(TITLE_QUIZ_TAB, new QuizPanelContainer().newPanelWith(firstquizlevel));
	}

	private void refreshReloadablePanel(JPanel newPanel, String title) {
		mainTabbedPane.remove(mainTabbedPane.getSelectedComponent());
		mainTabbedPane.addTab(title, newPanel);
		newPanel.requestFocusInWindow();
		mainTabbedPane.setSelectedComponent(newPanel);
	}

	class PatternedFrequencySetPanel implements ReloadablePanelContainer {
		public final JPanel newPanelWith(Object obj) {
			final FrequencySet frequencySet = (FrequencySet) obj;
			RehearsePanel tabBuilder = new RehearsePanel(PlayApi.getAllPatternedThaat(frequencySet, 2));
			final JComboBox thaatDropdown = UiDropdown.thaatDropDown(tabBuilder.buttonYcoordinate(), frequencySet);
			thaatDropdown.addActionListener(new DropdownActionListener(this));
			tabBuilder.getPanel().add(thaatDropdown);
			return tabBuilder.getPanel();
		}

		@Override
		public void reLoadWith(Object obj) {
			SymmetricalSet selectedThaat = (SymmetricalSet) obj;
			logger.info("selectedThaat [" + selectedThaat.name() + "]");
			refreshReloadablePanel(newPanelWith(selectedThaat), TITLE_REHEARSE_TAB);
		}
	}

	class QuizPanelContainer implements ReloadablePanelContainer {
		public final JPanel newPanelWith(Object obj) {
			QuizLevel<CyclicFrequencySet> quizLevel = (QuizLevel<CyclicFrequencySet>) obj;
			QuizPanel quizPanel = new QuizPanel(quizLevel);
			JComboBox dd = createQuizLevelDropdown(quizLevel, quizPanel.buttonYcoordinate());
			JPanel quizWithFrequencySetPanel = quizPanel.getPanel();
			quizWithFrequencySetPanel.add(dd);
			return quizWithFrequencySetPanel;
		}

		@Override
		public void reLoadWith(Object obj) {
			QuizLevel selectedQuiz = (QuizLevel) obj;
			logger.info("selectedQuiz [" + selectedQuiz.displayText() + "]");
			refreshReloadablePanel(newPanelWith(selectedQuiz), TITLE_QUIZ_TAB);
		}

		private JComboBox createQuizLevelDropdown(final QuizLevel quizLevel, final int buttonYcoordinate) {
			final JComboBox box = UiDropdown.quizDropdown(buttonYcoordinate, quizLevel);
			box.addActionListener(new DropdownActionListener(this));
			return box;
		}
	}

	interface ReloadablePanelContainer {
		Logger logger = LoggerFactory.getLogger(ReloadablePanelContainer.class);

		JPanel newPanelWith(Object selectedValue);

		void reLoadWith(Object obj);
	}

	private static class DropdownActionListener implements ActionListener {
		private final ReloadablePanelContainer reloadablPanelContainer;

		DropdownActionListener(ReloadablePanelContainer reloadablTab) {
			this.reloadablPanelContainer = reloadablTab;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			reconfigureTab(cbox.getSelectedItem());
		}

		void reconfigureTab(Object obj) {
			reloadablPanelContainer.reLoadWith(obj);
		}
	}
}
