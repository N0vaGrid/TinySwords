package scene;

import components.*;
import core.GameConstants;
import ecs.*;
import ecs.SystemManager;
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
        attackSystem = new AttackSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(attackSystem);
        animationsystem = new AnimationSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(animationsystem);
        tileCollisionSystem = new TileCollisionSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(tileCollisionSystem);
        cameraSystem = new CameraSystem(entityManager, componentManager);
        systemManager.addUpdateSystem(cameraSystem);
        cameraFollowSystem = new CameraFollowSystem(entityManager , componentManager);
        systemManager.addUpdateSystem(cameraFollowSystem);
        debugRenderSystem = new DebugRenderSystem(entityManager, componentManager);
        systemManager.addRenderSystem(debugRenderSystem);

        TileSet tileSet = new TileSet(10);
        BufferedImage grass = TextureManager.getTexture("textures/tiles/grass.png");

       // BufferedImage wall = TextureManager.getTexture("textures/tiles/wall.png");

       BufferedImage water = TextureManager.getTexture("textures/tiles/water.png");

        tileSet.setTile(0, new Tile(grass, false, TileType.GRASS));
        //tileSet.setTile(1, new Tile(wall, true, TileType.WALL));
        tileSet.setTile(2, new Tile(water, true, TileType.WATER));

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

        Camera camera = CameraManager.getCamera();

        camera.setWorldSize(
                30 * GameConstants.RENDER_TILE_SIZE,
                20 * GameConstants.RENDER_TILE_SIZE
        );

        // 后面会加组件
        componentManager.addComponent(player, new PlayerComponent());
        componentManager.addComponent(player, new TransformComponent(5*GameConstants.TILE_SIZE, 5*GameConstants.TILE_SIZE));
        componentManager.addComponent(player, new InputComponent());
        componentManager.addComponent(player, new VelocityComponent());
        componentManager.addComponent(player, new SpriteComponent(
                graphics.TextureManager.getTexture("textures/player/idle/Warrior_Idle_01.png"),192 ,192
        ));
        componentManager.addComponent(
                player,
                new CollisionComponent(48,48 ,72 ,72)
        );
        componentManager.addComponent(player, new AttackComponent());

        AnimatorComponent animator = new AnimatorComponent();
        BufferedImage[] idle = new BufferedImage[]{
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_01.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_02.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_03.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_04.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_05.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_06.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_07.png"),
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_08.png"),
        };
        BufferedImage[] run = new BufferedImage[]{
                TextureManager.getTexture("textures/player/run/Warrior_Run_01.png"),
                TextureManager.getTexture("textures/player/run/Warrior_Run_02.png"),
                TextureManager.getTexture("textures/player/run/Warrior_Run_03.png"),
                TextureManager.getTexture("textures/player/run/Warrior_Run_04.png"),
                TextureManager.getTexture("textures/player/run/Warrior_Run_05.png")
        };
        BufferedImage[] attack = new BufferedImage[]{
                TextureManager.getTexture("textures/player/attack/Warrior_Attack_01.png"),
                TextureManager.getTexture("textures/player/attack/Warrior_Attack_02.png"),
                TextureManager.getTexture("textures/player/attack/Warrior_Attack_03.png"),
                TextureManager.getTexture("textures/player/attack/Warrior_Attack_04.png"),
        };

        AnimationComponent idleAnim = new AnimationComponent(idle,8);
        AnimationComponent runAnim = new AnimationComponent(run,8);
        AnimationComponent attackAnim = new AnimationComponent(attack,8 , false,  true);

        animator.animations.put("idle", idleAnim);
        animator.animations.put("run",runAnim);
        animator.animations.put("attack",attackAnim);


        animator.play("idle");

        componentManager.addComponent(player, animator);
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