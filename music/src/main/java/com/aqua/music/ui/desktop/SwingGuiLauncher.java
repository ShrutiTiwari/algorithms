package com.aqua.music.ui.desktop;

import static com.aqua.music.ui.desktop.GuiItemBuilder.preferredSizeForMainPane;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SwingGuiLauncher
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

	public JFrame createAndShowGUI() {
		JFrame frame = new JFrame( frameTitle );
		frame.setLocationRelativeTo( null );

		frame.add( new GuiMultitabPanel(), BorderLayout.CENTER );

		//frame.setUndecorated( true );
		//AWTUtilities.setWindowOpacity(frame, 0.7f);
		
		// Display the window.
		frame.pack();
		frame.setVisible( true );
		
		frame.getContentPane().setPreferredSize( preferredSizeForMainPane );
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return frame;
	}
}