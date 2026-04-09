package components;

import ecs.Component;

public class PlayerComponent implements Component {

    // 攻击状态
    public boolean isAttacking = false;

    // 攻击计时
    public float attackTimer = 0f;

    // 攻击持续时间
    public float attackDuration = 0.35f;

}