/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.TextArea;

import javax.swing.JButton;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class CommonPanelComponents {
	private final TextArea consoleArea;
	private final JButton pauseButton;

	public CommonPanelComponents(JButton pauseButton, TextArea consoleArea) {
		this.pauseButton = pauseButton;
		this.consoleArea = consoleArea;
	}

	public TextArea consoleArea() {
		return consoleArea;
	}

	public JButton pauseButton() {
		return pauseButton;
	}
}
