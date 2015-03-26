package com.dekler.destructionfury.collision;

import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class Collision
{
	public static void collision(GameObject o, TiledMap map)
	{
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
			{
				int newX = o.getIntX() + i;
				int newY = o.getIntY() + j;
				if (newX >= 0 && newX < map.getWidth() && newY >= 0
						&& newY <= map.getHeight())
				{
					if (map.getTile(newX, newY) == TileEnum.WALL)
					{
						float dx = o.getX() - newX;
						float dy = o.getY() - newY;
						if(dx < 1f && dx>-o.getWidth())
							if(dx > 0)
								o.changePosition(1f-dx, 0);
							else
								o.changePosition(dx-o.getWidth(), 0);
							
						if(dy < 1f && dy>-o.getHeight())
							if(dy > 0)
								o.changePosition(1f-dy, 0);
							else
								o.changePosition(dy-o.getHeight(), 0);
					}
				}
			}

	}
}
