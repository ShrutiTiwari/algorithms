package com.aqua.music.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aqua.music.model.FrequencySet.SymmetricalSet;

class SwingGUIMaker {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static final int HORIZONAL_COORIDNATE = 30;
	private static final int BUTTON_WIDTH = 400;
	private static final int BUTTON_HEIGHT = 30;

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
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
		frame.getContentPane().setPreferredSize(new Dimension(450, 450));
		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Have Fun!!");
		frame.getContentPane().add(label);

		frame.getContentPane().add(createThaatPanel());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private Component createThaatPanel() {
		JPanel thaatPanel = new JPanel();
		thaatPanel.setLayout(null);
		thaatPanel.setPreferredSize(new Dimension(400, 400));
		int i = 0;
		for (SymmetricalSet each : SymmetricalSet.values()) {
			thaatPanel.add(new ThaatButton(each, i++).get());
		}
		thaatPanel.add(quitButton(400));
		return thaatPanel;
	}

	private JButton quitButton(int verticalIndex) {
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(HORIZONAL_COORIDNATE, verticalIndex, BUTTON_WIDTH, BUTTON_HEIGHT);
		quitButton.setToolTipText("Click this to play Kafi!");

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		return quitButton;
	}

	private class ThaatButton extends JButton {
		private SymmetricalSet selectedSet;
		private final int THAAT_BUTTON_WIDTH = 200;
		private int index;

		ThaatButton(SymmetricalSet specificThaat, int index) {
			this.selectedSet = specificThaat;
			this.index = index;
		}

		JButton get() {
			JButton specificButton = new JButton("Play " + selectedSet.name());
			specificButton.setBounds(HORIZONAL_COORIDNATE, (10 + (index * BUTTON_HEIGHT) + 50), THAAT_BUTTON_WIDTH, BUTTON_HEIGHT);
			specificButton.setToolTipText("Click this to play Kafi!");
			specificButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					selectedSet.nonblockingPlayAscendAndDescend();
				}
			});
			return specificButton;
		}
	}
}