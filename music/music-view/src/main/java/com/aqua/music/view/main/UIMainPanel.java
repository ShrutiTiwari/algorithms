package com.aqua.music.view.main;

import javax.swing.Box;
import javax.swing.JPanel;

import com.aqua.music.view.components.BottomPanelBuilder;
import com.aqua.music.view.components.TopPanelBuilder;
import com.aqua.music.view.components.UiJPanelBuilder;
import com.aqua.music.view.components.UiTabbedPane;

/**
 * @author "Shruti Tiwari"
 * 
 * @param <T>
 */
public class UIMainPanel<T> {
	private final JPanel jPanelInstance;

	UIMainPanel() {
		this.jPanelInstance = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		
		final TopPanelBuilder topPanelBuilder = new TopPanelBuilder();
		jPanelInstance.add(topPanelBuilder.getPanel());
		jPanelInstance.add(UiTabbedPane.getTabbedPane(topPanelBuilder));
		jPanelInstance.add(Box.createVerticalGlue());
		jPanelInstance.add(new BottomPanelBuilder().panel());
	}

	T getJPanel() {
		try {
			return (T) jPanelInstance;
		} catch (Exception e) {
			return null;
		}
	}

}
