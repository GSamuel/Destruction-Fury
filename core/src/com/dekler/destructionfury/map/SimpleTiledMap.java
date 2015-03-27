package com.dekler.destructionfury.map;

public class SimpleTiledMap extends TiledMap
{

	public SimpleTiledMap(int width, int height)
	{
		super(width,height);
		init();
	}
	
	private void init()
	{
		for(int i =0; i< getWidth(); i++)
			for(int j =0; j<getHeight(); j++)
			{
				if(j%3==0 && i%3 ==0)
					this.setTile(i, j, TileEnum.WALL);
				if(i == 0 || i == getWidth()-1 || j ==0 || j == getHeight()-1)
					this.setTile(i, j, TileEnum.WALL);
			}
	}

}
