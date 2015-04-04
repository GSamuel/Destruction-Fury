package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Boss extends Entity
{
	private int nextAttackTimer;
	private int mouthOpenTimer;

	public Boss(Level level)
	{
		super(level);
		this.setSize(1.9f, 1.9f);
		this.moveDown();
		health = 2;
	}

	public boolean mouthIsOpen()
	{
		return mouthOpenTimer > 0;
	}

	@Override
	public void attack()
	{
		nextAttackTimer = 300;
		mouthOpenTimer = 60;

		Vector2 dir = direction.getDirectionVector();
		Grenade grenade = new Grenade(level);
		grenade.setPosition(getX() + getWidth() * 0.5f - grenade.getWidth()
				* 0.5f + dir.x,
				getY() + getHeight() * 0.5f - grenade.getHeight() * 0.5f
						+ dir.y);
		grenade.setVelX(dir.x * 5);
		grenade.setVelY(dir.y * 5);
		level.addObject(grenade);
	}

	public void update()
	{
		if (mouthOpenTimer < 0)
			super.update();

		mouthOpenTimer--;
		nextAttackTimer--;

		if (nextAttackTimer < 0)
			attack();
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
	public void damage(int damage)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Crate)
			onTileCollision(TileEnum.WALL, o.getX(), o.getY());
		if (o instanceof Grenade)
		{
			Grenade g = (Grenade) o;
			if (g.isFlying())
			{
				o.remove();
				super.damage(1);
			}
		}
	}

}
