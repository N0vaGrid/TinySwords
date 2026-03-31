package components;

import ecs.Component;
import java.awt.image.BufferedImage;

public class SpriteComponent implements Component {

    public BufferedImage sprite;
    public int width;
    public int height;

    public SpriteComponent(BufferedImage sprite, int width, int height) {

        this.sprite = sprite;
        this.width = width;
        this.height = height;
    }
}