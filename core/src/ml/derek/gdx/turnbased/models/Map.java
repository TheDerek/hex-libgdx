package ml.derek.gdx.turnbased.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import ml.derek.gdx.turnbased.util.Vector2i;

public class Map
{
	private static int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};

	private final Tile[][] grid;
	private final Array<Entity> entities;
	public final int height;
	public final int width;

	public Map(Tile[][] grid)
	{
		this.grid = grid;
		width = grid[0].length;
		height = grid.length;
		entities = new Array<Entity>();
	}

	public Tile get(int x, int y)
	{
		return grid[y][x];
	}

	/**
	 * Attempts to add the entity to the specified tile,
	 * removed the entity from it's last tile if it exists
	 * Returns false if the tile is occupied.
	 * @param entity The entity to add.
	 * @param x The X coordinate in the map to add the entity to.
	 * @param y The Y coordinate in the map to add the entity to.
	 * @return True is the operation was successful.
	 */
	public boolean setEntity(Entity entity, int x, int y)
	{
		if(x > width - 1 || y > height - 1 || x < 0 || y < 0)
			return false;

		if(grid[y][x].hasEntity())
			return false;

		if(grid[y][x].isSolid())
			return false;


		if(entity.getTile() != null)
		{
			entity.lastTile = entity.getTile();
			entity.getTile().entity = null;
		}

		if(!entities.contains(entity, false))
			entities.add(entity);


		entity.setTile(grid[y][x]);

		return grid[y][x].setEntity(entity);
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

		return setEntity(creature, x2, y2);
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
		if(oldPos.y % 2 != 0 && y2 < 0 && x2 == 0)
			x2 = 0;

		// The creature is moving vertically
		//if(x2 == 0 && y2 != 0)
		{
			//if(grid[y2][x2].hasEntity() || grid[y2][x2].isSolid())
			//	x2 = 1;
		}

		return moveCreature(creature, oldPos.x + x2, oldPos.y + y2);
	}

	public static Array<Tile> getSurroundings(Tile[][] map, int x, int y) {
		Array<Tile> res = new Array<Tile>();
		for (int[] direction : directions)
		{
			int cx = x + direction[0];
			int cy = y + direction[1];
			if(cy >=0 && cy < map.length)
				if(cx >= 0 && cx < map[cy].length)
					res.add(map[cy][cx]);
		}

		return res;
	}

	public Tile randomTile()
	{
		return get(MathUtils.random(width - 1), MathUtils.random(height - 1));
	}
}