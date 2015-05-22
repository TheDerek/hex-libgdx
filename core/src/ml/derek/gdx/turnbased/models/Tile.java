package ml.derek.gdx.turnbased.models;

import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public abstract class Tile extends Hexagon
{
	protected Creature creature = null;

	public Tile(Vector3i position)
	{
		super(position);
	}

	public Tile(int x, int y, int z)
	{
		super(x, y, z);
	}

	public Tile(Vector2i position2)
	{
		super(position2);
	}

	public Tile(int x, int y)
	{
		super(x, y);
	}

	public abstract boolean isSolid();

	public boolean hasCreature()
	{
		return creature != null;
	}

	public Creature getCreature()
	{
		return creature;
	}

	protected boolean setCreature(Creature creature)
	{
		if(this.creature != null)
			return false;

		this.creature = creature;
		return true;
	}
}
