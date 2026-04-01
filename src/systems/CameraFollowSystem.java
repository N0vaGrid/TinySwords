package systems;

import ecs.*;
import components.*;
import graphics.Camera;
import graphics.CameraManager;

import java.util.Set;

public class CameraFollowSystem extends SystemBase {

    private Query query;

    public CameraFollowSystem(EntityManager em, ComponentManager cm) {
        super(em, cm);

        query = new Query(
                TransformComponent.class,
                PlayerComponent.class
        );
    }

    @Override
    public void update() {

        Camera camera = CameraManager.getCamera();

        Set<Integer> entities = componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            camera.x =
                    transform.x - camera.viewportWidth / 2.0;

            camera.y =
                    transform.y - camera.viewportHeight / 2.0;

            CameraManager.clampToWorld();
        }
    }
}