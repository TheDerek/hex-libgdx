package ml.derek.gdx.turnbased.models;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public class Hexagon
{
	public final Vector3i position;
	public final Vector2i position2;

	public Hexagon(Vector3i position)
	{
		this.position = position;

		if(position.x + position.y + position.z != 0 )
			throw new Error("Coordinates must add up to 0");

		this.position2 = new Vector2i(position.x, position.y);
	}

	public Hexagon(int x, int y, int z)
	{
		this(new Vector3i(x, y, z));
	}

	public Hexagon(Vector2i position2)
	{
		this.position2 = position2;
		this.position = new Vector3i(position2.x, position2.y, -position2.x -position2.y);
	}

	public Hexagon(int x, int y)
	{
		this(new Vector2i(x, y));
	}

	@Override
	public boolean equals(Object b)
	{
		return this.position.equals(((Hexagon)b).position);
	}

	@Override
	public int hashCode()
	{
		return position.hashCode();
	}
}
