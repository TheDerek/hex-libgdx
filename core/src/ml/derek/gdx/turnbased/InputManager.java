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

	public void update(Map map)
	{
		if(Gdx.input.isKeyJustPressed(Input.Keys.D))
		{
			map.moveCreatureRelative(creature, 1, 0);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.A))
		{
			map.moveCreatureRelative(creature, -1, 0);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
		{
			map.moveCreatureRelative(creature, -1, 1);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.E))
		{
			map.moveCreatureRelative(creature, 1, 1);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.X))
		{
			map.moveCreatureRelative(creature, 1, -1);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.Z))
		{
			map.moveCreatureRelative(creature, -1, -1);
		}
	}
}
