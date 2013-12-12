package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import open.music.api.InstrumentRole;

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
	private JPanel refreshablePanel;

	private JPanel middlePanel;

	protected MusicPanel(boolean extraPanel) {
		this.mainPanel = MusicPanelBuilder.BOX_VERTICAL.createPanel();
		this.topPanelBuilder = new TopPanelBuilder();
		this.pauseButton = topPanelBuilder.pauseButton;
		mainPanel.add(topPanelBuilder.getPanel());
		mainPanel.add(Box.createVerticalGlue());
		this.middlePanel = MusicPanelBuilder.BOX_VERTICAL.createPanel();
		mainPanel.add(middlePanel);
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
		middlePanel.remove(refreshablePanel);
		addMiddlePanel(selectedObject);
		middlePanel.revalidate();
	}

	public abstract void repaint();

	protected abstract JPanel createMiddlePanel(final Object selectedObject);

	private void addMiddlePanel(final Object selectedObject) {
		this.refreshablePanel = createMiddlePanel(selectedObject);
		middlePanel.add(refreshablePanel);
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
		private final JPanel topMostPanel = MusicPanelBuilder.BOX_HORIZONTAL.createPanel();
		private final JPanel leftPanel = MusicPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
		private JPanel rightPanel;

		private TopPanelBuilder() {
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			this.rightPanel = MusicPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();

			addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), rightPanel);
			addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), rightPanel);
			addToPanel(pauseButton, rightPanel);

			topMostPanel.add(leftPanel);
			topMostPanel.add(rightPanel);
		}

		public void add(JComponent aComponent) {
			leftPanel.add(aComponent);
		}

		JPanel getPanel() {
			return topMostPanel;
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
			this.bottomPanel = MusicPanelBuilder.BOX_VERTICAL.createPanel();

			addInstrument(InstrumentRole.MAIN);
			// addInstrument(InstrumentRole.RHYTHM);

			JPanel quitPanel = MusicPanelBuilder.RIGHT_FLOWLAYOUT.createPanel();
			JButton quitButton = MusicButtons.QUIT.createStaticNamedButton();
			quitPanel.add(quitButton);
			bottomPanel.add(quitPanel);
		}

		private void addInstrument(InstrumentRole instrumentRole) {
			InstrumentDisplay instrumentDisplay = new InstrumentDisplay(instrumentRole);
			bottomPanel.add(instrumentDisplay.instrumentLabel());
			bottomPanel.add(instrumentDisplay.displayPane());
			bottomPanel.add(Box.createVerticalGlue());
		}
	}
}
