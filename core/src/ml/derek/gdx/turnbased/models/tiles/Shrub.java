package ml.derek.gdx.turnbased.models.tiles;

import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public class Shrub extends Tile
{
	public Shrub(Vector3i position)
	{
		super(position);
	}

	public Shrub(int x, int y, int z)
	{
		super(x, y, z);
	}

	public Shrub(Vector2i position2)
	{
		super(position2);
	}

	public Shrub(int x, int y)
	{
		super(x, y);
	}

	@Override
	public boolean isSolid()
	{
		return true;
	}
}
