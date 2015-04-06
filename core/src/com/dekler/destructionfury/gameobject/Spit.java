package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Spit extends Entity
{

	public Spit(Level level)
	{
		super(level);
	}

	@Override
	public void update()
	{
		spawnCD.stop();
		super.update();
	}
	
	@Override
	public void attack()
	{
		
	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		if(t == TileEnum.WALL)
			remove();
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if(o instanceof Player)
			o.damage(1);
	}
	
}
