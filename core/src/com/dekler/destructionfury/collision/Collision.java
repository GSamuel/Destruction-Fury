package com.dekler.destructionfury.collision;

import javafx.geometry.Rectangle2D;

import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class Collision
{
	public static void collision(GameObject o, TiledMap map)
	{
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
			{
				int newX = o.getIntX() + i;
				int newY = o.getIntY() + j;

				if (newX >= 0 && newY >= 0 && newX < map.getWidth()
						&& newY < map.getHeight())
				{
					if (map.getTile(newX, newY) == TileEnum.WALL)
					{
						Rectangle2D recA = new Rectangle2D(o.getX(), o.getY(),
								o.getWidth(), o.getHeight());
						Rectangle2D recB = new Rectangle2D(newX, newY, 1f, 1f);

						if (recA.intersects(recB))
						{
							float distX = o.getX() + o.getWidth() * 0.5f
									- (newX + 0.5f);
							float minDistX = o.getWidth() * 0.5f + 0.5f;
							

							float distY = o.getY() + o.getHeight() * 0.5f
									- (newY + 0.5f);
							float minDistY = o.getHeight() * 0.5f + 0.5f;
							
							if(Math.abs(distX) >= Math.abs(distY))
							{
							if (distX >= 0)
								o.changePosition(minDistX - distX, 0f);
							else
								o.changePosition(-(minDistX + distX), 0f);
							}
							else
							{
								if (distY >= 0)
									o.changePosition(0f, minDistY - distY);
								else
									o.changePosition(0f, -(minDistY + distY));
							}

						}

					}
				}
			}

	}
}
