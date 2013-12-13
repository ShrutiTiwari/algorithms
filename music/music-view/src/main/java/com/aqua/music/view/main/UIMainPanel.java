package com.aqua.music.view.main;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.view.components.CommonBottomPanel;
import com.aqua.music.view.components.StateDependentUiImpl;
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
		JPanel middlePanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
		StateDependentUiImpl stateDependentUi = new StateDependentUiImpl();
		JTabbedPane tabbedPane = UiTabbedPane.getTabbedPane(stateDependentUi);
		middlePanel.add(tabbedPane);
		middlePanel.add(stateDependentUi.consoleArea());

		JPanel bottomPanel = new CommonBottomPanel(stateDependentUi).panel();

		jPanelInstance.add(stateDependentUi.topPanel());
		jPanelInstance.add(middlePanel);
		jPanelInstance.add(Box.createVerticalGlue());
		jPanelInstance.add(bottomPanel);
	}

	T getJPanel() {
		try {
			return (T) jPanelInstance;
		} catch (Exception e) {
			return null;
		}
	}
}
