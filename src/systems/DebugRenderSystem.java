package systems;

import core.GameConstants;
import ecs.*;
import components.*;
import graphics.Camera;
import graphics.CameraManager;

import java.awt.*;
import java.util.Set;


public class DebugRenderSystem extends SystemBase {

    private Query query;

    public DebugRenderSystem(EntityManager em, ComponentManager cm) {
        super(em, cm);

        query = new Query(
                TransformComponent.class,
                CollisionComponent.class
        );
    }

    public void render(Graphics2D g2) {

        Set<Integer> entities = componentManager.getEntitiesWith(query);

        Camera camera = CameraManager.getCamera();

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2));

        for (int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            CollisionComponent collision =
                    componentManager.getComponent(entity, CollisionComponent.class);

            int x = (int)(transform.x + collision.offsetX);
            int y = (int)(transform.y + collision.offsetY);

            g2.drawRect(
                    x,
                    y,
                    collision.width,
                    collision.height
            );
        }
    }
}