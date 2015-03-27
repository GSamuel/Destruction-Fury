package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.map.TileEnum;

public class Player extends Entity
{
	
	public Player()
	{
		this.setSize(0.9f, 0.9f);
	}

	@Override
	public void onTileCollision(TileEnum t)
	{
		
	}
}
