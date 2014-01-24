package com.aqua.music.view.main;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.view.components.CommonUi;
import com.aqua.music.view.components.UiColor;
import com.aqua.music.view.components.UiJPanelBuilder;
import com.aqua.music.view.components.UiTabbedPane;

/**
 * Helper class for  {@link UiLauncher} and {@link AppletLauncher}
 * 
 * @author "Shruti Tiwari"
 * 
 * 
 */
public class UIMainPanel {
	private final JPanel jPanelInstance;

	UIMainPanel() {
		this.jPanelInstance = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		jPanelInstance.setBackground(UiColor.BG_CLR);
		JPanel middlePanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();

		CommonUi commonComponents = new CommonUi();

		JTabbedPane tabbedPane = UiTabbedPane.getTabbedPane(commonComponents.stateDependentUi());
		middlePanel.add(tabbedPane);
		middlePanel.add(commonComponents.consoleArea());

		jPanelInstance.add(commonComponents.topPanel());
		jPanelInstance.add(middlePanel);
		jPanelInstance.add(Box.createVerticalGlue());
		jPanelInstance.add(commonComponents.bottomPanel());
	}

	JPanel getJPanel() {
		return jPanelInstance;
	}
}
