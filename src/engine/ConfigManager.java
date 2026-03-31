package engine;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    public static void load(String path) {

        try {

            InputStream stream =
                    ConfigManager.class.getClassLoader().getResourceAsStream(path);

            properties.load(stream);

        } catch (Exception e) {

            throw new RuntimeException("Failed to load config: " + path);
        }
    }

    public static String get(String key) {

        return properties.getProperty(key);
    }

    public static int getInt(String key) {

        return Integer.parseInt(properties.getProperty(key));
    }

    public static double getDouble(String key) {

        return Double.parseDouble(properties.getProperty(key));
    }

}