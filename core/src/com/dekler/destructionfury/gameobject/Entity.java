package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends GameObject
{
	protected Vector2 vel;
	protected float speed = 2;
	protected float time = 0f;	
	private Direction direction;
	
	public Entity()
	{
		this.vel = new Vector2();
		direction = Direction.DOWN;
	}
	
	public float getVelX()
	{
		return vel.x;
	}
	
	public float getVelY()
	{
		return vel.y;
	}
	
	public void setVelX(float velX)
	{
		this.vel.x = velX;
	}
	
	public void setVelY(float velY)
	{
		this.vel.y = velY;
	}
	
	public void moveLeft()
	{
		vel.x = -speed;
		direction = Direction.LEFT;
		stopMoveY();
	}

	public void moveRight()
	{
		vel.x = speed;
		direction = Direction.RIGHT;
		stopMoveY();
	}

	public void stopMoveX()
	{
		vel.x = 0;
	}

	public void moveUp()
	{
		vel.y = speed;
		direction = Direction.UP;
		stopMoveX();
	}

	public void moveDown()
	{
		vel.y = -speed;
		direction = Direction.DOWN;
		stopMoveX();
	}

	public void stopMoveY()
	{
		vel.y = 0;
	}
	
	public float getTime()
	{
		if(vel.x == 0f && vel.y == 0f)
			return 0f;
		return time;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void update()
	{
		pos.add(vel.x*Gdx.graphics.getDeltaTime(), vel.y*Gdx.graphics.getDeltaTime());
		time += Gdx.graphics.getDeltaTime();
	}
}
