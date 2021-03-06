package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public abstract class GameObject
{
	protected Vector2 size;
	protected Vector2 pos;
	protected Vector2 force;
	protected Level level;
	protected boolean remove = false;
	
	protected int health;

	public GameObject(Level level)
	{
		this.level = level;
		this.size = new Vector2(0.9f,0.9f);
		this.pos = new Vector2();
		this.force = new Vector2();
		health = 1;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public boolean getRemove()
	{
		return remove;
	}
	
	public void remove()
	{
		remove = true;
	}
	
	public void setSize(float width, float height)
	{
		size.x = width;
		size.y = height;
	}
	
	public float getWidth()
	{
		return size.x;
	}
	
	public float getHeight()
	{
		return size.y;
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
	
	public void setForceX(float forceX)
	{
		this.force.x = forceX;
	}
	
	public void setForceY(float forceY)
	{
		this.force.y = forceY;
	}
	
	public void addForce(float forceX, float forceY)
	{
		force.x += forceX;		force.y += forceY;
	}
	
	public abstract void update();
	public abstract void onTileCollision(TileEnum t, float x, float y);
	public abstract void onGameObjectCollision(GameObject o);
	public abstract void damage(int damage);
}
