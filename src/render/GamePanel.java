package render;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

import engine.GameEngine;

public class GamePanel extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private GameEngine engine;

    public GamePanel(GameEngine engine) {

        this.engine = engine;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        engine.render(g);
    }
}