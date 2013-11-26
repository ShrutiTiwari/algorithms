package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.api.AudioPlayerSettings;
import com.aqua.music.api.PlayApi;
import com.aqua.music.model.cyclicset.SymmetricalSet;
import com.aqua.music.model.puzzles.QuizController;
import com.aqua.music.model.puzzles.QuizLevel;

public class UiDropdown {
	public static JComponent instrumentDropDown(int xCooridnate, int yCoordinate, Object selectedItem) {
		JComboBox instrumentDropdown = createWith(PlayApi.getAllInstruments(), xCooridnate, yCoordinate, selectedItem,(3*UiButtons.DEFAULT_BUTTON_WIDTH));
		instrumentDropdown.addActionListener(new InstrumentDropdownActionListener());
		return instrumentDropdown;
	}

	static JComboBox quizDropdown(int buttonYcoordinate, Object selectedItem) {
		Collection<QuizLevel> quizLevels = QuizController.FrequencySetQuiz.quizLevels();
		return createWith(quizLevels.toArray(), UiButtons.X_COORIDNATE, buttonYcoordinate, selectedItem,UiButtons.DEFAULT_BUTTON_WIDTH);
	}

	static JComboBox thaatDropDown(int buttonYcoordinate, Object selectedItem) {
		return createWith(SymmetricalSet.values(), UiButtons.X_COORIDNATE, buttonYcoordinate, selectedItem,UiButtons.DEFAULT_BUTTON_WIDTH);
	}

	private static JComboBox createWith(Object[] objects, int xCooridnate, int buttonYcoordinate, Object selectedItem, int dropdownWidth) {
		final JComboBox box = new JComboBox(objects);
		box.setBackground(Color.WHITE);
		box.setForeground(Color.GRAY);
		box.setBounds(xCooridnate, buttonYcoordinate, dropdownWidth, UiButtons.BUTTON_HEIGHT);
		if (selectedItem != null) {
			box.setSelectedItem(selectedItem);
		} else {
			box.setSelectedItem(objects[0]);
		}
		return box;
	}

	static class InstrumentDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(InstrumentDropdownActionListener.class);

		InstrumentDropdownActionListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cbox = (JComboBox) arg0.getSource();
			Object obj = cbox.getSelectedItem();
			AudioPlayerSettings.changeInstrumentTo(obj);
		}
	}
	
	static class PaneReloadDropdownActionListener implements ActionListener {
		Logger logger = LoggerFactory.getLogger(PaneReloadDropdownActionListener.class);
		private final ReloadablePanelContainer reloadablePanelContainer;
		private UiTabbedPane uiTabbedPane;

		PaneReloadDropdownActionListener(UiTabbedPane uiTabbedPane, ReloadablePanelContainer reloadablePanelContainer) {
			this.reloadablePanelContainer = reloadablePanelContainer;
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
