package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class WarpPad extends GameObject
{

	private boolean active;

	public WarpPad(Level level)
	{
		super(level);
		setSize(2.5f, 1.2f);
		active = false;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	@Override
	public void update()
	{
		active = false;
	}

	@Override
	public void onTileCollision(TileEnum t)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		active = true;
	}
}
