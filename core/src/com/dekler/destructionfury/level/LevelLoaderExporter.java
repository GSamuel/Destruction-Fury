package com.dekler.destructionfury.level;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class LevelLoaderExporter
{
	public static void saveLevel(Level level)
	{
		TiledMap map = level.getMap();
		Pixmap pic = new Pixmap(map.getWidth(), map.getHeight(), Format.RGB888);
		for (int i = 0; i < map.getWidth(); i++)
			for (int j = 0; j < map.getHeight(); j++)
			{
				if (map.getTile(i, j) == TileEnum.WALL)
				{
					pic.setColor(Color.BLACK);
					pic.drawPixel(i, j);
				}
				else
				{
					pic.setColor(Color.YELLOW);
					pic.drawPixel(i, j);
				}

			}
		
		FileHandle file = new FileHandle("savedLevel.png");
		PixmapIO.writePNG(file, pic);
	}
}
