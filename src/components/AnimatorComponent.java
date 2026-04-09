package components;

import ecs.Component;
import java.util.HashMap;

public class AnimatorComponent implements Component {

    public HashMap<String, AnimationComponent> animations = new HashMap<>();

    public String currentAnimation = null;

    // 默认动画
    public String defaultAnimation = "idle";

    public void play(String name) {

        if (currentAnimation != null && currentAnimation.equals(name))
            return;

        AnimationComponent anim = animations.get(name);

        if (anim == null)
            return;

        currentAnimation = name;

        anim.frameIndex = 0;
        anim.timer = 0;
    }

    public AnimationComponent getCurrentAnimation() {

        if (currentAnimation == null)
            return null;

        return animations.get(currentAnimation);
    }

}