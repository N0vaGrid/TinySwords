package main;

import javax.swing.JFrame;

import engine.GameEngine;
import engine.GameLoop;
import render.GamePanel;

public class TinySwordGame {

    public static void main(String[] args) {

        GameEngine engine = new GameEngine();

        GamePanel panel = new GamePanel(engine);

        JFrame window = new JFrame("Tiny Sword");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GameLoop loop = new GameLoop(engine, panel);

        loop.start();
    }
}