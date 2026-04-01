package components;

import ecs.Component;
import java.awt.image.BufferedImage;

public class SpriteComponent implements Component {

    public BufferedImage texture;

    public int width;
    public int height;

    public SpriteComponent(BufferedImage texture , int width , int height) {

        this.texture = texture;

        this.width = width;
        this.height = height;
    }
}