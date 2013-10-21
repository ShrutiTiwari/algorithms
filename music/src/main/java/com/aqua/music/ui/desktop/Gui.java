package com.aqua.music.ui.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Gui extends JFrame {

public static void main(String[] args) {
    new Gui().setVisible(true);
}

public Gui() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    add(new JPanel() {
        public static final int SIZE = 3;
        /** Line thickness ratio to a block */
        public static final float LINE_THICKNESS = 0.1f;

        /** @return the width of a block. */
        protected final int getBlockWidth() {
            return getWidth() / SIZE;
        }

        /** @return the height of a block. */
        protected final int getBlockHeight() {
            return getHeight() / SIZE;
        }

        /**  @return the width of a cell. */
        protected final int getCellWidth() {
            return (int) Math.ceil(getBlockWidth()*(1-LINE_THICKNESS));
        }

        /** @return the height of a cell. */
        protected final int getCellHeight() {
            return (int) Math.ceil(getBlockHeight()*(1-LINE_THICKNESS));
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 255, 100));
            int lineWidth = (int) (LINE_THICKNESS * getBlockWidth());
            int lineHeight = (int) (LINE_THICKNESS * getBlockHeight());
            for(int i = 0; i <= SIZE; i++) {
                g.fillRect(i * getBlockWidth() - lineWidth / 2, 0, lineWidth, getHeight());
                g.fillRect(0, i * getBlockHeight() - lineHeight/2, getWidth(), lineHeight);
            }
            g.setColor(new Color(255, 0, 0, 100));
            for(int i = 0; i < SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    int x = j * getBlockWidth() + lineWidth/2;
                    int y = i * getBlockHeight() + lineHeight/2;
                    Graphics temp = g.create(x, y, getCellWidth(), getCellHeight());
                    drawCell(temp, i, j);
                }
            }
        }

        private void drawCell(Graphics g, int i, int j) {
            g.fillRect(0, 0, getCellWidth(), getCellHeight());
        }
    });
    setLocation(new Point(500, 200));
    setSize(new Dimension(600, 600));
}
}