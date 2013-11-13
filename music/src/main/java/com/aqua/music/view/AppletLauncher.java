package com.aqua.music.view;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppletLauncher extends JApplet {
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					JFrame jframe = new UiLauncher().createAndShowUi();
					setContentPane(new UiLauncher.UiTabbedPanel());
					jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI failed");
		}
	}
}
