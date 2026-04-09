package components;

import ecs.Component;
import java.awt.image.BufferedImage;

public class AnimationComponent implements Component {

    public BufferedImage[] frames;

    public int frameIndex = 0;

    public int frameDuration = 8;

    public int timer = 0;

    public boolean loop = true;

    // 是否为一次性动画（例如攻击）
    public boolean oneShot = false;

    public AnimationComponent(BufferedImage[] frames, int frameDuration) {
        this.frames = frames;
        this.frameDuration = frameDuration;
    }

    public AnimationComponent(BufferedImage[] frames, int frameDuration, boolean loop, boolean oneShot) {
        this.frames = frames;
        this.frameDuration = frameDuration;
        this.loop = loop;
        this.oneShot = oneShot;
    }

}