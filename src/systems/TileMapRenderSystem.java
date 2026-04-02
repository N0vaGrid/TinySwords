package systems;

import core.GameConstants;
import ecs.*;
import components.*;
import graphics.Camera;
import graphics.CameraManager;
import tilemap.Tile;

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

        for (int entity : entities) {

            TileMapComponent mapComp =
                    componentManager.getComponent(entity, TileMapComponent.class);

            var map = mapComp.map;

            // 可见区域计算
            int startCol = (int)(camera.x / tileSize);
            int endCol = (int)((camera.x + camera.viewportWidth) / tileSize) + 1;

            int startRow = (int)(camera.y / tileSize);
            int endRow = (int)((camera.y + camera.viewportHeight) / tileSize) + 1;

            for (int y = startRow; y < endRow; y++) {

                if (y < 0 || y >= map.height) continue;

                for (int x = startCol; x < endCol; x++) {

                    if (x < 0 || x >= map.width) continue;

                    int tileId = map.tiles[y][x];

                    Tile tile = map.tileSet.getTile(tileId);

                    if (tile == null) continue;

                    BufferedImage tileImage = tile.texture;

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
        // TileMap 目前不需要 update
    }
}