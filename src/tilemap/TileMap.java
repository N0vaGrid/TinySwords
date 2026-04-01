package tilemap;

import core.GameConstants;

public class TileMap {

    public int[][] tiles;

    public int width;
    public int height;

    public int tileSize = 64;

    public TileSet tileSet;

    public TileMap(int[][] tiles, TileSet tileSet) {

        this.tiles = tiles;
        this.height = tiles.length;
        this.width = tiles[0].length;

        this.tileSet = tileSet;
    }
}