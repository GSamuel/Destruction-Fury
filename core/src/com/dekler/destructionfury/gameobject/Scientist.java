package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Scientist extends Entity
{
	private Cooldown nextAttackCD;
	private Cooldown stuckCD;
	private boolean charging = false;
	private boolean stuck = false;

	public Scientist(Level level)
	{
		super(level);
		this.setSize(1.9f, 1.3f);
		this.moveUp();
		health = 3;
		nextAttackCD = new Cooldown(6f);
		stuckCD = new Cooldown(2f);
	}

	@Override
	public float getX()
	{
		if(direction == Direction.DOWN || direction == Direction.UP)
			return pos.x + 0.45f;
		else
			return pos.x;
	}

	public boolean isCharging()
	{
		return charging;
	}

	public boolean isStuck()
	{
		return stuck;
	}

	public void update()
	{
		if (!stuck)
			super.update();
		else
			time += Gdx.graphics.getDeltaTime();

		if (!charging && !stuck)
			nextAttackCD.update();
		if (nextAttackCD.cooldownOver())
			attack();

		if (stuck)
			stuckCD.update();
		if (stuckCD.cooldownOver())
			stuck = false;

		if (charging && !stuck)
			pos.add(vel.x * Gdx.graphics.getDeltaTime() * 2f, vel.y
					* Gdx.graphics.getDeltaTime() * 2f);

		if (direction == Direction.DOWN || direction == Direction.UP)
			this.setSize(1.2f, 1.5f);
		else
			this.setSize(1.9f, 1.3f);
		

	}

	@Override
	public void attack()
	{
		nextAttackCD.start();
		charging = true;

	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		if (t == TileEnum.WALL)
		{
			if (charging)
			{
				charging = false;
				stuck = true;
				stuckCD.start();
			} else if (!stuck)
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
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Crate)
			onTileCollision(TileEnum.WALL, o.getX(), o.getY());
		if (o instanceof Knife)
		{
			if (stuck)
				super.damage(1);
		}
	}

}
