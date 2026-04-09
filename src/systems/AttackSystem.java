package systems;

import components.AttackComponent;
import components.AnimatorComponent;
import components.InputComponent;
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

            // 冷却计时
            if (attack.cooldown > 0)
                attack.cooldown--;

            // 攻击输入
            if (input.attack && attack.cooldown == 0) {

                attack.cooldown = attack.maxCooldown;

                attack.attacking = true;

                animator.play("attack");

            }

        }

    }

}