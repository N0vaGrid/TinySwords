package components;

import ecs.Component;

public class CollisionComponent implements Component {

    public int width;
    public int height;

    public CollisionComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }
}