package com.dekler.destructionfury.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Rectangle2D;

import com.dekler.destructionfury.collision.Collision;
import com.dekler.destructionfury.gameobject.Boss;
import com.dekler.destructionfury.gameobject.Bull;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.Explosion;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Scientist;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.map.SimpleTiledMap;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class Level
{
	private boolean reload, paused;
	protected Entity player;
	protected TiledMap map;
	protected ArrayList<WarpPad> warpPads;
	protected ArrayList<Entity> entities;
	protected ArrayList<Entity> newEntities;
	protected ArrayList<GameObject> objects;
	protected ArrayList<GameObject> hurtables;
	protected ArrayList<Explosion> effects;
	protected PropertyManager levelProperties;
	protected ArrayList<Point> crateTargets;
	protected HashMap<Point, Boolean> targets;

	public Level()
	{
		objects = new ArrayList<GameObject>();
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		effects = new ArrayList<Explosion>();
		hurtables = new ArrayList<GameObject>();
		warpPads = new ArrayList<WarpPad>();
		map = new SimpleTiledMap(22, 22);
		crateTargets = new ArrayList<Point>();
		targets = new HashMap<Point, Boolean>();
		reload = false;
		paused = true;
	}

	public void reset()
	{
		objects.clear();
		entities.clear();
		effects.clear();
		hurtables.clear();
		crateTargets.clear();
		targets.clear();
		warpPads.clear();

		player = null;
		map = null;

		reload = false;
		paused = true;
	}

	public void nextLevel(WarpPad warpPad)
	{
		String nextLevel = "";
		if (!levelProperties.getProperty(warpPad.getKey()).isEmpty())
		{
			nextLevel = levelProperties.getProperty(warpPad.getKey());
			levelProperties.putProperty("level-name", nextLevel);
			reload = true;
			paused = true;
		}
		else if (getLevelProperties() != null)
		{
			nextLevel = levelProperties.getProperty("next-level");
			if (!nextLevel.isEmpty())
			{
				levelProperties.putProperty("level-name", nextLevel);
				reload = true;
				paused  =true;
			}
		}
	}
		
	public boolean paused()
	{
		return paused;
	}

	public void setPause(boolean paused)
	{
		this.paused = paused;
	}
	
	public boolean reload()
	{
		return reload;
	}

	public PropertyManager getLevelProperties()
	{
		return levelProperties;
	}

	public void setLevelProperties(PropertyManager levelProperties)
	{
		this.levelProperties = levelProperties;
	}

	public Player getPlayer()
	{
		return (Player) player;
	}

	public void addPlayer(int x, int y)
	{
		player = new Player(this);
		player.setPosition(x, y);
		entities.add(player);
	}

	public ArrayList<WarpPad> getWarpPads()
	{
		return warpPads;
	}

	public void addWarpPad(WarpPad pad)
	{
		warpPads.add(pad);
		objects.add(pad);
	}

	public void addObject(GameObject o)
	{
		objects.add(o);
	}

	public void addHurtable(GameObject o)
	{
		hurtables.add(o);
	}
	
	private void addEntities()
	{
		while(newEntities.size()>0)
			entities.add(newEntities.remove(newEntities.size()-1));
	}

	public void addEntity(Entity e)
	{
		newEntities.add(e);
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
		resetTargets();
		addEntities();

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

			for (GameObject o : objects)
			{
				Collision.collision(e, o);
				if (o instanceof Crate && !(e instanceof Player))
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
			Collision.collision(o, map, TileEnum.WALL);
			if (o instanceof Crate)
			{
				Crate crate = (Crate) o;
				Collision.collisionV2(crate,player);
				Collision.collision(o, map, TileEnum.FLOOR);
				Collision.collision(o, map, TileEnum.WALL);
				for (GameObject o2 : objects)
					if (o2 instanceof Crate && o != o2)
						Collision.collisionV2(o, o2);
				
				Collision.collisionV2(player, o);
				if(new Rectangle2D(o.getIntX(), o.getIntY(), 1f,1f).contains(crate.getTargetHitBox()))
				{targets.put(new Point((int) (o.getX() + o.getWidth() * 0.5f),
						(int) (o.getY() + o.getHeight() * 0.5f)), true);
				}
			}
		}
		for (WarpPad warpPad : warpPads)
			warpPad.setActive(puzzleDone()&&noBossesLeft());

	}

	public void setMap(TiledMap map)
	{
		this.map = map;
		calculateCrateTargets();
	}

	private void calculateCrateTargets()
	{
		crateTargets.clear();
		for (int i = 0; i < map.getWidth(); i++)
			for (int j = 0; j < map.getHeight(); j++)
				if (map.getTile(i, j) == TileEnum.CRATE_TARGET)
					crateTargets.add(new Point(i, j));

		resetTargets();
	}

	private void resetTargets()
	{
		targets.clear();
		for (Point p : crateTargets)
			targets.put(p, false);
	}
	
	public boolean noBossesLeft()
	{
		for(Entity e : entities)
			if(e instanceof Boss || e instanceof Bull || e instanceof Scientist)
				return false;
		
		return true;
				
	}

	public boolean puzzleDone()
	{
		for (Boolean b : targets.values())
			if (!b)
				return b;
		return true;
	}
}
