package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class AssetManager implements Disposable
{
    private TextureAtlas textureAtlas;
    private HashMap<String, AtlasRegion> map;
    
	public AssetManager()
	{
		map = new HashMap<String, TextureAtlas.AtlasRegion>();
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("tiles.pack"));

		map.put("water", textureAtlas.findRegion("tile-water"));
		map.put("stone", textureAtlas.findRegion("tile-stone"));
		map.put("grass", textureAtlas.findRegion("tile-grass"));
		map.put("sand", textureAtlas.findRegion("tile-sand"));
		map.put("wood", textureAtlas.findRegion("tile-wood"));
	}
	
	public TextureRegion getRegion(String textureName)
	{
		TextureRegion region = map.get(textureName);
		if(region == null)
			throw new NullPointerException();
		
		return region;
	}

	@Override
	public void dispose()
	{
		textureAtlas.dispose();
	}
}
