package com.aqua.music.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;

import com.aqua.music.view.UiMainFrame.UiTabbedPanel;

public class AppletLauncher extends JApplet {
	public void init() {
		try {
			Container container = getContentPane();
			UiTabbedPanel swingMainPanel = new UiMainFrame.UiTabbedPanel();
			add(swingMainPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("createGUI failed");
		}
	}
}
