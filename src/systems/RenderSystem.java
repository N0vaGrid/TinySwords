package systems;

import ecs.*;
import components.*;

import java.awt.Graphics;
import java.util.Set;

public class RenderSystem extends SystemBase {

    private Query query;

    public RenderSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                TransformComponent.class,
                SpriteComponent.class
        );
    }

    public void render(Graphics g) {

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            SpriteComponent sprite =
                    componentManager.getComponent(entity, SpriteComponent.class);

            g.drawImage(
                    sprite.texture,
                    (int) transform.x,
                    (int) transform.y,
                    sprite.width,
                    sprite.height,
                    null
            );
        }
    }

    @Override
    public void update() {

    }
}