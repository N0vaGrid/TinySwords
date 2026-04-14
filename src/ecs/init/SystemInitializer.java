package ecs.init;

import ecs.*;
import systems.*;

public class SystemInitializer {

    public static void init(
            SystemManager sm,
            EntityManager em,
            ComponentManager cm
    ){

        //sm.addRenderSystem(new RenderSystem(em, cm));
        sm.addUpdateSystem(new InputSystem(em, cm));
        sm.addUpdateSystem(new MovementSystem(em, cm));
        sm.addUpdateSystem(new AttackSystem(em, cm));
        sm.addUpdateSystem(new AnimationSystem(em, cm));
        sm.addUpdateSystem(new TileCollisionSystem(em, cm));
        sm.addUpdateSystem(new CameraSystem(em, cm));
        sm.addUpdateSystem(new CameraFollowSystem(em, cm));
        //sm.addRenderSystem(new DebugRenderSystem(em, cm));
    }
}