package systems;

import core.GameConstants;
import ecs.*;
import components.*;
import graphics.*;

import java.util.Set;

public class CameraSystem extends SystemBase {

    private Query query;

    public CameraSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(TransformComponent.class, PlayerComponent.class);
    }

    @Override
    public void update() {

        Camera camera = CameraManager.getCamera();

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            camera.x = transform.x - (camera.viewportWidth / GameConstants.SCALE) / 2;
            camera.y = transform.y - (camera.viewportHeight / GameConstants.SCALE) / 2;

            break;
        }
    }
}