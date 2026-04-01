package systems;

import core.GameConstants;
import ecs.*;
import components.*;
import graphics.Camera;
import graphics.CameraManager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Set;

public class TileMapRenderSystem extends SystemBase {

    private Query query;

    public TileMapRenderSystem(EntityManager em, ComponentManager cm) {
        super(em, cm);
        query = new Query(TileMapComponent.class);
    }

    public void render(Graphics g) {

        Camera camera = CameraManager.getCamera();

        int tileSize = GameConstants.RENDER_TILE_SIZE;

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TileMapComponent mapComp =
                    componentManager.getComponent(entity, TileMapComponent.class);

            var map = mapComp.map;

            // 只渲染可见区域
            int startCol = (int)(camera.x / tileSize);
            int endCol = (int)((camera.x + camera.viewportWidth) / tileSize) + 1;

            int startRow = (int)(camera.y / tileSize);
            int endRow = (int)((camera.y + camera.viewportHeight) / tileSize) + 1;

            for(int y = startRow; y < endRow; y++) {

                if(y < 0 || y >= map.height) continue;

                for(int x = startCol; x < endCol; x++) {

                    if(x < 0 || x >= map.width) continue;

                    int tile = map.tiles[y][x];

                    BufferedImage tileImage =
                            map.tileSet.getTile(tile);

                    if(tileImage == null) continue;

                    int worldX = x * tileSize;
                    int worldY = y * tileSize;

                    int screenX = (int)(worldX - camera.x);
                    int screenY = (int)(worldY - camera.y);

                    g.drawImage(
                            tileImage,
                            screenX,
                            screenY,
                            tileSize,
                            tileSize,
                            null
                    );
                }
            }
        }
    }

    @Override
    public void update() {

    }
}