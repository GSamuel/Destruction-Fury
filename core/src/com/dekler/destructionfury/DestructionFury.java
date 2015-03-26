package com.dekler.destructionfury;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.assetManager.SimpleTexturepack;
import com.dekler.destructionfury.assetManager.TexturePack;
import com.dekler.destructionfury.input.SimpleInputProcessor;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.renderer.LevelRenderer;

public class DestructionFury extends Game
{
	//assets
	private AssetManager assetManager;
	private TexturePack texturePack;
	
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
		texturePack = new SimpleTexturepack(assetManager);
		
		//model
		level = new Level();
		//view
		stage = new Stage();
		levelRenderer = new LevelRenderer(stage, level);
		
		//input
		iProcessor = new SimpleInputProcessor(level);
		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(new GestureDetector(iProcessor));
		im.addProcessor(iProcessor);
		Gdx.input.setInputProcessor(im);
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
