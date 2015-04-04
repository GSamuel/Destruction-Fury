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
		sprites.put("crate_floor", new Sprite(assetLoader.getRegion("crate_floor")));
		sprites.put("crate_target", new Sprite(assetLoader.getRegion("wood")));
		
		sprites.put("warp1", new Sprite(assetLoader.getRegion("warp1")));
		sprites.put("warp2", new Sprite(assetLoader.getRegion("warp2")));
		sprites.put("knife", new Sprite(assetLoader.getRegion("knife")));
		sprites.put("crate", new Sprite(assetLoader.getRegion("wooden_crate_front")));
		sprites.put("grenade", new Sprite(assetLoader.getRegion("grenade")));
		sprites.put("health", new Sprite(assetLoader.getRegion("health")));
		sprites.put("bomb_ui", new Sprite(assetLoader.getRegion("bomb_ui")));
		sprites.put("spit", new Sprite(assetLoader.getRegion("spit")));
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
