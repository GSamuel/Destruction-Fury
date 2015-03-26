package com.dekler.destructionfury;

import com.badlogic.gdx.Game;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.input.SimpleInputProcessor;
import com.dekler.destructionfury.map.RandomTiledMap;
import com.dekler.destructionfury.map.TiledMap;
import com.dekler.destructionfury.renderer.MapRenderer;

public class DestructionFury extends Game
{
	//assets
	private AssetManager assetManager;
	
	//model
	private TiledMap map;
	private MapRenderer mapRenderer;
	
	//Input
	private SimpleInputProcessor iProcessor;

	@Override
	public void create()
	{
		assetManager = new AssetManager();
		map = new RandomTiledMap(10, 10);
		mapRenderer = new MapRenderer(map);
	}

	@Override
	public void render()
	{
		mapRenderer.render();
	}
	
	@Override
	public void dispose()
	{
		mapRenderer.dispose();
	}
}
