package ml.derek.gdx.turnbased.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import ml.derek.gdx.turnbased.models.tiles.Plain;
import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

import java.util.Random;

public class Map
{
	private final Tile[][] grid;
	private final Array<Creature> creatures;
	public final int height;
	public final int width;

	public Map(Tile[][] grid)
	{
		this.grid = grid;
		width = grid[0].length;
		height = grid.length;
		creatures = new Array<Creature>();
	}

	public Tile get(int x, int y)
	{
		return grid[y][x];
	}

	/**
	 * Attempts to add the creature to the specified tile,
	 * removed the creature from it's last tile if it exists
	 * Returns false if the tile is occupied.
	 * @param creature The creature to add.
	 * @param x The X coordinate in the map to add the creature to.
	 * @param y The Y coordinate in the map to add the creature to.
	 * @return True is the operation was successful.
	 */
	public boolean setCreature(Creature creature, int x, int y)
	{
		if(grid[y][x].hasCreature())
			return false;

		if(grid[y][x].isSolid())
			return false;

		if(creature.getTile() != null)
			creature.getTile().creature = null;

		if(!creatures.contains(creature, false))
			creatures.add(creature);

		creature.oldTile = creature.getTile();
		creature.setTile(grid[y][x]);

		return grid[y][x].setCreature(creature);
	}

	/**
	 * Attempts to move the creature to (x2, y2). Fails if the space is already
	 * occupied or the creature attempts to move more than 1 space.
	 * @param creature The creature to move.
	 * @param x2 The X coordinate to move the creature to.
	 * @param y2 The Y coordinate to move the creature to.
	 * @return True if the operation was a success.
	 */
	public boolean moveCreature(Creature creature, int x2, int y2)
	{
		if(creature.getTile() == null)
			throw new Error("Cannot move a creature without a tile.");

		Vector2i oldPos = creature.getTile().position2;
		if((Math.abs(oldPos.x - x2) > 1) || (Math.abs(oldPos.y - y2) > 1))
		{
			Gdx.app.log("Failure", "Can only move a creature one space away" +
				"from its original position");
			return false;
		}

		return setCreature(creature, x2, y2);
	}

	public boolean moveCreatureRelative(Creature creature, int x2, int y2)
	{
		if(creature.getTile() == null)
			throw new Error("Cannot move a creature without a tile.");

		Vector2i oldPos = creature.getTile().position2;
		if(oldPos.y % 2 == 0 && y2 != 0 && x2 == -1)
			x2 = 0;
		if(oldPos.y % 2 != 0 && y2 != 0 && x2 == 1)
			x2 = 0;

		return moveCreature(creature, oldPos.x + x2, oldPos.y + y2);
	}

	public Tile randomTile()
	{
		return get(MathUtils.random(width - 1), MathUtils.random(height - 1));
	}
}
