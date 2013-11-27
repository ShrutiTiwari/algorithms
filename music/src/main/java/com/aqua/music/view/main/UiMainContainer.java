package com.aqua.music.view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author "Shruti Tiwari"
 *
 * @param <T>
 */
public interface UiMainContainer<T> {
	UiMainContainer addPanel(UIMainPanel<T> uiMainPanel);

	void display();

	public UiMainContainer SWING = new SwingBasedUiMainContainer();

	class SwingBasedUiMainContainer implements UiMainContainer<JPanel> {
		private static final String frameTitle = "Music";
		private static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
		private final JFrame swingframe;

		public SwingBasedUiMainContainer() {
			this.swingframe = new JFrame(frameTitle);
			swingframe.setLocationRelativeTo(null);
		}

		@Override
		public UiMainContainer addPanel(UIMainPanel<JPanel> uiMainPanel) {
			swingframe.add(uiMainPanel.getJPanel(), BorderLayout.CENTER);
			return this;
		}

		@Override
		public void display() {
			// frame.setUndecorated( true );
			// AWTUtilities.setWindowOpacity(frame, 0.7f);
			swingframe.pack();
			swingframe.setVisible(true);

			swingframe.getContentPane().setPreferredSize(preferredSizeForMainPane);
			//swingframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
	}
}
