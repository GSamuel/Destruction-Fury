package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Crate extends GameObject
{

	public Crate(Level level)
	{
		super(level);
	}

	@Override
	public void update()
	{

	}
	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Player)
		{
			Collision.collisionV2(this, o);
		}
	}

	@Override
	public void damage(int damage)
	{

	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		// TODO Auto-generated method stub
		
	}

}
