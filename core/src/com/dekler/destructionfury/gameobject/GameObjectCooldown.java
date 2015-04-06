package com.dekler.destructionfury.gameobject;

public class GameObjectCooldown extends Cooldown
{
	private GameObject object;
	
	public GameObjectCooldown(GameObject o, float time)
	{
		super(time);
		this.object = o;
	}
	
	public GameObject getObject()
	{
		return object;
	}
}
