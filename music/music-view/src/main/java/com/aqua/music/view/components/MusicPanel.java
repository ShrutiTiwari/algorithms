package com.aqua.music.view.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqua.music.view.components.UiTexts.UiLables;

/**
 * Abstract class for music panels
 * 
 * @author "Shruti Tiwari"
 * 
 */
abstract class MusicPanel {
	protected final Logger logger = LoggerFactory.getLogger(MusicPanel.class);

	private volatile boolean initialized = false;
	private final JPanel mainPanel;
	private JPanel refreshablePanel;

	private final JPanel leftPanel1 = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
	private final JPanel leftPanel2 = UiJPanelBuilder.BOX_HORIZONTAL.createPanel();
	private final JPanel leftPanel3 = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();
	private final JPanel leftPanelalert = UiJPanelBuilder.LEFT_FLOWLAYOUT.createPanel();

	private JPanel middlePanel;
	private JLabel customizationLabel;

	private JLabel alertLabel;

	protected MusicPanel(MusicPracticePanelType practicePanel) {
		this.mainPanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		this.middlePanel = UiJPanelBuilder.BOX_VERTICAL.createPanel();
		mainPanel.add(leftPanel1);
		if (practicePanel!=null && practicePanel.customizationAllowed()) {
			mainPanel.add(Box.createVerticalStrut(10));
			mainPanel.add(leftPanel2);
			mainPanel.add(leftPanelalert);
			mainPanel.add(leftPanel3);
			this.alertLabel = UiLables.CURRENT_CUSTOMIZATION.newLabel();
			alertLabel.setForeground(Color.RED);
			alertLabel.setVisible(false);
			this.customizationLabel = UiLables.CURRENT_CUSTOMIZATION.newLabel();
			leftPanelalert.add(alertLabel);
			leftPanel3.add(customizationLabel);
			
		}
		mainPanel.add(middlePanel);
	}
	
	protected JLabel alertLabel(){
		return alertLabel;
	}
	
	protected JLabel customizationLabel(){
		return customizationLabel;
	}

	public void addExtraTopControl(Component aComponent) {
		leftPanel1.add(aComponent);
		leftPanel1.add(Box.createHorizontalGlue());
	}

	public void addExtraTopControl2(Component aComponent) {
		leftPanel2.add(aComponent);
		leftPanel2.add(Box.createHorizontalGlue());
	}

	public JPanel getPanel() {
		if (!initialized) {
			configureMiddlePanel();
		}
		return mainPanel;
	}

	public void refreshSpecificComponentPanel(final Object selectedConfiguration) {
		middlePanel.remove(refreshablePanel);
		addMiddlePanel(selectedConfiguration);
		middlePanel.revalidate();
	}

	public abstract void repaint();

	protected abstract JPanel createMiddlePanel(final Object selectedObject);

	private void addMiddlePanel(final Object selectedConfiguration) {
		this.refreshablePanel = createMiddlePanel(selectedConfiguration);
		middlePanel.add(refreshablePanel);
	}

	private synchronized void configureMiddlePanel() {
		if (!initialized) {
			addMiddlePanel(null);
			mainPanel.setOpaque(true);
			initialized = true;
		}
	}
}
