package com.aqua.music.view;

public class UiLauncher {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void main(String[] args) {
		GuiLauncher.SWING.launch();
	}

	public interface GuiLauncher {
		void launch();

		GuiLauncher SWING = new GuiLauncher() {
			@Override
			public void launch() {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						// UIManager.put("swing.boldMetal", Boolean.FALSE);
						UiMainContainer uiMainContainer = UiMainContainer.SWING;
						uiMainContainer.addPanel(new UIMainPanel());
						uiMainContainer.display();
					}
				});
			}
		};
	}
}