package render;

import javax.swing.JPanel;
import java.awt.*;

import engine.ConfigManager;
import engine.GameEngine;
import input.KeyboardListener;

public class GamePanel extends JPanel {

    int width = ConfigManager.getInt("window.width");
    int height = ConfigManager.getInt("window.height");

    private GameEngine engine;

    public GamePanel(GameEngine engine) {

        this.engine = engine;

        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);

        addKeyListener(new KeyboardListener());
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );

        engine.render(g);
    }
}