package ml.derek.gdx.turnbased.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Derek Sewell on 27/12/14.
 * Copyright © 2014 Derek Sewell.
 * All rights reserved.
 */
public class Vector2i
{
	public int x;
	public int y;

	public Vector2i()
	{
		this.x = 0;
		this.y = 0;
	}

	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2i(float x, float y)
	{
		this.x = (int) x;
		this.y = (int) y;
	}


	public Vector2i add(Vector2i vector)
	{
		return new Vector2i(x + vector.x, y + vector.y);
	}

	public Vector2i add(float x, float y)
	{
		return new Vector2i(this.x + x, this.y + y);
	}


	public Vector2 toFloat()
	{
		return new Vector2(x, y);
	}

	public float len ()
	{
		return (float)Math.sqrt(x * x + y * y);
	}

	public Vector2i minus(Vector2i v) { return new Vector2i(x - v.x, y - v.y); }

	public Vector2i abs() { return new Vector2i(Math.abs(x), Math.abs(y)); }

	public Vector2i copy() { return new Vector2i(x, y); }
}
