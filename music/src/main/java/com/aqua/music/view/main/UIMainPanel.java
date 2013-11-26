package com.aqua.music.view.main;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.view.components.UiTabbedPane;

/**
 * @author "Shruti Tiwari"
 *
 * @param <T>
 */
public class UIMainPanel<T> {
	private final SwingBasedUiMainPanel jPanelInstance;
	private static JTabbedPane tabbedPane;
	
	UIMainPanel() {
		this.jPanelInstance = new SwingBasedUiMainPanel();
		this.tabbedPane = UiTabbedPane.getTabbedPane();
		jPanelInstance.add(tabbedPane);
	}

	public static void repaintSelectedTab(){
		tabbedPane.getSelectedComponent().repaint();
	}
	
	T getJPanel() {
		try {
			return (T) jPanelInstance;
		} catch (Exception e) {
			return null;
		}
	}

	private class SwingBasedUiMainPanel extends JPanel {
		private SwingBasedUiMainPanel() {
			super(new GridLayout(1, 1));
		}
	}
}
