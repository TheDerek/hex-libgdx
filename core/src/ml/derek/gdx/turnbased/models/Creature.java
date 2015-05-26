package ml.derek.gdx.turnbased.models;

public abstract class Creature extends Entity
{
	public Creature()
	{
		super();
	}

	public abstract int movesPerTurn();
}
