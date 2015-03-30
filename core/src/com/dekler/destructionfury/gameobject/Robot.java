package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Robot extends Entity
{
	public Robot(Level level)
	{
		super(level);
		this.setSize(0.9f, 0.9f);
		this.moveDown();
		health = 2;
	}

	@Override
	public void update()
	{
		super.update();
	}

	@Override
	public void onTileCollision(TileEnum t)
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
		if (o instanceof Knife)
		{
			Vector2 diff = new Vector2(getX() - o.getX(), getY() - o.getY());
			diff.setLength(2f);
			setForceX(diff.x);
			setForceY(diff.y);
		}
	}

	@Override
	public void attack()
	{

	}

	
}
