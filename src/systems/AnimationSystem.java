package systems;

import components.*;
import ecs.ComponentManager;
import ecs.EntityManager;
import ecs.Query;
import ecs.SystemBase;

import java.util.Set;

public class AnimationSystem extends SystemBase {

    private Query query;

    public AnimationSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                AnimatorComponent.class,
                SpriteComponent.class,
                StateComponent.class
        );
    }

    @Override
    public void update() {

        Set<Integer> entities = componentManager.getEntitiesWith(query);

        for (int entity : entities) {

            AnimatorComponent animator =
                    componentManager.getComponent(entity, AnimatorComponent.class);

            SpriteComponent sprite =
                    componentManager.getComponent(entity, SpriteComponent.class);

            StateComponent state =
                    componentManager.getComponent(entity, StateComponent.class);

            // 根据状态播放动画
            switch (state.state){

                case ATTACK:
                    animator.play("attack");
                    break;

                case RUN:
                    animator.play("run");
                    break;

                case IDLE:
                    animator.play("idle");
                    break;
            }

            AnimationComponent anim = animator.getCurrentAnimation();

            if(state.state == StateComponent.State.ATTACK){

                if(anim != null && !anim.loop){

                    if(anim.frameIndex >= anim.frames.length - 1){
                        state.state = StateComponent.State.IDLE;
                    }

                }
            }

            if(anim == null)
                continue;

            anim.timer++;

            if(anim.timer >= anim.frameDuration){

                anim.timer = 0;

                anim.frameIndex++;

                if(anim.frameIndex >= anim.frames.length){

                    if(anim.loop){

                        anim.frameIndex = 0;

                    }else{

                        anim.frameIndex = anim.frames.length - 1;
                    }
                }
            }

            sprite.texture = anim.frames[anim.frameIndex];
        }
    }
}