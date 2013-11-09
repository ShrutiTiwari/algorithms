package com.aqua.music.ui.swing;
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
					setContentPane(new UiTabbedPanel());
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI failed");
		}
	}
}
