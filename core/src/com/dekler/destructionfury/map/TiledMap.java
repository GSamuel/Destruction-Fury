package com.dekler.destructionfury.map;

public class TiledMap
{
	private int width, height;
	private TileEnum[][] map;

	public TiledMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		map = new TileEnum[width][height];
		init();
	}
	
	private void init()
	{
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				map[i][j] = TileEnum.FLOOR;
	}

	public void setTile(int x, int y, TileEnum t)
	{
		map[x][y] = t;
	}

	public TileEnum getTile(int x, int y)
	{
		return map[x][y];
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
