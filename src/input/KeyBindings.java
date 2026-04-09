package input;

import engine.ConfigManager;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyBindings {

    private static Map<String, Integer> bindings = new HashMap<>();

    public static void load() {

        bindings.put("move.up",
                getKey(ConfigManager.get("move.up")));

        bindings.put("move.down",
                getKey(ConfigManager.get("move.down")));

        bindings.put("move.left",
                getKey(ConfigManager.get("move.left")));

        bindings.put("move.right",
                getKey(ConfigManager.get("move.right")));

        bindings.put("attack",
                getKey(ConfigManager.get("attack")));

        bindings.put("debug.toggle",KeyEvent.VK_F1);
    }

    private static int getKey(String key) {

        switch (key.toUpperCase()) {

            case "W": return KeyEvent.VK_W;
            case "A": return KeyEvent.VK_A;
            case "S": return KeyEvent.VK_S;
            case "D": return KeyEvent.VK_D;
            case "SPACE": return KeyEvent.VK_SPACE;
        }

        return KeyEvent.VK_UNDEFINED;
    }

    public static int get(String action) {

        Integer key = bindings.get(action);

        if(key == null) {

            throw new RuntimeException(
                    "Key binding not found: " + action
            );
        }

        return key;
    }
}