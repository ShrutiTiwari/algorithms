package com.aqua.music.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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

public class UiTabs {
	private static final String TITLE_PLAIN_THAAT = "Thaat rehearse";
	private static final String TITLE_QUIZ_TAB = "Quiz with Frequency sets";
	private static final String TITLE_REHEARSE_TAB = "Rehearse with pattern";
	private static final String TITLE_SONG_TAB = "Song rehearse";
	private final JTabbedPane mainTabbedPane;
	private final PatternedFrequencySetTab patternedFrequencySetTab;
	private final QuizTab quizTab;
	
	public UiTabs(JTabbedPane mainTabbedPane) {
		this.mainTabbedPane = mainTabbedPane;
		this.quizTab = new QuizTab();
		this.patternedFrequencySetTab = new PatternedFrequencySetTab();
	}
	
	public void addPatternedTab(SymmetricalSet firstthaat, boolean isFocus) {
		if(patternedFrequencySetTab.getJPanel()!=null){
			mainTabbedPane.remove(patternedFrequencySetTab.getJPanel());
		}
		JPanel newTab = patternedFrequencySetTab.intializeWith(firstthaat);
		mainTabbedPane.addTab(TITLE_REHEARSE_TAB, newTab);
		
		setFocus(isFocus,newTab);
		
	}
	
	public void addPlaneThaat(JPanel panel){
		mainTabbedPane.addTab(TITLE_PLAIN_THAAT, panel);
	}
	
	public void addQuiz(QuizLevel<CyclicFrequencySet> quizLevel, boolean isFocus){
		if(quizTab.getJPanel()!=null){
			mainTabbedPane.remove(quizTab.getJPanel());
		}
		JPanel newTab = quizTab.intializeWith(quizLevel);
		mainTabbedPane.addTab(TITLE_QUIZ_TAB, newTab);
		
		setFocus(isFocus,newTab);
	}
	
	public void addSong(JPanel panel){
		mainTabbedPane.addTab(TITLE_SONG_TAB, panel);
	}

	synchronized void clearOldAndSetNew(final String title, JPanel tabbedPane1, boolean startup, Reloadable reloadableComponent) {
		if (!startup) {
			mainTabbedPane.remove(reloadableComponent.getJPanel());
		}
		
		mainTabbedPane.addTab(title, tabbedPane1);
		
	}

	private void setFocus(boolean isFocus, JPanel newTab) {
		if (isFocus) {
			newTab.requestFocusInWindow();
			mainTabbedPane.setSelectedComponent(newTab);
		}
	}
	
	private static class DropdownActionListener implements ActionListener {
		private final Reloadable reloadablTab;

		DropdownActionListener(Reloadable reloadablTab) {
			this.reloadablTab = reloadablTab;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			reconfigureTab(cbox.getSelectedItem());
		}

		void reconfigureTab(Object obj) {
			reloadablTab.reConfigure(obj);
		}
	}

 class PatternedFrequencySetTab implements Reloadable {
		private JPanel rehearseWithPatternsPanel;

		@Override
		public JPanel getJPanel() {
			return rehearseWithPatternsPanel;
		}

		@Override
		public void reConfigure(Object obj) {
			SymmetricalSet selectedThaat = (SymmetricalSet) obj;
			logger.info("selectedThaat [" + selectedThaat.name() + "]");
			addPatternedTab(selectedThaat, true);
		}
		
		final JPanel intializeWith(final FrequencySet frequencySet) {
			RehearsePanel tabBuilder = new RehearsePanel(PlayApi.getAllPatternedThaat(frequencySet, 2));
			final JComboBox thaatDropdown = UiDropdown.createWith(SymmetricalSet.values(), tabBuilder.buttonYcoordinate(), frequencySet);
			thaatDropdown.addActionListener(new DropdownActionListener(this));
			tabBuilder.getPanel().add(thaatDropdown);
			rehearseWithPatternsPanel = tabBuilder.getPanel();
			return rehearseWithPatternsPanel;
		}
	}

	class QuizTab implements Reloadable {
		private JPanel quizWithFrequencySetPanel;

		@Override
		public JPanel getJPanel() {
			return quizWithFrequencySetPanel;
		}

		@Override
		public void reConfigure(Object obj) {
			QuizLevel selectedQuiz = (QuizLevel) obj;
			logger.info("selectedQuiz [" + selectedQuiz.displayText() + "]");
			addQuiz(selectedQuiz, true);
		}

		final JPanel intializeWith(QuizLevel<CyclicFrequencySet> quizLevel) {
			QuizPanel quizTabBuilder = new QuizPanel(quizLevel);
			JComboBox dd = createQuizLevelDropdown(quizLevel, quizTabBuilder.buttonYcoordinate());
			this.quizWithFrequencySetPanel = quizTabBuilder.getPanel();
			quizWithFrequencySetPanel.add(dd);
			return quizWithFrequencySetPanel;
		}

		private JComboBox createQuizLevelDropdown(final QuizLevel quizLevel, final int buttonYcoordinate) {
			Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
			final JComboBox box = UiDropdown.createWith(quizLevels.toArray(), buttonYcoordinate, quizLevel);
			box.addActionListener(new DropdownActionListener(this));
			return box;
		}
	}

	interface Reloadable {
		Logger logger = LoggerFactory.getLogger(UiTabsFactory.class);		
		JPanel getJPanel();
		void reConfigure(Object obj);
	}
}
