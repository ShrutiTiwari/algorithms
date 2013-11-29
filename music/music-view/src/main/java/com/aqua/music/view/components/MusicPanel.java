package com.aqua.music.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

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

	private final JPanel commonComponentPanel;
	private final CommonComponents commonComponents;
	private JPanel extraComponentPanel = null;
	private volatile boolean initialized = false;
	private final JPanel mainPanel;

	private JPanel specificComponentPanel;

	protected MusicPanel(boolean extraPanel) {
		this.mainPanel = new JPanel(new BorderLayout());
		this.commonComponentPanel = new JPanel();
		this.commonComponents = new CommonComponents();
		configureCommonComponentsPanel();
		configureExtraPanel(extraPanel);
	}

	public void addToExtraComponentPanel(JComponent aComponent) {
		extraComponentPanel.add(aComponent);
		extraComponentPanel.add(Box.createVerticalGlue());
	}

	public JPanel getPanel() {
		if (!initialized) {
			configureSpecificComponentPanel();
		}
		return mainPanel;
	}

	public JButton pauseButton() {
		return commonComponents.pauseButton;
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
		mainPanel.add(specificComponentPanel, BorderLayout.CENTER);
	}

	private void configureCommonComponentsPanel() {
		BoxLayout b = new BoxLayout(commonComponentPanel, BoxLayout.PAGE_AXIS);
		commonComponentPanel.setLayout(b);
		for (JComponent each : commonComponents.getAllComponents()) {
			each.setForeground(Color.BLUE);
			commonComponentPanel.add(each);
			each.setAlignmentX(Component.RIGHT_ALIGNMENT);
			each.setMinimumSize(new Dimension(200, 200));
			commonComponentPanel.add(Box.createVerticalGlue());
		}
		mainPanel.add(commonComponentPanel, BorderLayout.EAST);
	}

	private void configureExtraPanel(boolean extraPanel) {
		if (extraPanel) {
			this.extraComponentPanel = new JPanel();
			BoxLayout b1 = new BoxLayout(extraComponentPanel, BoxLayout.PAGE_AXIS);
			extraComponentPanel.setLayout(b1);
			mainPanel.add(extraComponentPanel, BorderLayout.WEST);
		}
	}

	private synchronized void configureSpecificComponentPanel() {
		if (!initialized) {
			addSpecificComponentPanel(null);
			mainPanel.setOpaque(true);
			initialized = true;
		}
	}

	private static class CommonComponents {
		private final JButton pauseButton;
		private final Collection<JComponent> result = new ArrayList<JComponent>();

		private CommonComponents() {
			JComponent dropDown = UiDropdown.instrumentDropDown(null);
			result.add(dropDown);
			result.add(MusicButtons.INCREASE_TEMPO.createStaticNamedButton());
			result.add(MusicButtons.DECREASE_TEMPO.createStaticNamedButton());

			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			result.add(pauseButton);
			JButton quitButton = MusicButtons.QUIT.createStaticNamedButton();
			result.add(quitButton);
		}

		private Collection<JComponent> getAllComponents() {
			return result;
		}
	}
}