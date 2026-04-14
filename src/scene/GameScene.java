package scene;

import components.*;
import core.GameConstants;
import ecs.*;
import ecs.SystemManager;
import ecs.factory.EntityFactory;
import ecs.init.SystemInitializer;
import engine.ConfigManager;
import graphics.Camera;
import graphics.CameraManager;
import graphics.TextureManager;
import systems.*;
import tilemap.TileMap;
import tilemap.TileMapLoader;
import tilemap.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScene extends Scene {

    private EntityManager entityManager;
    private ComponentManager componentManager;
    private SystemManager systemManager;

    private RenderSystem renderSystem;
    private InputSystem inputSystem;
    private MovementSystem movementSystem;
    private CameraSystem cameraSystem;
    private TileMapRenderSystem tileSystem;
    private CameraFollowSystem cameraFollowSystem;
    private TileCollisionSystem tileCollisionSystem;
    private DebugRenderSystem debugRenderSystem;
    private AnimationSystem animationsystem;
    private AttackSystem attackSystem;
    @Override
    public void init() {

        // ===== 初始化相机 =====
        CameraManager.init(
                ConfigManager.getInt("window.width"),
                ConfigManager.getInt("window.height")
        );

        // ===== 初始化 ECS =====
        entityManager = new EntityManager();
        componentManager = new ComponentManager();
        systemManager = new SystemManager();

        // ===== 初始化系统（已解耦）=====
        SystemInitializer.init(systemManager, entityManager, componentManager);

        // 单独拿出来（因为render 要用）
        renderSystem = new RenderSystem(entityManager, componentManager);
        debugRenderSystem = new DebugRenderSystem(entityManager, componentManager);

        systemManager.addRenderSystem(renderSystem);
        systemManager.addRenderSystem(debugRenderSystem);

        // ===== 初始化地图 =====
        initTileMap();

        // ===== 初始化世界 =====
        initWorld();
    }

    private void initTileMap(){

        TileSet tileSet = new TileSet(10);

        BufferedImage grass = TextureManager.getTexture("textures/tiles/grass.png");
        BufferedImage water = TextureManager.getTexture("textures/tiles/water.png");

        tileSet.setTile(0, new Tile(grass, false, TileType.GRASS));
        tileSet.setTile(2, new Tile(water, true, TileType.WATER));

        TileMap map = TileMapLoader.load("maps/world.txt", tileSet);

        int mapEntity = entityManager.createEntity();

        componentManager.addComponent(
                mapEntity,
                new TileMapComponent(map)
        );

        tileSystem = new TileMapRenderSystem(entityManager, componentManager);
        systemManager.addRenderSystem(tileSystem);
    }

    private void initWorld() {
        Camera camera = CameraManager.getCamera();

        camera.setWorldSize(
                30 * GameConstants.RENDER_TILE_SIZE,
                20 * GameConstants.RENDER_TILE_SIZE
        );

        // ===== 创建玩家（已交给工厂）=====
        EntityFactory.createPlayer(entityManager, componentManager);
    }

    @Override
    public void update() {

        systemManager.updateSystems();
    }

    @Override
    public void render(Graphics g) {

        tileSystem.render(g);

        renderSystem.render(g);

        if (g instanceof Graphics2D) {
            debugRenderSystem.render((Graphics2D) g);
        }
    }

    @Override
    public void dispose() {

    }
}