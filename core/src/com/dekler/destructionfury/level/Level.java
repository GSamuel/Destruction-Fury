package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Explosion;
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
	private ArrayList<Explosion> effects;

	public Level()
	{
		player = new Player(this);
		player.setPosition(1f, 1f);

		map = new SimpleTiledMap(22, 22);

		objects = new ArrayList<GameObject>();
		entities = new ArrayList<Entity>();
		effects = new ArrayList<Explosion>();

		entities.add(player);

		Entity robot = new Robot(this);
		robot.setPosition(1f, 1f);
		entities.add(robot);

		robot = new Robot(this);
		robot.setPosition(3f, 5f);
		entities.add(robot);

		robot = new Robot(this);
		robot.setPosition(2f, 6f);
		entities.add(robot);

		robot = new Robot(this);
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
	
	public ArrayList<Explosion> getEffects()
	{
		return effects;
	}
	
	public void addEffect(Explosion e)
	{
		effects.add(e);
	}

	public void update()
	{
		for (Entity e : entities)
			e.update();

		for (GameObject o : objects)
			o.update();
		
		for(Explosion ef: effects)
		{
			ef.update();			
		}

		for (Entity e : entities)
		{
			Collision.collision(e, map);
			Collision.collision(player, e);
		}
		for (GameObject o : objects)
			Collision.collision(o, map);

	}
}
