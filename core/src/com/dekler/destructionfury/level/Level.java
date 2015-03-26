package com.dekler.destructionfury.level;

import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private GameObject player;
	private GameObject objects;
	private TiledMap map;
	
	public GameObject getPlayer()
	{
		return player;
	}
	
	public TiledMap getMap()
	{
		return map;
	}
}
