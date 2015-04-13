package com.dekler.destructionfury.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.dekler.destructionfury.gameobject.Boss;
import com.dekler.destructionfury.gameobject.Bull;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.gameobject.Scientist;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class HTMLLevelLoaderExporter implements LevelLoaderExporterInterface
{

	@Override
	public void loadLevel(Level level, PropertyManager propManager)
	{level.reset();
	String fileName;
	if(level.getLevelProperties() == null)
		fileName = propManager.getProperty("level-name");
	else
		fileName = level.getLevelProperties().getProperty("level-name");
	
	PropertyManager levelProperties = new PropertyManager(fileName.split("\\x2E")[0]+".properties");
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

	@Override
	public void saveLevel(Level level, String fileName,
			PropertyManager propManager)
	{
		
	}

}
