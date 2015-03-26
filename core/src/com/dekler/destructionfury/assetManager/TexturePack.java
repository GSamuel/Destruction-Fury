package com.dekler.destructionfury.assetManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dekler.destructionfury.map.Tile;

public interface TexturePack
{
	public abstract Sprite getSprite(String s);
	public abstract Sprite getSprite(Tile t);
}
