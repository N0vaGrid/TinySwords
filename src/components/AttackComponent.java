package components;

import ecs.Component;

public class AttackComponent implements Component {

    // 是否正在攻击
    public boolean attacking = false;

    // 攻击冷却
    public int cooldown = 0;

    // 冷却时间
    public int maxCooldown = 25;

}