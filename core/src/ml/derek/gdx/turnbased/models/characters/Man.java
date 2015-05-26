package ml.derek.gdx.turnbased.models.characters;

import ml.derek.gdx.turnbased.models.*;

public class Man extends Creature
{
	public Man()
	{
		super();
	}

	@Override
	public int movesPerTurn()
	{
		return 1;
	}
}
