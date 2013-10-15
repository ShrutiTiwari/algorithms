package com.aqua.music.ui;

import static com.aqua.music.ui.GuiItemBuilder.preferredSizeForMainPane;

import java.awt.BorderLayout;

import javax.swing.JFrame;

class SwingGuiLauncher
{
	private static final String frameTitle = "Music";
	private static final String frameLabel = "Have some fun with Indian Classical Music!!";

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked from the event-dispatching thread.
	 */
	public static void main( String[] args ) {
		javax.swing.SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				// UIManager.put("swing.boldMetal", Boolean.FALSE);
				new SwingGuiLauncher().createAndShowGUI();
			}
		} );
	}

	void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame( frameTitle );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		frame.setLocationRelativeTo( null );
		frame.getContentPane().setPreferredSize( preferredSizeForMainPane );

		frame.add( new GuiMultitabPanel(), BorderLayout.CENTER );

		// Display the window.
		frame.pack();
		frame.setVisible( true );
	}
}