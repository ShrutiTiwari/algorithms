package com.aqua.music.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.api.PlayApi;
import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.view.components.RehearsePanel;
import com.aqua.music.view.components.UiTabs;

class UiMainFrame {
	private static final String frameTitle = "Music";
	private static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
	private static final QuizLevel<CyclicFrequencySet> firstQuizLevel = (QuizLevel<CyclicFrequencySet>) QuizController.FrequencySetQuiz
			.quizLevels().iterator().next();
	private static final SymmetricalSet firstThaat = SymmetricalSet.THAAT_KAFI;

	private final JFrame swingframe;

	public UiMainFrame() {
		this.swingframe = new JFrame(frameTitle);
		swingframe.setLocationRelativeTo(null);
	}

	JFrame initialize() {
		JPanel swingMainPanel = new UiTabbedPanel();
		swingframe.add(swingMainPanel, BorderLayout.CENTER);
		// frame.setUndecorated( true );
		// AWTUtilities.setWindowOpacity(frame, 0.7f);
		swingframe.pack();
		swingframe.setVisible(true);

		swingframe.getContentPane().setPreferredSize(preferredSizeForMainPane);
		swingframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return swingframe;
	}
	
	static class UiTabbedPanel extends JPanel {
		public UiTabbedPanel() {
			super(new GridLayout(1, 1));
			add(tabbedPane());
		}
		private JTabbedPane tabbedPane() {
			JTabbedPane tabbedPane = new JTabbedPane();
			UiTabs uiTabs = new UiTabs(tabbedPane);
			uiTabs.addPlaneThaat(new RehearsePanel(PlayApi.getAllPlainThaat()).getPanel());
			uiTabs.addSong(new RehearsePanel(PlayApi.getAllSongs()).getPanel());
			uiTabs.addPatternedTab(firstThaat, false);
			uiTabs.addQuiz(firstQuizLevel, false);
			tabbedPane.setOpaque(true);
			return tabbedPane;
		}
	}
}