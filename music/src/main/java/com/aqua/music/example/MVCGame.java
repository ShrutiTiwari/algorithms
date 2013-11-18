package com.aqua.music.example;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MVCGame implements Runnable {

    public static void main(String[] args) {
        EventQueue.invokeLater(new MVCGame());
    }

    @Override
    public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MainPanel());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class MainPanel extends JPanel {

    public MainPanel() {
        super(new BorderLayout());
        Model model = new Model();
        View view = new View(model);
        Control control = new Control(model, view);
        JLabel label = new JLabel("Guess what color!", JLabel.CENTER);
        this.add(label, BorderLayout.NORTH);
        this.add(view, BorderLayout.CENTER);
        this.add(control, BorderLayout.SOUTH);
    }
}

enum Piece {

    Red(Color.red), Green(Color.green), Blue(Color.blue);
    public Color color;

    private Piece(Color color) {
        this.color = color;
    }
}