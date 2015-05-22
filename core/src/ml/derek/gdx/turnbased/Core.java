package ml.derek.gdx.turnbased;

import com.badlogic.gdx.ApplicationAdapter;
import ml.derek.gdx.turnbased.models.Creature;
import ml.derek.gdx.turnbased.models.Map;
import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.models.characters.Man;
import ml.derek.gdx.turnbased.util.Generator;

public class Core extends ApplicationAdapter {
	InputManager manager;
	Renderer renderer;
	Map map;
	
	@Override
	public void create ()
	{
		int size = 100;
		float scale = 1/5f;
		map = Generator.ponds(40, 40);

		renderer = new Renderer(size * scale, size * scale);

		Creature man = new Man();

		Tile tile = map.randomTile();
		do
		{
			tile = map.randomTile();

		} while(tile.isSolid());

		map.setCreature(man, tile.position.x, tile.position.y);
		renderer.focus = man;

		manager = new InputManager(man);
	}

	@Override
	public void render ()
	{
		manager.update(map);
		renderer.render(map);
	}
}
