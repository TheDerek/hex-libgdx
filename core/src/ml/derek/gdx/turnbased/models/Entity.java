package ml.derek.gdx.turnbased.models;

import com.badlogic.gdx.math.Vector2;
import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public abstract class Entity
{
	public Tile lastTile = null;
	protected Tile tile;

	protected Tile getTile()
	{
		return tile;
	}

	protected void setTile(Tile tile)
	{
		this.tile = tile;
	}

	public Vector3i getPosition()
	{
		return getTile().position;
	}

	public Vector2i getPosition2()
	{
		return getTile().position2;
	}

	public Tile getLastTile()
	{
		return lastTile;
	}
}
