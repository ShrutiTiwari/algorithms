package com.aqua.music.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	private final JPanel commonComponentPanel;
	private volatile boolean initialized = false;
	private final JPanel mainPanel;
	private final ConfigurationPanel configurationPanel;
	private JPanel specificComponentPanel;

	protected MusicPanel(boolean extraPanel) {
		// main panel
		this.mainPanel = new JPanel();
		BoxLayout b = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(b);

		this.configurationPanel = new ConfigurationPanel();
		this.commonComponentPanel = configurationPanel.getPanel();
		this.pauseButton = configurationPanel.pauseButton;

		mainPanel.add(commonComponentPanel, BorderLayout.WEST);
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
		private final JPanel extraComponents = new JPanel();;
		private ConfigurationPanel() {
			this.configurationPanel = new JPanel();
			BoxLayout b = new BoxLayout(configurationPanel, BoxLayout.PAGE_AXIS);
			configurationPanel.setLayout(b);

			JPanel commonPanel = new JPanel();
			addToPanel(MusicButtons.INCREASE_TEMPO.createStaticNamedButton(), commonPanel);
			addToPanel(MusicButtons.DECREASE_TEMPO.createStaticNamedButton(), commonPanel);
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			addToPanel(pauseButton, commonPanel);
			addToPanel(UiDropdown.instrumentDropDown(null), commonPanel);
			
			addToPanel(commonPanel, configurationPanel);
		}

		/**
		 * @param aComponent
		 */
		public void add(JComponent aComponent) {
			extraComponents.add(aComponent);
			addToPanel(extraComponents, configurationPanel);
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
