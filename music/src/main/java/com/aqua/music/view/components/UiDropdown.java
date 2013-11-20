package com.aqua.music.view.components;

import java.awt.Color;

import javax.swing.JComboBox;

public class UiDropdown {
	static JComboBox createWith(Object[] objects, int buttonYcoordinate, Object selectedItem){
		final JComboBox box = new JComboBox(objects);
		box.setBackground(Color.RED);
		box.setForeground(Color.GREEN);
		box.setBounds(UiButtons.X_COORIDNATE, buttonYcoordinate, UiButtons.LARGE_BUTTON_WIDTH, UiButtons.BUTTON_HEIGHT);
		return box;
	}
}
