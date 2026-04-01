package tilemap;

import java.awt.image.BufferedImage;

public class TileSet {

    private BufferedImage[] tiles;

    public TileSet(BufferedImage texture, int tileSize) {

        int columns = texture.getWidth() / tileSize;
        int rows = texture.getHeight() / tileSize;

        tiles = new BufferedImage[columns * rows];

        int index = 0;

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {

                tiles[index++] = texture.getSubimage(
                        x * tileSize,
                        y * tileSize,
                        tileSize,
                        tileSize
                );
            }
        }
    }

    public BufferedImage getTile(int index) {

        if(index < 0 || index >= tiles.length) {
            return null;
        }

        return tiles[index];
    }
}