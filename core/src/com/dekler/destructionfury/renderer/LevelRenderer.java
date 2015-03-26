package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.level.Level;

public class LevelRenderer implements Disposable
{
	public static final int TILE_SIZE = 64;
	
	private Level level;

	// View Stuff
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	
	private AssetManager assetManager;

	public LevelRenderer(Stage stage, Level level, AssetManager assetManager)
	{
		this.stage = stage;
		this.level = level;
		this.assetManager = assetManager;

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 960, 640);

		stage.getViewport().setCamera(camera);
	}

	public void render()
	{
		GameObject p = level.getPlayer();
		camera.position.set(p.getX() * TILE_SIZE, p.getY()*TILE_SIZE, 0);
		camera.update();
		MapRenderer.render(level, batch, camera, assetManager.getTexturePack(), TILE_SIZE);
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		batch.dispose();
	}
}
