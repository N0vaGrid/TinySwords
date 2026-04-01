package main;

import javax.swing.JFrame;

import engine.ConfigManager;
import engine.GameEngine;
import engine.GameLoop;
import input.KeyBindings;
import render.GamePanel;

public class TinySwordGame {

    public static void main(String[] args) {

        ConfigManager.load("config/game.properties");

        ConfigManager.load("config/controls.properties");

        KeyBindings.load();

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