package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Explosion extends GameObject
{
	private String aniName;
	private float time;
	
	public Explosion(Level level)
	{
		super(level);
		this.aniName = "explosion";
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

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void damage(int damage)
	{
		// TODO Auto-generated method stub
		
	}
}
