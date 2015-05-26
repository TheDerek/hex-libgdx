package ml.derek.gdx.turnbased;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectMap;
import ml.derek.gdx.turnbased.models.Creature;
import ml.derek.gdx.turnbased.models.Entity;
import ml.derek.gdx.turnbased.models.Map;
import ml.derek.gdx.turnbased.models.Tile;
import ml.derek.gdx.turnbased.models.characters.Man;
import ml.derek.gdx.turnbased.models.entities.Tree;
import ml.derek.gdx.turnbased.models.tiles.*;

public class Renderer
{
	public static final float FIFTH = (float) Math.sqrt(3/2);
	public static final float HEIGHT = 1f;
	public static final float WIDTH = FIFTH * HEIGHT;

	public float lerp = 0.08f;
	public Creature focus;

	private ShapeRenderer shapeBatch;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ObjectMap<Class, TextureRegion> tileTextures;

	private Vector2 entityDrawingPos;
	private Vector2 tileDrawingPos;


	public Renderer(float width, float height)
	{
		camera = new OrthographicCamera(width, height);
		camera.translate(width/2f, height/2f);
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		shapeBatch = new ShapeRenderer();
		shapeBatch.setProjectionMatrix(camera.combined);

		shapeBatch.setColor(Color.GREEN);

		Texture tileset = new Texture("map.png");
		tileTextures = new ObjectMap<Class, TextureRegion>();
		tileTextures.put(Mountain.class, new TextureRegion(tileset, 0, 0, 15, 26));
		tileTextures.put(Plain.class, new TextureRegion(tileset, 15, 0, 15, 26));
		tileTextures.put(Ocean.class, new TextureRegion(tileset, 30, 0, 15, 26));
		tileTextures.put(Man.class, new TextureRegion(tileset, 45, 0, 15, 26));
		tileTextures.put(Shrub.class, new TextureRegion(tileset, 60, 0, 15, 26));
		tileTextures.put(Beach.class, new TextureRegion(tileset, 75, 0, 15, 26));
		tileTextures.put(Tree.class, new TextureRegion(tileset, 90, 0, 15, 26));

		tileDrawingPos = new Vector2();
	}

	public void render(Map map, int framesPerStep, int currentFrame)
	{
		Gdx.gl.glClearColor(0.4f, 0.4f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//shapeBatch.begin(ShapeRenderer.ShapeType.Line);

		lerpCamera(focus.getPosition2().toFloat());

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int y = map.height - 1; y >= 0; y--)
		{
			for (int x = map.width - 1; x >= 0; x--)
			{
				Tile tile = map.get(x, y);
				if(y % 2 != 0)
					tileDrawingPos.set(x, y);
				else
					tileDrawingPos.set(x + 0.5f, y);

				entityDrawingPos = tileDrawingPos.cpy();

				toHex(tileDrawingPos);
				batch.draw(tileTextures.get(tile.getClass()),
						tileDrawingPos.x, tileDrawingPos.y, WIDTH, HEIGHT * 2);

				if(tile.hasEntity())
				{
					if(tile.getEntity().getLastTile() != null)
					{
						Entity entity = tile.getEntity();
						Vector2 position = tile.position2f;
						Vector2 lastPosition = entity.getLastTile().position2f;
						float t = (float)currentFrame / (float)framesPerStep;
						Gdx.app.log("Render", Float.toString(t));
						entityDrawingPos.x = lastPosition.x + (position.x - lastPosition.x) * t;
						entityDrawingPos.y = lastPosition.y + (position.y - lastPosition.y) * t;
						lerpCamera(entityDrawingPos);
					}

					toHex(entityDrawingPos);
					batch.draw(tileTextures.get(tile.getEntity().getClass()),
							entityDrawingPos.x, entityDrawingPos.y, WIDTH, HEIGHT * 2);
				}
			}
		}
		batch.end();
		//shapeBatch.end();
	}

	public float pixelWidth()
	{
		return 2 * camera.viewportWidth / (float) Gdx.graphics.getWidth();
	}

	public float pixelHeight()
	{
		return 2 * camera.viewportHeight / (float) Gdx.graphics.getHeight();
	}

	public void toHex(Vector2 vector)
	{
		vector.x *= 1 - pixelWidth() * 2f;
		vector.y *= (3f/4f);
		vector.y *= 1f - pixelHeight() * 2f;
	}

	public void toHex(Vector3 vector)
	{
		vector.x *= 1 - pixelWidth() * 2f;
		vector.y *= (3f/4f);
		vector.y *= 1f - pixelHeight() * 2f;
	}

	public void lerpCamera(Vector2 focus)
	{
		if(focus != null)
		{
			Vector3 position = camera.position;
			position.x += (focus.x - position.x) * lerp;
			position.y += (focus.y * 3f/4f  - position.y) * lerp;
			camera.update();
		}
	}
}
