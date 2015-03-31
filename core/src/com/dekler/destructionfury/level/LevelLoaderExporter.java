package com.dekler.destructionfury.level;

import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class LevelLoaderExporter
{
	public static void saveLevel(Level level, String fileName,
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
					pic.drawPixel(i, j);
				}else if(map.getTile(i, j) == TileEnum.CRATE_FLOOR)
				{
					pic.setColor(propManager.getColorProperty("cratefloor-color"));
					pic.drawPixel(i, j);
				}
				else
				{
					pic.setColor(Color.WHITE);
					pic.drawPixel(i, j);
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
				pic.drawPixel(o.getIntX(), o.getIntY());
			} else if (o instanceof Robot)
			{
				pic.setColor(propManager.getColorProperty("robot-color"));
				pic.drawPixel(o.getIntX(), o.getIntY());
			} else if (o instanceof Crate)
			{
				pic.setColor(propManager.getColorProperty("crate-color"));
				pic.drawPixel(o.getIntX(), o.getIntY());
			}
		}

		FileHandle file = new FileHandle(fileName);
		PixmapIO.writePNG(file, pic);
	}

	public static Level loadLevel(PropertyManager propManager)
	{
		Level level = new Level();
		FileHandle file = new FileHandle(propManager.getProperty("level-name"));
		
		Pixmap pic = new Pixmap(file);
		
		TiledMap map = new TiledMap(pic.getWidth(), pic.getHeight());
		
		for(int i = 0; i < pic.getWidth(); i++)
			for(int j =0; j< pic.getHeight(); j++)
			{
				int pixel = pic.getPixel(i, j);
				if(pixel == Color.rgba8888(propManager.getColorProperty("player-color")))
					level.addPlayer(i, j);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("robot-color")))
				{
					Robot robot = new Robot(level);
					robot.setPosition(i, j);
					level.addEntity(robot);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("crate-color")))
				{
					Crate crate = new Crate(level);
					crate.setPosition(i, j);
					level.addObject(crate);
					map.setTile(i, j, TileEnum.CRATE_FLOOR);
				}else if(pixel == Color.rgba8888(propManager.getColorProperty("wall-color")))
					map.setTile(i,j	, TileEnum.WALL);
				else if(pixel == Color.rgba8888(propManager.getColorProperty("cratefloor-color")))
						map.setTile(i,j	, TileEnum.CRATE_FLOOR);
					
			}
		
		level.setMap(map);

		return level;
	}
}
