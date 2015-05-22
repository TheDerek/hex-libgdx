package ml.derek.gdx.turnbased.util;

import com.badlogic.gdx.Gdx;
import ml.derek.gdx.turnbased.models.Map;
import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.models.tiles.Mountain;
import ml.derek.gdx.turnbased.models.tiles.Ocean;
import ml.derek.gdx.turnbased.models.tiles.Plain;
import ml.derek.gdx.turnbased.models.tiles.Shrub;
import net.dermetfan.utils.math.Noise;

public class Generator
{
	public static Map ponds(int width, int height)
	{
		float[][] noise = Noise.diamondSquare(10, 1, 1, false, false, 1, 1);
		Tile[][] map = new Tile[height][width];

		for (int y = 0; y < map.length; y++)
		{
			for (int x = 0; x < map[y].length; x++)
			{
				float r = noise[y][x];
				Gdx.app.log("gen", "[" + x + ", " + y + "]: " + noise[y][x]);
				if(r > 0)
					map[y][x] = new Ocean(x, y);
				else if(r > -1)
					map[y][x] = new Plain(x, y);
				else
					map[y][x] = new Mountain(x, y);
			}
		}

		return new Map(map);
	}

	public static Map blank(int width, int height)
	{
		Tile[][] grid = new Tile[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				grid[y][x] = new Plain(new Vector3i(x, y, -x-y));
			}
		}

		return new Map(grid);
	}
}
