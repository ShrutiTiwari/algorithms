package com.aqua.music.ui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UiLauncher {
	private static final String frameTitle = "Music";
	private static final String frameLabel = "Have fun with Indian Classical Music!!";
	private static final Dimension preferredSizeForMainPane = new Dimension(450, 450);

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// UIManager.put("swing.boldMetal", Boolean.FALSE);
				new UiLauncher().createAndShowUi();
			}
		});
	}

	public JFrame createAndShowUi() {
		final JFrame frame = new JFrame(frameTitle);
		frame.setLocationRelativeTo(null);

		frame.add(new UiTabbedPanel(), BorderLayout.CENTER);

		// frame.setUndecorated( true );
		// AWTUtilities.setWindowOpacity(frame, 0.7f);
		frame.pack();
		frame.setVisible(true);

		frame.getContentPane().setPreferredSize(preferredSizeForMainPane);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return frame;
	}

	static class UiTabbedPanel extends JPanel {
		public UiTabbedPanel() {
			super(new GridLayout(1, 1));
			JTabbedPane tabbedPane = createTabbedPan();
			add(tabbedPane);
		}

		private JTabbedPane createTabbedPan() {
			JTabbedPane tabbedPane = new JTabbedPane();

			RehearseTabs reharseTabFactory = new RehearseTabs(tabbedPane);
			tabbedPane.addTab("Thaat rehearse", reharseTabFactory.plainTab());
			reharseTabFactory.addPatternTab();

			tabbedPane.setOpaque(true);
			return tabbedPane;
		}
	}
}