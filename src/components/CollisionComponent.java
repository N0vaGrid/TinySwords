package components;

import ecs.Component;

public class CollisionComponent implements Component {

    public int width;
    public int height;

    public int offsetX;
    public int offsetY;

    public CollisionComponent(int width, int height) {
        this(width, height, 0, 0);
    }

    public CollisionComponent(int width, int height, int offsetX, int offsetY) {
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}