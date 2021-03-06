package com.dekler.destructionfury;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.input.SimpleInputProcessor;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.level.LevelLoaderExporterInterface;
import com.dekler.destructionfury.level.PropertyManager;
import com.dekler.destructionfury.level.SimpleLevel;
import com.dekler.destructionfury.renderer.LevelRenderer;

public class DestructionFury extends Game
{
	private PropertyManager propManager;
	private LevelLoaderExporterInterface levelLoaderExporter;
	private boolean html;
	private boolean assetsLoaded = false;

	// assets
	private AssetManager assetManager;

	// model
	private Stage stage;
	private Level level;

	// view
	private LevelRenderer levelRenderer;

	// Input
	private SimpleInputProcessor iProcessor;

	public DestructionFury(LevelLoaderExporterInterface levelLoaderExporter,
			boolean html)
	{
		this.levelLoaderExporter = levelLoaderExporter;
		this.html = html;
	}

	@Override
	public void create()
	{
		// set resolution to default and set full-screen to true
		if (!html)
		{
			Gdx.graphics.setDisplayMode(
					Gdx.graphics.getDesktopDisplayMode().width,
					Gdx.graphics.getDesktopDisplayMode().height, true);
			Gdx.graphics.setVSync(true);
		}
		initProperties();

		// assets
		assetManager = new AssetManager();

		// model
		if (propManager.getIntegerProperty("load-level") != 0)
		{
			level = new Level();
			levelLoaderExporter.loadLevel(level, propManager);
		} else
		{
			level = new SimpleLevel();
			levelLoaderExporter.saveLevel(level, "stdSave.png", propManager);
		}

		// view
		stage = new Stage();
		levelRenderer = new LevelRenderer(stage, level, assetManager,
				propManager);

		// input
		iProcessor = new SimpleInputProcessor(level, propManager,
				levelLoaderExporter);

		InputMultiplexer im = new InputMultiplexer();
		if(!html)
			im.addProcessor(new GestureDetector(iProcessor));
		im.addProcessor(iProcessor);
		Gdx.input.setInputProcessor(im);
	}

	private void initProperties()
	{
		Properties properties = new Properties();
		properties.put("load-level", "0");
		properties.put("render-hitbox", "0");
		properties.put("level-name", "level.png");
		properties.put("player-color", "" + 0 + "," + 255 + "," + 0);
		properties.put("wall-color", "" + 100 + "," + 100 + "," + 100);
		properties.put("robot-color", "" + 255 + "," + 0 + "," + 0);
		properties.put("crate-color", "" + 255 + "," + 255 + "," + 0);
		properties.put("cratefloor-color", "" + 200 + "," + 140 + "," + 0);
		properties.put("cratetarget-color", "" + 255 + "," + 0 + "," + 255);
		properties.put("warppad-color", "" + 0 + "," + 0 + "," + 255);
		properties.put("boss1-color", "" + 127 + "," + 0 + "," + 0);
		properties.put("boss2-color", "" + 0 + "," + 127 + "," + 0);
		properties.put("boss3-color", "" + 0 + "," + 0 + "," + 127);
		properties.put("breakwall-color", "" + 0 + "," + 0 + "," + 0);

		propManager = new PropertyManager("settings.properties", properties,
				html);
		propManager.readPropertyFile();
		if (!html)
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
		if(!assetsLoaded)
		{
			assetManager.loadAssets();
			assetsLoaded = true;
		}
		if (level.reload())
			levelLoaderExporter.loadLevel(level, propManager);
		iProcessor.update();
		if (!level.paused())
			level.update();
		levelRenderer.render();
	}

	@Override
	public void dispose()
	{
		levelRenderer.dispose();
	}
}
