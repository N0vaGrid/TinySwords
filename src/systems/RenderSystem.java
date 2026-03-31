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

    @Override
    public void render(Graphics g) {

        Set<Integer> entities = componentManager.getEntitiesWith(query);

        for (int entity : entities) {

            TransformComponent t =
                    componentManager.getComponent(entity, TransformComponent.class);

            SpriteComponent s =
                    componentManager.getComponent(entity, SpriteComponent.class);

            g.drawImage(
                    s.sprite,
                    (int)t.x,
                    (int)t.y,
                    null
            );
        }
    }
}