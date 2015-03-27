package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.Explosion;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.map.SimpleTiledMap;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private Entity player;
	private ArrayList<Entity> entities;
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> hurtables;
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
		hurtables = new ArrayList<GameObject>();

		entities.add(player);
		
		GameObject o = new WarpPad(this);
		o.setPosition(4f, 4f);
		objects.add(o);

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
	
	public void addHurtable(GameObject o)
	{
		hurtables.add(o);
	}

	public TiledMap getMap()
	{
		return map;
	}
	
	public ArrayList<GameObject> getGameObjects()
	{
		return objects;
	}

	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	public ArrayList<Explosion> getEffects()
	{
		return effects;
	}
	
	public ArrayList<GameObject> getHurtables()
	{
		return hurtables;
	}
	
	public void addEffect(Explosion e)
	{
		effects.add(e);
	}

	public void update()
	{
		
		for(int i=0; i < entities.size(); i++)
			if(entities.get(i).getRemove())
				entities.remove(i--);

		for(int i=0; i < objects.size(); i++)
			if(objects.get(i).getRemove())
				objects.remove(i--);
		
		for(int i=0; i < hurtables.size(); i++)
			if(hurtables.get(i).getRemove())
				hurtables.remove(i--);
		
		
		for (Entity e : entities)
			e.update();

		for (GameObject o : objects)
			o.update();
		
		for(Explosion ef: effects)
			ef.update();
		
		for(GameObject h : hurtables)
			h.update();
		
		for(Entity e: entities)
			for(GameObject h: hurtables)
				Collision.collision(e, h);

		for (Entity e : entities)
		{
			Collision.collision(e, map);
			Collision.collision(player, e);
		}
		for (GameObject o : objects)
			Collision.collision(player, o);

	}
}
