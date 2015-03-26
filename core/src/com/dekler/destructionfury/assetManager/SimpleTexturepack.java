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
		sprites.put("floor", new Sprite(assetLoader.getRegion("sand")));
		sprites.put("wall", new Sprite(assetLoader.getRegion("stone")));
	}

	@Override
	public Sprite getSprite(TileEnum t)
	{
		return sprites.get(t.toString().toLowerCase());
	}

}
