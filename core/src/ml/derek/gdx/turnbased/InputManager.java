package ml.derek.gdx.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ml.derek.gdx.turnbased.models.Creature;
import ml.derek.gdx.turnbased.models.Map;

public class InputManager
{
	private Creature creature;
	public InputManager(Creature creature)
	{
		this.creature = creature;
	}
	private float currentFrame = 0;

	public void update(Map map, int framesPerStep)
	{
		if(currentFrame < framesPerStep)
			currentFrame++;
		else
		{
			currentFrame = 0;
			step(map);
		}
	}

	private void step(Map map)
	{
		creatureMovement(creature, map);
	}

	private void creatureMovement(Creature creature, Map map)
	{
		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			map.moveCreatureRelative(creature, 1, 0);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			map.moveCreatureRelative(creature, -1, 0);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.Q))
		{
			map.moveCreatureRelative(creature, -1, 1);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.E))
		{
			map.moveCreatureRelative(creature, 1, 1);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.X))
		{
			map.moveCreatureRelative(creature, 1, -1);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.Z))
		{
			map.moveCreatureRelative(creature, -1, -1);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.W))
		{
			map.moveCreatureRelative(creature, 0, 1);
		} else

		if(Gdx.input.isKeyPressed(Input.Keys.S))
		{
			map.moveCreatureRelative(creature, 0, -1);
		}
	}
}
