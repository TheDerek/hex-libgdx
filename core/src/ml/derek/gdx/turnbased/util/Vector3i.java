package ml.derek.gdx.turnbased.util;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Derek Sewell on 27/12/14.
 * Copyright © 2014 Derek Sewell.
 * All rights reserved.
 */
public class Vector3i
{
	public int x;
	public int y;
	public int z;

	public Vector3i()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3i(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(float x, float y, float z)
	{
		this.x = (int) x;
		this.y = (int) y;
		this.z = (int) z;
	}


	public Vector3i add(Vector3i vector)
	{
		return new Vector3i(x + vector.x, y + vector.y, z + vector.z);
	}

	public Vector3i add(float x, float y, float z)
	{
		return new Vector3i(this.x + x, this.y + y, this.z + z);
	}


	public Vector3 toFloat()
	{
		return new Vector3(x, y, z);
	}

	public float len ()
	{
		return (float)Math.sqrt(x * x + y * y);
	}

	public Vector3i minus(Vector3i v) { return new Vector3i(x - v.x, y, z - v.y); }

	public Vector3i abs() { return new Vector3i(Math.abs(x), Math.abs(y), Math.abs(z)); }

	public Vector3i copy() { return new Vector3i(x, y, z); }

	@Override
	public boolean equals(Object b)
	{
		if(getClass() != b.getClass())
			return false;

		Vector3i a = ((Vector3i) b);
		return a.x == x && a.y == y && a.z == z;
	}

	@Override
	public int hashCode()
	{
		int prime = 31;
		return prime * x * y * z;
	}
}
