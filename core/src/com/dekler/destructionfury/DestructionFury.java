package com.dekler.destructionfury;

import java.util.Properties;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.input.SimpleInputProcessor;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.level.LevelLoaderExporter;
import com.dekler.destructionfury.level.PropertyManager;
import com.dekler.destructionfury.level.SimpleLevel;
import com.dekler.destructionfury.renderer.LevelRenderer;

public class DestructionFury extends Game
{
	private PropertyManager propManager;

	// assets
	private AssetManager assetManager;

	// model
	private Stage stage;
	private Level level;

	// view
	private LevelRenderer levelRenderer;

	// Input
	private SimpleInputProcessor iProcessor;

	@Override
	public void create()
	{
		// set resolution to default and set full-screen to true
		Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
				Gdx.graphics.getDesktopDisplayMode().height, true);
		Gdx.graphics.setVSync(true);

		initProperties();

		// assets
		assetManager = new AssetManager();

		// model
		if (propManager.getIntegerProperty("load-level") != 0)
		{
			level = LevelLoaderExporter.loadLevel(propManager);
		} else
		{
			level = new SimpleLevel();
			LevelLoaderExporter.saveLevel(level, "stdSave.png", propManager);
		}

		// view
		stage = new Stage();
		levelRenderer = new LevelRenderer(stage, level, assetManager);

		// input
		iProcessor = new SimpleInputProcessor(level);

		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(new GestureDetector(iProcessor));
		im.addProcessor(iProcessor);
		Gdx.input.setInputProcessor(im);
	}

	private void initProperties()
	{
		Properties properties = new Properties();
		properties.put("load-level", "0");
		properties.put("level-name", "level.png");
		properties.put("player-color", "" + 0 + "," + 255 + "," + 0);
		properties.put("wall-color", "" + 100 + "," + 100 + "," + 100);
		properties.put("robot-color", "" + 255 + "," + 0 + "," + 0);
		properties.put("crate-color", "" + 255 + "," + 255 + "," + 0);

		propManager = new PropertyManager("settings.properties", properties);
		propManager.readPropertyFile();
		propManager.writePropertyFile();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
		stage.getCamera().update();
	}

	@Override
	public void render()
	{
		iProcessor.update();
		level.update();
		levelRenderer.render();
	}

	@Override
	public void dispose()
	{
		levelRenderer.dispose();
	}
}
