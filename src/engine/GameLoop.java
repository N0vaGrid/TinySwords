package engine;

import render.GamePanel;

public class GameLoop implements Runnable {

    private GameEngine engine;
    private GamePanel panel;

    private Thread thread;
    private boolean running = false;

    private final int FPS = 60;

    public GameLoop(GameEngine engine, GamePanel panel) {
        this.engine = engine;
        this.panel = panel;
    }

    public void start() {

        running = true;

        thread = new Thread(this);

        thread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS;

        long lastTime = System.nanoTime();

        double delta = 0;

        while (running) {

            long now = System.nanoTime();

            delta += (now - lastTime) / timePerFrame;

            lastTime = now;

            if (delta >= 1) {

                engine.update();

                panel.repaint();

                delta--;
            }
        }
    }
}