package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject
{
	protected Vector2 size; 
	protected Vector2 pos;
	protected Vector2 vel;
	
	protected boolean movable;
	
	protected int health;

	public GameObject()
	{
		this.size = new Vector2();
		this.pos = new Vector2();
		this.vel = new Vector2();
	}

	public float getX()
	{
		return pos.x;
	}
	
	public int getIntX()
	{
		return (int)pos.x;
	}

	public float getY()
	{
		return pos.y;
	}
	
	public int getIntY()
	{
		return (int) pos.y;
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

	public void setPosition(float x, float y)
	{
		pos.set(x, y);
	}

	public void changePosition(float x, float y)
	{
		pos.add(x, y);
	}
	
	public void update()
	{
		pos.add(vel.x*Gdx.graphics.getDeltaTime(), vel.y*Gdx.graphics.getDeltaTime());
	}
}
