package com.aqua.music.ui;

import static com.aqua.music.ui.DisplayItemFactory.preferredSizeForMainPane;
import static com.aqua.music.ui.DisplayItemFactory.preferredSizeForThaatPanel;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.model.FrequencySet.SymmetricalSet;

class SwingGUIMaker {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SwingGUIMaker().createAndShowGUI();
			}
		});
	}

	void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("RehearseClassicalMusic");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(preferredSizeForMainPane);
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Have Fun!!");
		frame.getContentPane().add(label);

		frame.getContentPane().add(createPlayablePanel());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private Component createPlayablePanel() {
		DisplayItemFactory displayItemFactory = new DisplayItemFactory();

		JPanel playablePanel = new JPanel();
		playablePanel.setLayout(null);
		playablePanel.setPreferredSize(preferredSizeForThaatPanel);
		for (SymmetricalSet each : SymmetricalSet.values()) {
			playablePanel.add(displayItemFactory.createWith(
					DisplayItemType.PLAYABLE, each));
		}
		playablePanel.add(displayItemFactory.createWith(DisplayItemType.QUIT,
				null));
		return playablePanel;
	}
}