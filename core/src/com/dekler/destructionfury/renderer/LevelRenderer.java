package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.level.PropertyManager;

public class LevelRenderer implements Disposable
{
	public static final int TILE_SIZE = 128;

	private Level level;

	// View Stuff
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;

	private Vector2 minCamPos;
	private Vector2 maxCamPos;
	private AssetManager assetManager;

	private static ShapeRenderer shapeRenderer = new ShapeRenderer();
	private PropertyManager propManager;

	public LevelRenderer(Stage stage, Level level, AssetManager assetManager, PropertyManager propManager)
	{
		this.stage = stage;
		this.level = level;
		this.assetManager = assetManager;
		this.propManager = propManager;

		minCamPos = new Vector2(Gdx.graphics.getWidth() * 0.5f,
				Gdx.graphics.getHeight() * 0.5f);
		maxCamPos = new Vector2(level.getMap().getWidth() * TILE_SIZE
				- Gdx.graphics.getWidth() * 0.5f, level.getMap().getHeight()
				* TILE_SIZE - Gdx.graphics.getHeight() * 0.5f);

		batch = (SpriteBatch) stage.getBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 960, 640);

		stage.getViewport().setCamera(camera);
	}

	public void render()
	{
		GameObject p = level.getPlayer();
		camera.position.set(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, 0);

		if (camera.position.x < minCamPos.x)
			camera.position.x = minCamPos.x;
		else if (camera.position.x > maxCamPos.x)
			camera.position.x = maxCamPos.x;

		if (camera.position.y < minCamPos.y)
			camera.position.y = minCamPos.y;
		else if (camera.position.y > maxCamPos.y)
			camera.position.y = maxCamPos.y;

		camera.update();

		MapRenderer.render(level, batch, camera, assetManager.getTexturePack(),
				TILE_SIZE);
		GameObjectRenderer
				.render(level, batch, camera, assetManager, TILE_SIZE);
		EntityRenderer.render(level, batch, camera, assetManager, TILE_SIZE);

		EffectRenderer.render(level, batch, camera, assetManager, TILE_SIZE);

		if(propManager.getIntegerProperty("render-hitbox") != 0)
		HitBoxRenderer.render(level, batch, camera, assetManager, TILE_SIZE);

		UIRenderer.render(level, batch, camera, assetManager, TILE_SIZE);

		// when paused
		if (level.paused())
		{
			shapeRenderer.setProjectionMatrix(camera.combined);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			batch.begin();
			shapeRenderer.setColor(new Color(0, 0, 0, 0.7f));
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.rect(camera.position.x-Gdx.graphics.getWidth()*0.5f, camera.position.y - Gdx.graphics.getHeight()*0.5f, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			shapeRenderer.end();
			batch.end();
		}
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		batch.dispose();
	}
}
