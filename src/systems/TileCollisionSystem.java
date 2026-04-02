package systems;

import ecs.*;
import components.*;
import tilemap.Tile;
import core.GameConstants;

import java.util.Set;

public class TileCollisionSystem extends SystemBase {

    private Query entityQuery;
    private Query tileMapQuery;

    public TileCollisionSystem(EntityManager em, ComponentManager cm) {
        super(em, cm);

        entityQuery = new Query(
                TransformComponent.class,
                VelocityComponent.class,
                CollisionComponent.class
        );

        tileMapQuery = new Query(
                TileMapComponent.class
        );
    }

    @Override
    public void update() {

        Set<Integer> mapEntities =
                componentManager.getEntitiesWith(tileMapQuery);

        if (mapEntities.isEmpty()) return;

        int mapEntity = mapEntities.iterator().next();

        TileMapComponent mapComp =
                componentManager.getComponent(mapEntity, TileMapComponent.class);

        var map = mapComp.map;

        int tileSize = GameConstants.RENDER_TILE_SIZE;

        Set<Integer> entities =
                componentManager.getEntitiesWith(entityQuery);

        for (int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            VelocityComponent velocity =
                    componentManager.getComponent(entity, VelocityComponent.class);

            CollisionComponent collision =
                    componentManager.getComponent(entity, CollisionComponent.class);

            double oldX = transform.x - velocity.vx;
            double oldY = transform.y - velocity.vy;

            int leftTile = (int)(transform.x / tileSize);
            int rightTile = (int)((transform.x + collision.width) / tileSize);

            int topTile = (int)(transform.y / tileSize);
            int bottomTile = (int)((transform.y + collision.height) / tileSize);

            boolean blocked = false;

            for (int y = topTile; y <= bottomTile; y++) {

                for (int x = leftTile; x <= rightTile; x++) {

                    if (x < 0 || y < 0 || x >= map.width || y >= map.height)
                        continue;

                    int tileId = map.tiles[y][x];

                    Tile tile = map.tileSet.getTile(tileId);

                    if (tile != null && tile.solid) {

                        blocked = true;
                        break;
                    }
                }

                if (blocked) break;
            }

            if (blocked) {

                transform.x = oldX;
                transform.y = oldY;

                velocity.vx = 0;
                velocity.vy = 0;
            }
        }
    }
}