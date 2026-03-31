package engine;

import components.ComponentManager;
import ecs.*;
import ecs.SystemManager;
import systems.*;

import java.awt.Graphics;

public class GameEngine {

    private EntityManager entityManager;
    private ComponentManager componentManager;
    private SystemManager systemManager;

    private RenderSystem renderSystem;

    public GameEngine() {

        entityManager = new EntityManager();
        componentManager = new ComponentManager();
        systemManager = new SystemManager();

        initSystems();
        initWorld();
    }

    private void initSystems() {

        renderSystem = new RenderSystem(entityManager, componentManager);

        systemManager.addRenderSystem(renderSystem);
    }

    private void initWorld() {

        int player = entityManager.createEntity();

        // 以后在这里添加组件
    }

    public void update() {

        systemManager.updateSystems();
    }

    public void render(Graphics g) {

        for(SystemBase system : systemManager.getRenderSystems()) {

            system.render(g);
        }
    }
}