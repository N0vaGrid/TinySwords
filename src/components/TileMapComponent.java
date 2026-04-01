package components;

import ecs.Component;
import tilemap.TileMap;

public class TileMapComponent implements Component {

    public TileMap map;

    public TileMapComponent(TileMap map) {

        this.map = map;
    }
}