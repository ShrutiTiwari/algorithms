package com.aqua.music.view.main;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * @author "Shruti Tiwari"
 *
 */
public class AppletLauncher extends JApplet {
	public void init() {
		try {
			add(new UIMainPanel<JPanel>().getJPanel(), BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("createGUI failed");
		}
	}
}
