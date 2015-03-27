package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.map.TileEnum;

public class Robot extends Entity
{
	public Robot()
	{
		this.setSize(0.9f, 0.9f);
		this.moveDown();
	}

	@Override
	public void update()
	{
		super.update();
		if(Math.random()*100 <1)
			onTileCollision(TileEnum.WALL);
	}
	
	@Override
	public void onTileCollision(TileEnum t)
	{
		if(t == TileEnum.WALL)
		{
			int num = (int) (Math.random()*4);
			if(num ==0)
				this.moveLeft();
			else if(num ==1)
				this.moveRight();
			else if(num == 2)
				this.moveUp();
			else if (num == 3)
				this.moveDown();
		}
			
	}
}