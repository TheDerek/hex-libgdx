package ml.derek.gdx.turnbased.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import ml.derek.gdx.turnbased.models.Map;
import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.models.entities.Tree;
import ml.derek.gdx.turnbased.models.tiles.*;
import net.dermetfan.utils.math.Noise;

public class Generator
{
	public static Map ponds(int width, int height)
	{
		float[][] noise = Noise.diamondSquare(12, 1, 1, false, false, 1, 1);
		Tile[][] grid = new Tile[height][width];

		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[y].length; x++)
			{
				float r = noise[y][x];
				Gdx.app.log("gen", "[" + x + ", " + y + "]: " + noise[y][x]);
				if(r > 1f)
					grid[y][x] = new Mountain(x, y);
				else if(r > -0.7)
					grid[y][x] = new Plain(x, y);
				else
					grid[y][x] = new Ocean(x, y);
			}
		}

		Map map = new Map(grid);

		for (int y = 0; y < map.height; y++)
		{
			for (int x = 0; x < map.width; x++)
			{
				if(map.get(x, y).getClass() == Plain.class && !map.get(x, y).hasEntity())
				{
					if(MathUtils.random(20) == 0)
						map.setEntity(new Tree(), x, y);
				}
			}
		}

		return map;
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
