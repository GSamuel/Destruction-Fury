package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject
{
	protected Vector2 size; 
	protected Vector2 pos;
	
	protected int health;

	public GameObject()
	{
		this.size = new Vector2();
		this.pos = new Vector2();
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
		
	}
}
