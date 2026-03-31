package render;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

import engine.ConfigManager;
import engine.GameEngine;

public class GamePanel extends JPanel {

    int width = ConfigManager.getInt("window.width");
    int height = ConfigManager.getInt("window.height");

    private GameEngine engine;

    public GamePanel(GameEngine engine) {

        this.engine = engine;

        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        engine.render(g);
    }
}