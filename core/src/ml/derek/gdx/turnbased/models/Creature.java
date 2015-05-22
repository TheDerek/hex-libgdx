package ml.derek.gdx.turnbased.models;

import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public abstract class Creature
{
	public abstract int movesPerTurn();
	protected Tile tile;
	protected Tile oldTile;

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
}
