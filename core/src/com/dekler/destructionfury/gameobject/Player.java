package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Player extends Entity
{

	private int attackTime;
	private int explosionTime;

	public Player(Level level)
	{
		super(level);
		this.setSize(0.9f, 0.9f);
		this.speed = 5f;
	}

	@Override
	public void onTileCollision(TileEnum t)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Robot)
		{
			Vector2 diff = new Vector2(getX() - o.getX(), getY() - o.getY());
			diff.setLength(2.6f);
			setForceX(diff.x);
			setForceY(diff.y);

			/*
			if (explosionTime <= 0)
			{
				level.addEffect(new Explosion(pos.x, pos.y));
				explosionTime = 30;
			}*/
		}
	}

	@Override
	public void update()
	{
		super.update();
		attackTime--;
		explosionTime--;
	}

	@Override
	public void attack()
	{
		if (attackTime <= 0)
		{
			Knife knife = new Knife(level);
			knife.setPosition(getX(), getY());
			level.addHurtable(knife);
			attackTime = 30;
		}
	}

	@Override
	public void damage(int damage)
	{
		health -= damage;
	}
}
