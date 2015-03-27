package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Knife extends GameObject
{
	private int damage;
	private int time;
	public Knife(Level level)
	{
		super(level);
		damage = 1;
		time = 6;
	}

	@Override
	public void update()
	{
		time --;
		if(time <=0)
			remove();
	}

	@Override
	public void onTileCollision(TileEnum t)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if(o instanceof Robot)
			o.damage(damage);
	}

	@Override
	public void damage(int damage)
	{
		
	}
	
}
