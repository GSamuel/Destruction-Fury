package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SimpleTexturepack implements TexturePack
{
	private HashMap<String, Sprite> sprites;
	
	public SimpleTexturepack(AssetManager assetManager)
	{
		sprites = new HashMap<String, Sprite>();
		sprites.put("floor", new Sprite(assetManager.getRegion("sand")));
		sprites.put("wall", new Sprite(assetManager.getRegion("stone")));
	}
	
	@Override
	public Sprite getSprite(String name)
	{
		return sprites.get(name);
	}

}
