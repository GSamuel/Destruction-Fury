package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.Explosion;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.map.SimpleTiledMap;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	protected Entity player;
	protected TiledMap map;
	protected ArrayList<Entity> entities;
	protected ArrayList<GameObject> objects;
	protected ArrayList<GameObject> hurtables;
	protected ArrayList<Explosion> effects;

	public Level()
	{
		objects = new ArrayList<GameObject>();
		entities = new ArrayList<Entity>();
		effects = new ArrayList<Explosion>();
		hurtables = new ArrayList<GameObject>();
		map = new SimpleTiledMap(22, 22);
	}
	
	
	
	public void reset()
	{
		objects.clear();
		entities.clear();
		effects.clear();
		hurtables.clear();
	}

	public Entity getPlayer()
	{
		return player;
	}
	
	public void addPlayer(int x, int y)
	{
		player = new Player(this);
		player.setPosition(x, y);
		entities.add(player);
	}

	public void addObject(GameObject o)
	{
		objects.add(o);
	}

	public void addHurtable(GameObject o)
	{
		hurtables.add(o);
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
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

		for (int i = 0; i < entities.size(); i++)
			if (entities.get(i).getRemove())
				entities.remove(i--);

		for (int i = 0; i < objects.size(); i++)
			if (objects.get(i).getRemove())
				objects.remove(i--);

		for (int i = 0; i < hurtables.size(); i++)
			if (hurtables.get(i).getRemove())
				hurtables.remove(i--);

		for (Entity e : entities)
			e.update();

		for (GameObject o : objects)
			o.update();

		for (Explosion ef : effects)
			ef.update();

		for (GameObject h : hurtables)
			h.update();

		for (Entity e : entities)
		{
			for (GameObject h : hurtables)
				Collision.collision(e, h);
			
			for(GameObject o: objects)
			{
				Collision.collision(e, o);
				if(o instanceof Crate)
					Collision.collisionV2(e, o);
			}
		}

		for (Entity e : entities)
		{
			Collision.collision(e, map, TileEnum.WALL);
			Collision.collision(player, e);
		}
		for (GameObject o : objects)
		{
			Collision.collision(player, o);
			Collision.collision(o, map, TileEnum.WALL);
			if(o instanceof Crate)
			{
				Collision.collision(o, map, TileEnum.FLOOR);
				for(GameObject o2: objects)
					if(o2 instanceof Crate && o != o2)
						Collision.collisionV2(o, o2);
				Collision.collisionV2(player, o);
			}
		}

	}



	public void setMap(TiledMap map)
	{
		this.map = map;
	}
}
