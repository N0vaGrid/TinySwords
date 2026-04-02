package tilemap;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage texture;
    public boolean solid;
    public TileType type;

    public Tile(BufferedImage texture, boolean solid, TileType type) {
        this.texture = texture;
        this.solid = solid;
        this.type = type;
    }

}