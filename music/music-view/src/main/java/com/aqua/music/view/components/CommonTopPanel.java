/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.view.components.UiButtons.MusicButtons;

public class CommonTopPanel {
	private final JButton pauseButton;
	private final JPanel leftPanel = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
	private JPanel rightPanel;
	private final JPanel mainPanel = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();

	public JButton pauseButton() {
		return pauseButton;
	}

	public CommonTopPanel() {
		this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
		this.rightPanel = UiJPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();

		addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), rightPanel);
		addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), rightPanel);
		addToPanel(pauseButton, rightPanel);

		mainPanel.add(leftPanel);
		
		leftPanel.add(new JLabel(" Choose one of the practice tabs for practice "));
		
		mainPanel.add(rightPanel);
	}

	private static void addToPanel(JComponent each, JPanel containerPanel) {
		each.setForeground(Color.BLUE);
		containerPanel.add(each);
		each.setAlignmentX(Component.RIGHT_ALIGNMENT);
		containerPanel.add(Box.createVerticalGlue());
	}

	public JPanel getPanel() {
		return mainPanel;
	}
}