package systems;

import ecs.*;
import components.*;

import java.awt.Graphics;
import java.util.Map;

public class RenderSystem extends SystemBase {

    public RenderSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);
    }

    @Override
    public void render(Graphics g) {

        Map<Integer, Component> transforms =
                componentManager.getComponents(TransformComponent.class);

        for (int entity : transforms.keySet()) {

            TransformComponent t =
                    (TransformComponent) transforms.get(entity);

            SpriteComponent s =
                    componentManager.getComponent(entity, SpriteComponent.class);

            if (s != null) {

                g.drawImage(
                        s.sprite,
                        (int) t.x,
                        (int) t.y,
                        null
                );
            }
        }
    }
}