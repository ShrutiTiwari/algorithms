package com.aqua.music.view.main;

import java.awt.TextArea;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.aqua.music.view.components.CommonBottomPanel;
import com.aqua.music.view.components.CommonPanelComponents;
import com.aqua.music.view.components.CommonTopPanel;
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
		
		final CommonTopPanel commonTopPanel = new CommonTopPanel();
		JPanel topPanel = commonTopPanel.getPanel();

		JPanel middlePanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
		final TextArea consoleArea = createConsoleArea();
		JTabbedPane tabbedPane = UiTabbedPane.getTabbedPane(new CommonPanelComponents(commonTopPanel.pauseButton(), consoleArea));
		middlePanel.add(tabbedPane);
		middlePanel.add(consoleArea);

		JPanel bottomPanel = new CommonBottomPanel().panel();

		jPanelInstance.add(topPanel);
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
	private TextArea createConsoleArea() {
		TextArea textArea = new TextArea("Played notes will be displayed here in indian scale....");
		textArea.setEditable(false);
		textArea.setVisible(true);
		return textArea;
	}
}
