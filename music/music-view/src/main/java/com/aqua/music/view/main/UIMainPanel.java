package com.aqua.music.view.main;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import open.music.api.SingletonFactory;

import com.aqua.music.view.components.CommonUi;
import com.aqua.music.view.components.UiColor;
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
		jPanelInstance.setBackground(UiColor.BG_CLR);
		JPanel middlePanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();

		CommonUi<T> commonComponents=new CommonUi<T>();
		
		JTabbedPane tabbedPane = UiTabbedPane.getTabbedPane(commonComponents.stateDependentUi());
		middlePanel.add(tabbedPane);
		middlePanel.add(commonComponents.consoleArea());

		jPanelInstance.add(commonComponents.topPanel());
		jPanelInstance.add(middlePanel);
		jPanelInstance.add(Box.createVerticalGlue());
		jPanelInstance.add(commonComponents.bottomPanel());
		SingletonFactory.PLAY_API.initializeStateDepenendentUi(commonComponents.stateDependentUi());
	}

	T getJPanel() {
		try {
			return (T) jPanelInstance;
		} catch (Exception e) {
			return null;
		}
	}
}
