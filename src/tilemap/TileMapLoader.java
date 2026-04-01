package tilemap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TileMapLoader {

    public static TileMap load(String path, TileSet tileSet) {

        List<int[]> rows = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            TileMapLoader.class
                            .getClassLoader()
                            .getResourceAsStream(path)
                    )
            );

            String line;

            while((line = reader.readLine()) != null) {

                String[] tokens = line.split(" ");

                int[] row = new int[tokens.length];

                for(int i=0;i<tokens.length;i++) {

                    row[i] = Integer.parseInt(tokens[i]);
                }

                rows.add(row);
            }

        } catch(Exception e) {

            throw new RuntimeException(e);
        }

        int[][] tiles = rows.toArray(new int[0][]);

        return new TileMap(tiles, tileSet);
    }

}