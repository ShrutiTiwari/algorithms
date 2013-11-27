package com.aqua.music.view.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiButtons.MusicButtons;

/**
 * @author "Shruti Tiwari"
 * 
 */
abstract class AbstractMusicPanel {
	private static final Dimension preferredSizeForThaatPanel = new Dimension(400, 400);
	protected final Logger logger = LoggerFactory.getLogger(AbstractMusicPanel.class);

	private final JPanel commonComponentPanel;
	private final CommonComponents commonComponents;
	private volatile boolean initialized = false;
	private final JPanel mainPanel;
	private JPanel specificComponentPanel;	

	protected AbstractMusicPanel() {
		this.mainPanel = createBlankMainTab();
		this.commonComponentPanel = new JPanel();
		//commonComponentPanel.setLayout(new BorderLayout());
		this.commonComponents = new CommonComponents();
		for (JComponent each : commonComponents.getAllComponents()) {
			commonComponentPanel.add(each);
		}
		mainPanel.add(commonComponentPanel);
	}

	public void addToCommonComponentPanel(JComponent aComponent) {
		commonComponentPanel.add(aComponent);
	}
	
	public JPanel getPanel() {
		if (!initialized) {
			addSpecificComponentPanel();
		}
		return mainPanel;
	}

	public JButton pauseButton() {
		return commonComponents.pauseButton;
	}

	public void renewSpecificComponentPanel(final Object selectedObject) {
		mainPanel.remove(specificComponentPanel);
		this.specificComponentPanel = createSpecificComponentPanel(selectedObject);
		mainPanel.add(specificComponentPanel);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	protected abstract JPanel createSpecificComponentPanel(final Object selectedObject);

	private synchronized void addSpecificComponentPanel() {
		if (!initialized) {
			this.specificComponentPanel = createSpecificComponentPanel(null);
			mainPanel.add(specificComponentPanel);
			mainPanel.setOpaque(true);
			initialized = true;
		}
	}

	private JPanel createBlankMainTab() {
		JPanel mainTab = new JPanel();
		mainTab.setLayout(new GridLayout(2,1));
		mainTab.setPreferredSize(preferredSizeForThaatPanel);
		return mainTab;
	}

	private static class CommonComponents {
		private final JButton pauseButton;
		private final Collection<JComponent> result = new ArrayList<JComponent>();

		private CommonComponents() {
			JComponent dropDown = UiDropdown.instrumentDropDown(null);
			result.add(dropDown);
			this.pauseButton = MusicButtons.PAUSE.createStaticNamedButton();
			result.add(pauseButton);
			result.add(MusicButtons.INCREASE_TEMPO.createStaticNamedButton());
			result.add(MusicButtons.DECREASE_TEMPO.createStaticNamedButton());
			result.add(MusicButtons.QUIT.createStaticNamedButton());
		}

		private Collection<JComponent> getAllComponents() {
			return result;
		}
	}
}
