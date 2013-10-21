package com.aqua.music.ui;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LauncherApplet extends JApplet {
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					JFrame jframe = new SwingGuiLauncher().createAndShowGUI();
					setContentPane(new GuiMultitabPanel());
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI failed");
		}
	}
}
