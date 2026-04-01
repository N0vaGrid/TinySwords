package scene;

import components.*;
import ecs.*;
import ecs.SystemManager;
import systems.*;

import java.awt.Graphics;

public class GameScene extends Scene {

    private EntityManager entityManager;
    private ComponentManager componentManager;
    private SystemManager systemManager;

    private RenderSystem renderSystem;
    private InputSystem inputSystem;
    private MovementSystem movementSystem;

    @Override
    public void init() {

        entityManager = new EntityManager();
        componentManager = new ComponentManager();
        systemManager = new SystemManager();

        renderSystem = new RenderSystem(entityManager, componentManager);
        systemManager.addRenderSystem(renderSystem);

        inputSystem = new InputSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(inputSystem);
        movementSystem = new MovementSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(movementSystem);


        initWorld();
    }

    private void initWorld() {

        int player = entityManager.createEntity();

        // 后面会加组件
        componentManager.addComponent(player, new TransformComponent(100, 100));
        componentManager.addComponent(player, new InputComponent());
        componentManager.addComponent(player, new VelocityComponent());
        componentManager.addComponent(player, new SpriteComponent(
                graphics.TextureManager.getTexture("textures/images/Warrior_Idle_01.png")
        ));
    }

    @Override
    public void update() {

        systemManager.updateSystems();
    }

    @Override
    public void render(Graphics g) {

        renderSystem.render(g);
    }

    @Override
    public void dispose() {

    }
}