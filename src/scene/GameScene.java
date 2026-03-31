package scene;

import ecs.*;
import systems.*;

import java.awt.Graphics;

public class GameScene extends Scene {

    private EntityManager entityManager;
    private ComponentManager componentManager;
    private SystemManager systemManager;

    private RenderSystem renderSystem;

    @Override
    public void init() {

        entityManager = new EntityManager();
        componentManager = new ComponentManager();
        systemManager = new SystemManager();

        renderSystem = new RenderSystem(entityManager, componentManager);

        systemManager.addRenderSystem(renderSystem);

        initWorld();
    }

    private void initWorld() {

        int player = entityManager.createEntity();

        // 后面会加组件
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