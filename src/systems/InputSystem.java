package systems;

import ecs.*;
import components.*;
import input.*;

import java.util.Set;

public class InputSystem extends SystemBase {

    private Query query;

    public InputSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(InputComponent.class);
    }

    @Override
    public void update() {

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            InputComponent input =
                    componentManager.getComponent(entity, InputComponent.class);
            PlayerComponent player =
                    componentManager.getComponent(entity, PlayerComponent.class);

            input.up =
                InputManager.isKeyDown(KeyBindings.get("move.up"));

            input.down =
                InputManager.isKeyDown(KeyBindings.get("move.down"));

            input.left =
                InputManager.isKeyDown(KeyBindings.get("move.left"));

            input.right =
                InputManager.isKeyDown(KeyBindings.get("move.right"));

            input.attack =
                InputManager.isKeyDown(KeyBindings.get("attack"));

        }
    }
}