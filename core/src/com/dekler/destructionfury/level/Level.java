package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.SimpleGameObject;
import com.dekler.destructionfury.map.RandomTiledMap;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private GameObject player;
	private ArrayList<GameObject> objects;
	private TiledMap map;

	public Level()
	{
		player = new SimpleGameObject();
		map = new RandomTiledMap(15, 10);
		objects = new ArrayList<GameObject>();
	}

	public GameObject getPlayer()
	{
		return player;
	}

	public void addObject(GameObject o)
	{
		objects.add(o);
	}

	public TiledMap getMap()
	{
		return map;
	}

	public void update()
	{
		player.update();
		for(GameObject o: objects)
			o.update();
	}
}
