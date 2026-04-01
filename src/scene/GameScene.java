package scene;

import components.*;
import core.GameConstants;
import ecs.*;
import ecs.SystemManager;
import engine.ConfigManager;
import graphics.CameraManager;
import graphics.TextureManager;
import systems.*;
import tilemap.TileMap;
import tilemap.TileMapLoader;
import tilemap.TileSet;

import java.awt.Graphics;

public class GameScene extends Scene {

    private EntityManager entityManager;
    private ComponentManager componentManager;
    private SystemManager systemManager;

    private RenderSystem renderSystem;
    private InputSystem inputSystem;
    private MovementSystem movementSystem;
    private CameraSystem cameraSystem;
    private TileMapRenderSystem tileSystem;

    @Override
    public void init() {

        CameraManager.init(
                ConfigManager.getInt("window.width"),
                ConfigManager.getInt("window.height")
        );

        entityManager = new EntityManager();
        componentManager = new ComponentManager();
        systemManager = new SystemManager();

        renderSystem = new RenderSystem(entityManager, componentManager);
        systemManager.addRenderSystem(renderSystem);

        inputSystem = new InputSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(inputSystem);
        movementSystem = new MovementSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(movementSystem);
        cameraSystem = new CameraSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(cameraSystem);

        var tilesetTexture =
                TextureManager.getTexture("textures/tiles/tileset.png");

        TileSet tileSet = new TileSet(tilesetTexture, GameConstants.TILE_SIZE);

        TileMap map =
                TileMapLoader.load("maps/world.txt", tileSet);

        int mapEntity = entityManager.createEntity();

        componentManager.addComponent(
                mapEntity,
                new TileMapComponent(map)
        );

        tileSystem =
                new TileMapRenderSystem(entityManager, componentManager);

        systemManager.addRenderSystem(tileSystem);

        initWorld();
    }

    private void initWorld() {

        int player = entityManager.createEntity();

        // 后面会加组件
        componentManager.addComponent(player, new PlayerComponent());
        componentManager.addComponent(player, new TransformComponent(5*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE));
        componentManager.addComponent(player, new InputComponent());
        componentManager.addComponent(player, new VelocityComponent());
        componentManager.addComponent(player, new SpriteComponent(
                graphics.TextureManager.getTexture("textures/images/Warrior_Idle_01.png"),192 ,192
        ));
    }

    @Override
    public void update() {

        systemManager.updateSystems();
    }

    @Override
    public void render(Graphics g) {

        tileSystem.render(g);

        renderSystem.render(g);
    }

    @Override
    public void dispose() {

    }
}