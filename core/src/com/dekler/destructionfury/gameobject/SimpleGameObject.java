package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;


public class SimpleGameObject extends GameObject
{

	public SimpleGameObject(Level level)
	{
		super(level);
	}

	@Override
	public void update()
	{
	}

	@Override
	public void onTileCollision(TileEnum t)
	{
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		
	}

	@Override
	public void damage(int damage)
	{
		health -= damage;
	}

}
