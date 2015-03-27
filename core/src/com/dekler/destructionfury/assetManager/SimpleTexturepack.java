package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dekler.destructionfury.map.TileEnum;

public class SimpleTexturepack implements TexturePack
{
	private HashMap<String, Sprite> sprites;
	
	public SimpleTexturepack(AssetLoader assetLoader)
	{
		sprites = new HashMap<String, Sprite>();
		sprites.put("floor", new Sprite(assetLoader.getRegion("floor")));
		sprites.put("wall", new Sprite(assetLoader.getRegion("stone")));
		sprites.put("warp1", new Sprite(assetLoader.getRegion("warp1")));
		sprites.put("warp2", new Sprite(assetLoader.getRegion("warp2")));
		sprites.put("knife", new Sprite(assetLoader.getRegion("knife")));
	}
	

	@Override
	public Sprite getSprite(TileEnum t)
	{
		return sprites.get(t.toString().toLowerCase());
	}


	@Override
	public Sprite getSprite(String spriteName)
	{
		return sprites.get(spriteName);
	}

}
