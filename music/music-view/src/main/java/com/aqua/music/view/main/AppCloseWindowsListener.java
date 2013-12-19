/**
 * 
 */
package com.aqua.music.view.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import open.music.api.AudioPlayerFacade;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class AppCloseWindowsListener implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		AudioPlayerFacade.stop();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
