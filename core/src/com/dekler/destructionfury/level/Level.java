package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.map.RandomTiledMap;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private Entity player;
	private ArrayList<GameObject> objects;
	private TiledMap map;

	public Level()
	{
		player = new Player();
		map = new RandomTiledMap(15, 10);
		objects = new ArrayList<GameObject>();
	}

	public Entity getPlayer()
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
		System.out.println(player.getX()+":"+player.getY());
		player.update();
		for(GameObject o: objects)
			o.update();
		
		Collision.collision(player, map);
	}
}
