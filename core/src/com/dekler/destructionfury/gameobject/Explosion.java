package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Explosion
{
	private Vector2 pos;
	private Vector2 size;
	private String aniName;
	private float time;
	
	public Explosion(float x, float y, String aniName)
	{
		pos = new Vector2(x,y);
		size = new Vector2(1f,1f);
		this.aniName =aniName;
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
	
	public float getY()
	{
		return pos.y;
	}
	
	public String getAniName()
	{
		return aniName;
	}

	public void update()
	{
		time += Gdx.graphics.getDeltaTime();
	}
	
	public float getTime()
	{
		return time;
	}
}
