package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.map.SimpleTiledMap;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private Entity player;
	private ArrayList<Entity> entities;
	private ArrayList<GameObject> objects;
	private TiledMap map;

	public Level()
	{
		player = new Player();
		player.setPosition(1f, 1f);
		
		map = new SimpleTiledMap(22,22);
		
		objects = new ArrayList<GameObject>();
		entities = new ArrayList<Entity>();

		entities.add(player);

		Entity robot = new Robot();
		robot.setPosition(1f, 1f);
		entities.add(robot);
		
		robot = new Robot();
		robot.setPosition(3f, 5f);
		entities.add(robot);
		
		robot = new Robot();
		robot.setPosition(2f, 6f);
		entities.add(robot);
		
		robot = new Robot();
		robot.setPosition(2f, 3f);
		entities.add(robot);
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
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}

	public void update()
	{
		for(Entity e: entities)
			e.update();
		
		for(GameObject o: objects)
			o.update();
		

		for(Entity e: entities)
			Collision.collision(e, map);
		for(GameObject o: objects)
			Collision.collision(o, map);
	}
}
