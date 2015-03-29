package com.dekler.destructionfury.map;

import java.util.Random;

public class RandomTiledMap extends TiledMap
{

	public RandomTiledMap(int width, int height)
	{
		super(width, height);

		Random rand = new Random(System.currentTimeMillis());

		for (int i = 0; i < getWidth(); i++)
			for (int j = 0; j < getHeight(); j++)
			{
				if (rand.nextBoolean())
					setTile(i, j, TileEnum.WALL);
				else
					setTile(i, j, TileEnum.FLOOR);
			}
	}
}
