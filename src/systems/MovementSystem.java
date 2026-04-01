package systems;

import ecs.*;
import components.*;
import engine.ConfigManager;

import java.util.Set;

public class MovementSystem extends SystemBase {

    private Query query;

    private double speed;

    public MovementSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                TransformComponent.class,
                VelocityComponent.class,
                InputComponent.class
        );

        speed = ConfigManager.getDouble("player.speed");
    }

    @Override
    public void update() {

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            VelocityComponent velocity =
                    componentManager.getComponent(entity, VelocityComponent.class);

            InputComponent input =
                    componentManager.getComponent(entity, InputComponent.class);

            velocity.vx = 0;
            velocity.vy = 0;

            if(input.up) velocity.vy -= speed;
            if(input.down) velocity.vy += speed;
            if(input.left) velocity.vx -= speed;
            if(input.right) velocity.vx += speed;

            transform.x += velocity.vx;
            transform.y += velocity.vy;

            //System.out.println(transform.x + "," + transform.y);
        }
    }
}