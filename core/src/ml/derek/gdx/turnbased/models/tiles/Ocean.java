package ml.derek.gdx.turnbased.models.tiles;

import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public class Ocean extends Tile
{
	public Ocean(Vector3i position)
	{
		super(position);
	}

	public Ocean(int x, int y, int z)
	{
		super(x, y, z);
	}

	public Ocean(Vector2i position2)
	{
		super(position2);
	}

	public Ocean(int x, int y)
	{
		super(x, y);
	}

	@Override
	public boolean isSolid()
	{
		return false;
	}
}
