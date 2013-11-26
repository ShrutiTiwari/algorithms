package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		box.setBackground(Color.WHITE);
		box.setForeground(Color.GRAY);
		box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate, UiButtons.DEFAULT_BUTTON_WIDTH, UiButtons.BUTTON_HEIGHT);
		box.setSelectedItem(selectedItem);
		return box;
	}
	
	static class DropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(DropdownActionListener.class);
		private UiTabbedPane uiTabbedPane;
		private final ReloadablePanelContainer reloadablePanelContainer;

		DropdownActionListener(UiTabbedPane uiTabbedPane, ReloadablePanelContainer reloadablePanelContainer) {
			this.reloadablePanelContainer=reloadablePanelContainer;
			this.uiTabbedPane = uiTabbedPane;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
				Entry<String, JPanel> newPanelWith = reloadablePanelContainer.newPanelWith(obj);
				uiTabbedPane.refreshReloadablePanel(newPanelWith.getValue(), newPanelWith.getKey());
		}
	}
}	
