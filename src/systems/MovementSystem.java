package systems;

import ecs.*;
import components.*;
import engine.ConfigManager;

import java.util.Set;

public class MovementSystem extends SystemBase {

    private Query query;

    private double speed;

    public MovementSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                TransformComponent.class,
                VelocityComponent.class,
                InputComponent.class
                //AnimationComponent.class
        );

        speed = ConfigManager.getDouble("player.speed");
    }

    @Override
    public void update() {

        Set<Integer> entities =
                componentManager.getEntitiesWith(query);

        for(int entity : entities) {

            TransformComponent transform =
                    componentManager.getComponent(entity, TransformComponent.class);

            VelocityComponent velocity =
                    componentManager.getComponent(entity, VelocityComponent.class);

            InputComponent input =
                    componentManager.getComponent(entity, InputComponent.class);

            AnimatorComponent animator =
                    componentManager.getComponent(entity, AnimatorComponent.class);

            PlayerComponent player =
                    componentManager.getComponent(entity, PlayerComponent.class);

            SpriteComponent sprite =
                    componentManager.getComponent(entity, SpriteComponent.class);

            StateComponent state =
                    componentManager.getComponent(entity, StateComponent.class);

            velocity.vx = 0;
            velocity.vy = 0;

            if(input.up) velocity.vy -= speed;
            if(input.down) velocity.vy += speed;
            if(input.left) {velocity.vx -= speed; sprite.flipX = true;}
            if(input.right) {velocity.vx += speed; sprite.flipX = false;}

            transform.x += velocity.vx;
            transform.y += velocity.vy;


            // 根据速度判断状态
            if(state != null){
                if(velocity.vx != 0 || velocity.vy != 0){

                    if(state.state != StateComponent.State.ATTACK)
                        state.state = StateComponent.State.RUN;

                }else{

                    if(state.state != StateComponent.State.ATTACK)
                        state.state = StateComponent.State.IDLE;
                }
            }


        }
    }
}