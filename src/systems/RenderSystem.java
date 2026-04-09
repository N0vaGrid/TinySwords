package systems;

import core.GameConstants;
import ecs.*;
import components.*;
import graphics.Camera;
import graphics.CameraManager;

import java.awt.Graphics;
import java.util.Set;

public class RenderSystem extends SystemBase {

    private Query query;

    public RenderSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                TransformComponent.class,
                SpriteComponent.class
        );
    }

    public void render(Graphics g) {

        int renderSize = GameConstants.RENDER_TILE_SIZE;

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            SpriteComponent sprite =
                    componentManager.getComponent(entity, SpriteComponent.class);

            Camera camera = CameraManager.getCamera();

            int screenX = (int)((transform.x - camera.x) * GameConstants.SCALE);
            int screenY = (int)((transform.y - camera.y) * GameConstants.SCALE);

            int width = sprite.width * GameConstants.SCALE;
            int height = sprite.height * GameConstants.SCALE;

            if(sprite.flipX){

                g.drawImage(
                        sprite.texture,
                        screenX + width,
                        screenY,
                        -width,
                        height,
                        null
                );

            }else{

                g.drawImage(
                        sprite.texture,
                        screenX,
                        screenY,
                        width,
                        height,
                        null
                );

            }

        }
    }

    @Override
    public void update() {

    }
}