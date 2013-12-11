package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiButtons.MusicButtons;

/**
 * Abstract class for music panels
 * 
 * @author "Shruti Tiwari"
 * 
 */
public abstract class MusicPanel {
	protected final Logger logger = LoggerFactory.getLogger(MusicPanel.class);

	private final JButton pauseButton;
	private volatile boolean initialized = false;
	private final JPanel mainPanel;
	private final TopPanelBuilder topPanelBuilder;
	private JPanel middlePanel;

	protected MusicPanel(boolean extraPanel) {
		this.mainPanel = MusicPanels.BOX_VERTICAL.createPanel();
		this.topPanelBuilder = new TopPanelBuilder();
		this.pauseButton = topPanelBuilder.pauseButton;
		mainPanel.add(topPanelBuilder.topPanel);
		mainPanel.add(Box.createVerticalGlue());
	}

	public void addExtraTopControl(JComponent aComponent) {
		topPanelBuilder.add(aComponent);
	}

	public JPanel getPanel() {
		if (!initialized) {
			configureMiddlePanel();
		}
		return mainPanel;
	}

	public JButton pauseButton() {
		return pauseButton;
	}

	public void refreshSpecificComponentPanel(final Object selectedObject) {
		mainPanel.remove(middlePanel);
		addMiddlePanel(selectedObject);
		mainPanel.revalidate();
	}

	public abstract void repaint();

	protected abstract JPanel createMiddlePanel(final Object selectedObject);

	private void addMiddlePanel(final Object selectedObject) {
		this.middlePanel = createMiddlePanel(selectedObject);
		mainPanel.add(middlePanel);
	}

	private synchronized void configureMiddlePanel() {
		if (!initialized) {
			addMiddlePanel(null);
			mainPanel.add(Box.createVerticalGlue());
			mainPanel.add(new BottomPanelBuilder().bottomPanel);
			mainPanel.setOpaque(true);
			initialized = true;
		}
	}

	private static class TopPanelBuilder {
		private final JButton pauseButton;
		private final JPanel topPanel;
		private final JPanel extraComponents = MusicPanels.RIGHT_FLOWLAYOUT.createPanel();

		private TopPanelBuilder() {
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			this.topPanel = MusicPanels.RIGHT_FLOWLAYOUT.createPanel();
			topPanel.add(extraComponents);
			addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), topPanel);
			addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), topPanel);
			addToPanel(pauseButton, topPanel);
		}

		public void add(JComponent aComponent) {
			extraComponents.add(aComponent);
		}

		private static void addToPanel(JComponent each, JPanel containerPanel) {
			each.setForeground(Color.BLUE);
			containerPanel.add(each);
			each.setAlignmentX(Component.RIGHT_ALIGNMENT);
			containerPanel.add(Box.createVerticalGlue());
		}
	}

	private static class BottomPanelBuilder {
		private final JPanel bottomPanel;

		private BottomPanelBuilder() {
			this.bottomPanel = MusicPanels.BOX_VERTICAL.createPanel();

			JPanel instrumentLabelPanel = MusicPanels.LEFT_FLOWLAYOUT.createPanel();
			instrumentLabelPanel.add(new JLabel("Instruments::"));
			instrumentLabelPanel.setSize(new Dimension(10,40));
			
			JComponent scrollPane = UiScrollPane.instrumentDisplay(null);

			JPanel quitPanel = MusicPanels.RIGHT_FLOWLAYOUT.createPanel();
			JButton quitButton = MusicButtons.QUIT.createStaticNamedButton();
			quitPanel.add(quitButton);
			
			bottomPanel.add(instrumentLabelPanel);
			bottomPanel.add(scrollPane);
			bottomPanel.add(Box.createVerticalGlue());
			bottomPanel.add(quitPanel);
			
		}
	}
}
