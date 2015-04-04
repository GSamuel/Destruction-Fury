package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Boss extends Entity
{

	public Boss(Level level)
	{
		super(level);
		this.setSize(1.9f, 1.9f);
		this.moveDown();
		health = 2;
	}

	public boolean mouthIsOpen()
	{
		return false;
	}

	@Override
	public void attack()
	{
		// TODO Auto-generated method stub

	}
	
	public void update()
	{
		super.update();
	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		if (t == TileEnum.WALL)
		{
			int num = (int) (Math.random() * 4);
			if (num == 0)
				this.moveLeft();
			else if (num == 1)
				this.moveRight();
			else if (num == 2)
				this.moveUp();
			else if (num == 3)
				this.moveDown();
		}
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Crate)
			onTileCollision(TileEnum.WALL, o.getX(), o.getY());
	}

}
