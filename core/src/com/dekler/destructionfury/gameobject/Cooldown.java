package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;

public class Cooldown
{
	private float cooldownTime;
	private float curTime;
	
	public Cooldown(float cooldownTime)
	{
		this.cooldownTime = cooldownTime;
	}
	
	public boolean cooldownOver()
	{
		return curTime <= 0;
	}
	
	public void update()
	{
		curTime -= Gdx.graphics.getDeltaTime();
	}
	
	public void start()
	{
		curTime = cooldownTime;
	}
	
	public float percentageDone()
	{
		if(curTime <= 0 || cooldownTime <=0)
			return 1f;
		return 1f - curTime/cooldownTime;
	}
}
