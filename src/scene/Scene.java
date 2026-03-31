package scene;

import java.awt.Graphics;

public abstract class Scene {

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void dispose();
}