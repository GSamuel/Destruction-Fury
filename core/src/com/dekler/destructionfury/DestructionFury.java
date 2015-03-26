package com.dekler.destructionfury;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.input.SimpleInputProcessor;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TiledMap;
import com.dekler.destructionfury.renderer.LevelRenderer;
import com.dekler.destructionfury.renderer.MapRenderer;

public class DestructionFury extends Game
{
	//assets
	private AssetManager assetManager;
	
	//model
	private Stage stage;
	private Level level;
	
	//view
	private LevelRenderer levelRenderer;
	
	//Input
	private SimpleInputProcessor iProcessor;

	@Override
	public void create()
	{
		//assets
		assetManager = new AssetManager();
		//model
		level = new Level();
		//view
		stage = new Stage();
		levelRenderer = new LevelRenderer(stage, level);
		//input
		iProcessor = new SimpleInputProcessor(stage, level);
	}

	@Override
	public void render()
	{
		levelRenderer.render();
	}
	
	@Override
	public void dispose()
	{
		levelRenderer.dispose();
	}
}
