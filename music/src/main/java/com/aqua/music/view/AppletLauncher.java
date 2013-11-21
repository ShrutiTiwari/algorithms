package com.aqua.music.view;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

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
