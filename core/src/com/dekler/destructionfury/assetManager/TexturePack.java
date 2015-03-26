package com.dekler.destructionfury.assetManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dekler.destructionfury.map.TileEnum;

public interface TexturePack
{
	public abstract Sprite getSprite(TileEnum t);
}
