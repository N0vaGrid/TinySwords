package input;

import java.util.HashSet;
import java.util.Set;

public class InputManager {

    private static Set<Integer> pressedKeys = new HashSet<>();

    public static void keyPressed(int key) {

        pressedKeys.add(key);
    }

    public static void keyReleased(int key) {

        pressedKeys.remove(key);
    }

    public static boolean isKeyDown(int key) {

        return pressedKeys.contains(key);
    }
}