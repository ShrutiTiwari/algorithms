package com.aqua.music.view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class UiLauncher {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingBasedUi swingBasedUi = new SwingBasedUi();
				swingBasedUi.addPanel(new UIMainPanel());
				swingBasedUi.display();
			}
		});
	}

	static class SwingBasedUi {
		private static final String frameTitle = "Music";
		private static final Dimension preferredSizeForMainPane = new Dimension(450, 450);
		private final JFrame swingframe;

		public SwingBasedUi() {
			this.swingframe = new JFrame(frameTitle);
			swingframe.setLocationRelativeTo(null);
			swingframe.addWindowListener(new AppCloseWindowsListener());
		}

		public SwingBasedUi addPanel(UIMainPanel uiMainPanel) {
			swingframe.add(uiMainPanel.getJPanel(), BorderLayout.CENTER);
			return this;
		}

		public void display() {
			swingframe.pack();
			swingframe.setVisible(true);
			swingframe.setLocationRelativeTo(null);
			swingframe.getContentPane().setPreferredSize(preferredSizeForMainPane);
		}
	}
}