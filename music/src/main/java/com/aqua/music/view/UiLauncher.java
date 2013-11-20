package com.aqua.music.view;


public class UiLauncher {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void main(String[] args) {
		new UiLauncher().launch();
	}

	void launch(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// UIManager.put("swing.boldMetal", Boolean.FALSE);
				new UiMainFrame().initialize();
			}
		});
	}
}