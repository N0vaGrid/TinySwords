package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {

    private static Map<String, BufferedImage> textures = new HashMap<>();

    public static BufferedImage getTexture(String path) {

        if(textures.containsKey(path)) {

            return textures.get(path);
        }

        try {

            BufferedImage image =
                    ImageIO.read(
                            TextureManager.class
                            .getClassLoader()
                            .getResourceAsStream(path)
                    );

            textures.put(path, image);

            return image;

        } catch (Exception e) {

            throw new RuntimeException("Failed to load texture: " + path);
        }
    }
}