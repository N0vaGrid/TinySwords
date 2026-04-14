package ecs.factory;

import components.*;
import ecs.ComponentManager;
import ecs.EntityManager;
import graphics.TextureManager;

import java.awt.image.BufferedImage;

public class EntityFactory {

    public static int createPlayer(EntityManager em, ComponentManager cm){

        int player = em.createEntity();

        // ===== 基础组件 =====
        cm.addComponent(player, new PlayerComponent());
        cm.addComponent(player, new TransformComponent(5*64, 5*64));
        cm.addComponent(player, new InputComponent());
        cm.addComponent(player, new VelocityComponent());
        cm.addComponent(player, new AttackComponent());
        cm.addComponent(player, new StateComponent());

        // ===== 渲染 =====
        cm.addComponent(player, new SpriteComponent(
                TextureManager.getTexture("textures/player/idle/Warrior_Idle_01.png"),
                192, 192
        ));

        // ===== 碰撞 =====
        cm.addComponent(player, new CollisionComponent(48,48,72,72));

        // ===== 动画 =====
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
        AnimationComponent attackAnim = new AnimationComponent(attack,12,false,true);

        animator.animations.put("idle", idleAnim);
        animator.animations.put("run", runAnim);
        animator.animations.put("attack", attackAnim);

        animator.play("idle");

        cm.addComponent(player, animator);

        return player;
    }
}