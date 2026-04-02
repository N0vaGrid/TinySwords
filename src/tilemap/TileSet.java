package tilemap;

import tilemap.Tile;

public class TileSet {

    private Tile[] tiles;

    public TileSet(int size) {
        tiles = new Tile[size];
    }

    public void setTile(int id, Tile tile) {
        tiles[id] = tile;
    }

    public Tile getTile(int id) {

        if (id < 0 || id >= tiles.length) {
            return null;
        }

        return tiles[id];
    }

}