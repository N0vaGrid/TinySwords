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
                SpriteComponent.class
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

            AnimationComponent anim = animator.getCurrentAnimation();

            AttackComponent attack =
                    componentManager.getComponent(entity, AttackComponent.class);

            VelocityComponent velocity =
                    componentManager.getComponent(entity, VelocityComponent.class);
            if(attack != null && attack.attacking){
                // 攻击时保持 attack 动画
            }
            else{

                if(velocity.vx != 0 || velocity.vy != 0)
                    animator.play("run");
                else
                    animator.play("idle");
            }

            if (anim == null)
                continue;

            anim.timer++;

            if (anim.timer >= anim.frameDuration) {

                anim.timer = 0;

                anim.frameIndex++;

                if (anim.frameIndex >= anim.frames.length) {

                    if (anim.loop) {

                        anim.frameIndex = 0;

                    } else {

                        anim.frameIndex = anim.frames.length - 1;

                        // 如果是一次性动画
                        if(anim.oneShot && attack != null){

                            attack.attacking = false;

                            if(velocity.vx != 0 || velocity.vy != 0)
                                animator.play("run");
                            else
                                animator.play("idle");

                            anim = animator.getCurrentAnimation();
                        }
                    }
                }
            }

            sprite.texture = anim.frames[anim.frameIndex];
        }
    }
}