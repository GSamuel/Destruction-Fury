package com.dekler.destructionfury.gameobject;

import javafx.geometry.Rectangle2D;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Boss extends Entity
{
	private int nextAttackTimer;
	private int mouthOpenTimer;
	private Rectangle2D hitLeft, hitRight, hitUp, hitDown;

	public Boss(Level level)
	{
		super(level);
		this.setSize(1.9f, 1.9f);
		this.moveDown();
		health = 3;
		}
	
	private void updateHitBox()
	{
		hitLeft = new Rectangle2D(getX(), getY()+getHeight()*0.25f, getWidth() * 0.25f,
				getHeight()*0.5f);
		hitRight = new Rectangle2D(getX()+getWidth()*0.75f, getY()+getHeight()*0.25f, getWidth()*0.25f, getHeight()*0.5f);
	
		hitUp = new Rectangle2D(getX()+getWidth()*0.25f, getY()+getHeight()*0.75f, getWidth()*0.5f, getHeight()*0.25f);
		hitDown = new Rectangle2D(getX()+getWidth()*0.25f, getY(), getWidth()*0.5f, getHeight()*0.25f);
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
		Spit spit = new Spit(level);
		spit.setPosition(getX() + getWidth() * 0.5f - spit.getWidth() * 0.5f
				+ dir.x, getY() + getHeight() * 0.5f - spit.getHeight() * 0.5f
				+ dir.y);
		spit.setVelX(dir.x * 5);
		spit.setVelY(dir.y * 5);
		level.addObject(spit);
	}

	public void update()
	{
		if (mouthOpenTimer < 0)
			super.update();

		mouthOpenTimer--;
		nextAttackTimer--;

		if (nextAttackTimer < 0)
			attack();

		updateHitBox();
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
			if (g.isFlying() && mouthIsOpen())
			{
				boolean mouthHit = false;
				switch(direction)
				{
				case LEFT: 
					mouthHit = Collision.collision(g, hitLeft);
					break;
				case RIGHT: 
					mouthHit = Collision.collision(g, hitRight);
					break;
				case UP: 
					mouthHit = Collision.collision(g, hitUp);
					break;
				case DOWN: 
					mouthHit = Collision.collision(g, hitDown);
					break;
				}
				
				if (mouthHit)
				{
					o.remove();
					super.damage(1);
				}

			}
		}
	}

}
