package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.dekler.destructionfury.level.Level;

public class LevelRenderer implements Disposable
{
	private Level level;

	// View Stuff
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;

	private ShapeRenderer shapeRenderer;

	public LevelRenderer(Stage stage, Level level)
	{
		this.stage = stage;
		this.level = level;

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 960, 640);

		stage.getViewport().setCamera(camera);
	}

	public void render()
	{
		MapRenderer.render(level, batch, camera, shapeRenderer);
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		batch.dispose();
		shapeRenderer.dispose();
	}
}
