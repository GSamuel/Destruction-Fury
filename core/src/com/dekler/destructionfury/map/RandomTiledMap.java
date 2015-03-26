package com.dekler.destructionfury.map;

import java.util.Random;

public class RandomTiledMap extends TiledMap
{

	public RandomTiledMap(int width, int height)
	{
		super(width, height);

		Random rand = new Random(System.currentTimeMillis());

		Tile t;
		for (int i = 0; i < getWidth(); i++)
			for (int j = 0; j < getHeight(); j++)
			{
				if (rand.nextBoolean())
					setTile(i, j, Tile.WALL);
				else
					setTile(i, j, Tile.FLOOR);
			}
	}
}
