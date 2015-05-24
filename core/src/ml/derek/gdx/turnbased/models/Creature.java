package ml.derek.gdx.turnbased.models;

import ml.derek.gdx.turnbased.util.Vector2i;
import ml.derek.gdx.turnbased.util.Vector3i;

public abstract class Creature extends Entity
{
	public abstract int movesPerTurn();
}
