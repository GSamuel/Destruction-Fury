package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends GameObject
{
	protected Vector2 vel;
	protected float speed = 2;
	
	public Entity()
	{
		this.vel = new Vector2();
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
	}

	public void moveRight()
	{
		vel.x = speed;
	}

	public void stopMoveX()
	{
		vel.x = 0;
	}

	public void moveUp()
	{
		vel.y = -speed;
	}

	public void moveDown()
	{
		vel.y = speed;
	}

	public void stopMoveY()
	{
		vel.y = 0;
	}
	
	public void update()
	{
		pos.add(vel.x*Gdx.graphics.getDeltaTime(), vel.y*Gdx.graphics.getDeltaTime());
	}
}
