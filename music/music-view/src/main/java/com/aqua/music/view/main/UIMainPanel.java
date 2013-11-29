package com.aqua.music.view.main;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.aqua.music.view.components.UiTabbedPane;

/**
 * @author "Shruti Tiwari"
 * 
 * @param <T>
 */
public class UIMainPanel<T> {
	private final JPanel jPanelInstance;

	UIMainPanel() {
		this.jPanelInstance = new JPanel(new GridLayout(1, 1));
		jPanelInstance.add(UiTabbedPane.getTabbedPane());
	}

	T getJPanel() {
		try {
			return (T) jPanelInstance;
		} catch (Exception e) {
			return null;
		}
	}

}
