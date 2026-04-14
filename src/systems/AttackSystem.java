package systems;

import components.*;
import ecs.ComponentManager;
import ecs.EntityManager;
import ecs.Query;
import ecs.SystemBase;

import java.util.Set;

public class AttackSystem extends SystemBase {

    private Query query;

    public AttackSystem(EntityManager em, ComponentManager cm) {

        super(em, cm);

        query = new Query(
                InputComponent.class,
                AttackComponent.class,
                AnimatorComponent.class
        );
    }

    @Override
    public void update() {

        Set<Integer> entities = componentManager.getEntitiesWith(query);

        for (int entity : entities) {

            InputComponent input =
                    componentManager.getComponent(entity, InputComponent.class);

            AttackComponent attack =
                    componentManager.getComponent(entity, AttackComponent.class);

            AnimatorComponent animator =
                    componentManager.getComponent(entity, AnimatorComponent.class);

            AnimationComponent anim = animator.getCurrentAnimation();

            // 冷却计时
            if (attack.cooldown > 0)
                attack.cooldown--;

            // 攻击输入
            if (input.attack && attack.cooldown == 0) {

                attack.attacking = true;

                StateComponent state =
                        componentManager.getComponent(entity, StateComponent.class);

                state.state = StateComponent.State.ATTACK;

                animator.play("attack");
            }
            if(attack.attacking && anim.oneShot){

                if(anim.frameIndex == anim.frames.length - 1){

                    attack.attacking = false;

                    StateComponent state =
                            componentManager.getComponent(entity, StateComponent.class);

                    state.state = StateComponent.State.IDLE;
                }
            }

        }

    }

}