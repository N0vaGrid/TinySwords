package components;

import ecs.Component;

public class TransformComponent implements Component {

    public double x;
    public double y;

    public TransformComponent(double x, double y) {
        this.x = x;
        this.y = y;
    }
}