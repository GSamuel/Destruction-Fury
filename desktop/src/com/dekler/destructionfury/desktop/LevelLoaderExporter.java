package com.dekler.destructionfury.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.dekler.destructionfury.gameobject.Boss;
import com.dekler.destructionfury.gameobject.Bull;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.gameobject.Scientist;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.level.LevelLoaderExporterInterface;
import com.dekler.destructionfury.level.PropertyManager;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class LevelLoaderExporter implements LevelLoaderExporterInterface
{
	public void saveLevel(Level level, String fileName,
			PropertyManager propManager)
	{
		TiledMap map = level.getMap();
		Pixmap pic = new Pixmap(map.getWidth(), map.getHeight(), Format.RGB888);
		for (int i = 0; i < map.getWidth(); i++)
			for (int j = 0; j < map.getHeight(); j++)
			{
				if (map.getTile(i, j) == TileEnum.WALL)
				{
					pic.setColor(propManager.getColorProperty("wall-color"));
					pic.drawPixel(i, map.getHeight()-j-1);
				}else if(map.getTile(i, j) == TileEnum.CRATE_FLOOR)
				{
					pic.setColor(propManager.getColorProperty("cratefloor-color"));
					pic.drawPixel(i, map.getHeight()-j-1);
				}else if(map.getTile(i, j) == TileEnum.CRATE_TARGET)
				{
					pic.setColor(propManager.getColorProperty("cratetarget-color"));
					pic.drawPixel(i, map.getHeight()-j-1);
				}else if(map.getTile(i, j) == TileEnum.BREAKABLE_WALL)
				{
					pic.setColor(propManager.getColorProperty("breakwall-color"));
					pic.drawPixel(i, map.getHeight()-j-1);
				}
				else
				{
					pic.setColor(Color.WHITE);
					pic.drawPixel(i, map.getHeight()-j-1);
				}
			}

		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.addAll(level.getEntities());
		objects.addAll(level.getGameObjects());

		for (GameObject o : objects)
		{
			if (o instanceof Player)
			{
				pic.setColor(propManager.getColorProperty("player-color"));
				pic.drawPixel(o.getIntX(),map.getHeight()- o.getIntY()-1);
			} else if (o instanceof Robot)
			{
				pic.setColor(propManager.getColorProperty("robot-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			} else if (o instanceof Crate)
			{
				pic.setColor(propManager.getColorProperty("crate-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			} else if(o instanceof WarpPad)
			{
				pic.setColor(propManager.getColorProperty("warppad-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			} else if (o instanceof Boss)
			{
				pic.setColor(propManager.getColorProperty("boss1-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			}else if (o instanceof Bull)
			{
				pic.setColor(propManager.getColorProperty("boss2-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			}else if (o instanceof Scientist)
			{
				pic.setColor(propManager.getColorProperty("boss3-color"));
				pic.drawPixel(o.getIntX(), map.getHeight()- o.getIntY()-1);
			}
		}

		FileHandle file = Gdx.files.local(fileName);
		
		
		PixmapIO.writePNG(file, pic);
	}

	public void loadLevel(Level level, PropertyManager propManager)
	{
		level.reset();
		String fileName;
		if(level.getLevelProperties() == null)
			fileName = propManager.getProperty("level-name");
		else
			fileName = level.getLevelProperties().getProperty("level-name");
		
		PropertyManager levelProperties = new PropertyManager(fileName.split("\\x2E")[0]+".properties", false);
		levelProperties.putProperty("next-level", "");
		
		level.setLevelProperties(levelProperties);
		
		FileHandle file = Gdx.files.local(fileName);
		
		Pixmap pic = new Pixmap(file);
		
		TiledMap map = new TiledMap(pic.getWidth(), pic.getHeight());
		
		for(int i = 0; i < pic.getWidth(); i++)
			for(int j =0; j< pic.getHeight(); j++)
			{
				int pixel = pic.getPixel(i, pic.getHeight()-j-1);
				if(pixel == Color.rgba8888(propManager.getColorProperty("player-color")))
					level.addPlayer(i, j);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("robot-color")))
				{
					Robot robot = new Robot(level);
					robot.setPosition(i, j);
					level.addEntity(robot);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("boss1-color")))
				{
					Boss b = new Boss(level);
					b.setPosition(i, j);
					level.addEntity(b);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("boss2-color")))
				{
					Bull b = new Bull(level);
					b.setPosition(i, j);
					level.addEntity(b);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("boss3-color")))
				{
					Scientist sc = new Scientist(level);
					sc.setPosition(i, j);
					level.addEntity(sc);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("crate-color")))
				{
					Crate crate = new Crate(level);
					crate.setPosition(i, j);
					level.addObject(crate);
					map.setTile(i, j, TileEnum.CRATE_FLOOR);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("warppad-color")))
				{
					WarpPad warpPad = new WarpPad(level);
					warpPad.setPosition(i, j);
					level.addWarpPad(warpPad);
					levelProperties.putProperty(warpPad.getKey(),"");
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("wall-color")))
					map.setTile(i,j	, TileEnum.WALL);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("cratefloor-color")))
					map.setTile(i,j	, TileEnum.CRATE_FLOOR);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("cratetarget-color")))
					map.setTile(i,j	, TileEnum.CRATE_TARGET);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("breakwall-color")))
						map.setTile(i,j	, TileEnum.BREAKABLE_WALL);
			}

		
		levelProperties.readPropertyFile();
		levelProperties.writePropertyFile();
		
		levelProperties.putProperty("level-name", fileName);
		
		level.setMap(map);
	}
}
