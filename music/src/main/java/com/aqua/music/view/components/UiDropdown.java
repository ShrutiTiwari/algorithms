package com.aqua.music.view.components;

import java.awt.Color;
import java.util.Collection;

import javax.swing.JComboBox;

import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiDropdown {
	static JComboBox quizDropdown(int buttonYcoordinate, Object selectedItem){
		Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
		return createWith(quizLevels.toArray(), buttonYcoordinate, selectedItem);
	}
	
	static JComboBox thaatDropDown(int buttonYcoordinate, Object selectedItem){
		return createWith(SymmetricalSet.values(), buttonYcoordinate, selectedItem);		
	}
	
	private static JComboBox createWith(Object[] objects, int buttonYcoordinate, Object selectedItem) {
		final JComboBox box = new JComboBox(objects);
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate, UiButtons.LARGE_BUTTON_WIDTH, UiButtons.BUTTON_HEIGHT);
		box.setSelectedItem(selectedItem);
		return box;
	}
}
	
