package octopus.webapps.ui;

import java.awt.Component;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.*;        

public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        //frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        frame.getContentPane().setPreferredSize(new Dimension(300,400));
        
        
        frame.getContentPane().add(createQuitPanel());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static Component createQuitPanel() {
    	JPanel quitPanel=new JPanel();
    	quitPanel.setLayout(null);
    	
    	JButton quitButton = new JButton("Quit");
    	quitButton.setBounds(50, 60, 80, 30);
    	quitButton.setToolTipText("Click this to abort!");
    	
    	quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
    	
    	//quitPanel.setPreferredSize(new Dimension(200,200));
    	quitPanel.add(quitButton);
    	
		return quitPanel;
	}

	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}