package com.aqua.music.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

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
	private final ConfigurationPanel configurationPanel;
	private JPanel specificComponentPanel;

	protected MusicPanel(boolean extraPanel) {
		this.mainPanel = MusicPanels.BOX_VERTICAL.createPanel();
		this.configurationPanel = new ConfigurationPanel();
		this.pauseButton = configurationPanel.pauseButton;
		mainPanel.add(configurationPanel.getPanel());
		mainPanel.add(Box.createVerticalGlue());
	}

	public void addExtraComponents(JComponent aComponent) {
		configurationPanel.add(aComponent);
	}

	public JPanel getPanel() {
		if (!initialized) {
			configureSpecificComponentPanel();
		}
		return mainPanel;
	}

	public JButton pauseButton() {
		return pauseButton;
	}

	public void refreshSpecificComponentPanel(final Object selectedObject) {
		mainPanel.remove(specificComponentPanel);
		addSpecificComponentPanel(selectedObject);
		mainPanel.revalidate();
	}

	public abstract void repaint();

	protected abstract JPanel createSpecificComponentPanel(final Object selectedObject);

	private void addSpecificComponentPanel(final Object selectedObject) {
		this.specificComponentPanel = createSpecificComponentPanel(selectedObject);
		specificComponentPanel.add(MusicButtons.QUIT.createStaticNamedButton());
		mainPanel.add(specificComponentPanel, BorderLayout.CENTER);
	}

	private synchronized void configureSpecificComponentPanel() {
		if (!initialized) {
			addSpecificComponentPanel(null);
			mainPanel.setOpaque(true);
			initialized = true;
		}
	}

	private static class ConfigurationPanel {
		private final JButton pauseButton;
		private final JPanel configurationPanel;
		private final JPanel extraComponents = MusicPanels.BOX_HORIZONTAL.createPanel();

		private ConfigurationPanel() {
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			this.configurationPanel = MusicPanels.BOX_VERTICAL.createPanel();

			JPanel configurationButtonsPanel = MusicPanels.BOX_HORIZONTAL.createPanel();
			configurationButtonsPanel.add(extraComponents);
			addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), configurationButtonsPanel);
			addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), configurationButtonsPanel);
			addToPanel(pauseButton, configurationButtonsPanel);

			configurationPanel.add(configurationButtonsPanel);
			configurationPanel.add(Box.createVerticalGlue());
			
			JPanel instrumentPanel = MusicPanels.BOX_HORIZONTAL.createPanel();
			JLabel instrumentsTitle = new JLabel("Instruments::");
			instrumentPanel.add(instrumentsTitle);
			instrumentPanel.add(UiScrollPane.instrumentDisplay(null));
			configurationPanel.add(instrumentPanel);
		}

		/**
		 * @param aComponent
		 */
		public void add(JComponent aComponent) {
			extraComponents.add(aComponent);
		}

		private static void addToPanel(JComponent each, JPanel containerPanel) {
			each.setForeground(Color.BLUE);
			containerPanel.add(each);
			each.setAlignmentX(Component.RIGHT_ALIGNMENT);
			containerPanel.add(Box.createVerticalGlue());
		}

		private JPanel getPanel() {
			return configurationPanel;
		}
	}
}
