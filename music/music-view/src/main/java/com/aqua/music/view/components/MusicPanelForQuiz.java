package com.aqua.music.view.components;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.aqua.music.model.cyclicset.CyclicFrequencySet;
import com.aqua.music.model.puzzles.QuizLevel;
import com.aqua.music.model.puzzles.QuizLevel.Quiz;

/**
 * @author "Shruti Tiwari"
 * 
 */
class MusicPanelForQuiz extends MusicPanel {
	private final QuizLevel initialQuizLevel;
	private JPanel resultPanel;

	MusicPanelForQuiz(final QuizLevel initialQuizLevel) {
		super(true);
		this.initialQuizLevel = initialQuizLevel;
		final JComboBox quizDropdown = UiDropdown.quizDropdown(initialQuizLevel);
		quizDropdown.addActionListener(new UiDropdown.QuizDropdownActionListener(this));
		addExtraTopControl(quizDropdown);
	}

	protected JPanel createMiddlePanel(final Object selectedObject) {
		resultPanel = MusicPanelBuilder.BOX_HORIZONTAL.createPanel();
		resultPanel.add(Box.createVerticalStrut(100));

		QuizLevel quizLevel = (QuizLevel) selectedObject;
		if (quizLevel == null) {
			quizLevel = this.initialQuizLevel;
		}
		int quizIndex = 1;

		Collection<Quiz<CyclicFrequencySet>> quizSections = (Collection<Quiz<CyclicFrequencySet>>) quizLevel.quizSections();
		JPanel[] subPanels = new JPanel[] { MusicPanelBuilder.BOX_VERTICAL.createPanel() };

		if (quizSections.size() > 5) {
			subPanels = new JPanel[] { MusicPanelBuilder.BOX_VERTICAL.createPanel(), MusicPanelBuilder.BOX_VERTICAL.createPanel() };
		}

		int panel = 0;
		for (Quiz<CyclicFrequencySet> eachQuizSection : quizSections) {
			JPanel quizSectionPanel = MusicPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
			final String quizName = "Quiz " + quizIndex;
			JButton quizPlayButton = UiButtons.MusicButtons.QUIZ_PLAY.createDynamicNamedButton(quizName);
			quizSectionPanel.add(quizPlayButton);

			final Collection<JButton> multipleChoiceButtons = new ArrayList<JButton>();
			for (CyclicFrequencySet eachOption : eachQuizSection.quizItems()) {
				JButton b = UiButtons.MusicButtons.CHOICE_OPTIONS.createDynamicNamedButton(eachOption.name());
				multipleChoiceButtons.add(b);
				quizSectionPanel.add(b);
			}
			quizPlayButton.addActionListener(new QuizPlayActionListener(this, eachQuizSection, multipleChoiceButtons));
			quizIndex++;
			subPanels[panel++].add(quizSectionPanel);
			panel = (panel > subPanels.length-1 ? 0 : panel);
		}

		for (JPanel each : subPanels) {
			resultPanel.add(each);
		}

		return resultPanel;
	}

	public void repaint() {
		resultPanel.repaint();
	}
}
