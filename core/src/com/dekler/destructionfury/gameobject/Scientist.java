package com.dekler.destructionfury.gameobject;

import java.util.ArrayList;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Scientist extends Entity
{
	private Cooldown nextAttack, spawnBotCD, despawnCD;
	private float spawnX, spawnY;
	private int newX, newY;
	private boolean spawnBot = false;
	private boolean despawn = false;
	private ArrayList<GameObjectCooldown> bots;

	public Scientist(Level level)
	{
		super(level);
		this.setSize(0.9f, 0.9f);
		health = 3;
		speed = 2f;
		this.moveDown();
		nextAttack = new Cooldown(3f);
		spawnBotCD = new Cooldown(0.4f);
		despawnCD = new Cooldown(0.4f);
		bots = new ArrayList<GameObjectCooldown>();
	}

	public float getSpawnPercentage()
	{
		if (despawn)
			return 1f - despawnCD.percentageDone();
		return spawnCD.percentageDone();
	}

	public void update()
	{
		if (!despawn)
			super.update();
		else
		{
			despawnCD.update();
			if (despawnCD.cooldownOver())
			{
				despawn = false;
				spawned = false;
				setPosition(newX, newY);
			}
		}
		if (spawned)
		{
			nextAttack.update();
			spawnBotCD.update();
			if (nextAttack.cooldownOver() && !spawnBot)
			{
				spawnX = getX();
				spawnY = getY();
				spawnBot = true;
				spawnBotCD.start();
			}

			if (spawnBotCD.cooldownOver() && spawnBot)
			{
				spawnBot = false;
				attack();
			}

			for (int i = 0; i < bots.size(); i++)
			{
				GameObjectCooldown g = bots.get(i);
				if (g.getObject().getRemove())
					bots.remove(i--);
				else
				{
					g.update();
					if (g.cooldownOver())
					{
						g.getObject().damage(100);
					}
				}
			}
		}
	}

	@Override
	public void attack()
	{
		nextAttack.start();
		Robot robot = new Robot(level);
		robot.setPosition(spawnX, spawnY);

		int num = (int) (Math.random() * 4);
		if (num == 0)
			robot.moveLeft();
		else if (num == 1)
			robot.moveRight();
		else if (num == 2)
			robot.moveUp();
		else if (num == 3)
			robot.moveDown();

		GameObjectCooldown g = new GameObjectCooldown(robot, 20f);
		g.start();
		bots.add(g);
		level.addEntity(robot);
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

	public int getDamageTimer()
	{
		return 0;
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o instanceof Crate)
			onTileCollision(TileEnum.WALL, o.getX(), o.getY());
		if (o instanceof Knife)
		{
			if (spawned && !despawn)
			{
				super.damage(1);

				if (health > 0)
				{
					do
					{
						newX = (int) (Math.random() * level.getMap().getWidth());
						newY = (int) (Math.random() * level.getMap().getWidth());
					} while (level.getMap().getTile(newX, newY) != TileEnum.FLOOR);

					despawn = true;
					despawnCD.start();
				}
			}
		}
	}

}
