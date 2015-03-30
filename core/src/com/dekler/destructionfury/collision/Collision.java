package com.dekler.destructionfury.collision;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;

import com.dekler.destructionfury.gameobject.Entity;
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

							if (Math.abs(distX) >= Math.abs(distY))
							{
								if (distX >= 0)
								{
									o.changePosition(minDistX - distX, 0f);
									o.onTileCollision(TileEnum.WALL);
								} else
								{
									o.changePosition(-(minDistX + distX), 0f);
									o.onTileCollision(TileEnum.WALL);
								}
							} else
							{
								if (distY >= 0)
								{
									o.changePosition(0f, minDistY - distY);
									o.onTileCollision(TileEnum.WALL);
								} else
								{
									o.changePosition(0f, -(minDistY + distY));
									o.onTileCollision(TileEnum.WALL);
								}
							}

						}

					}
				}
			}
	}

	public static void collisions(ArrayList<Entity> objects)
	{
		for (int i = 0; i < objects.size(); i++)
			for (int j = i + 1; j < objects.size(); j++)
				collision(objects.get(i), objects.get(j));
	}

	public static void collisionV2(GameObject a, GameObject b)
	{
		Rectangle2D recA = new Rectangle2D(a.getX(), a.getY(), a.getWidth(),
				a.getHeight());
		Rectangle2D recB = new Rectangle2D(b.getX(), b.getY(), b.getWidth(),
				b.getHeight());

		if (recA.intersects(recB))
		{

			float distX = a.getX() + a.getWidth() * 0.5f
					- (b.getX() + b.getWidth() * 0.5f);
			float minDistX = b.getWidth() * 0.5f + a.getWidth() * 0.5f;

			float distY = a.getY() + a.getHeight() * 0.5f
					- (b.getY() + b.getHeight() * 0.5f);
			float minDistY = b.getHeight() * 0.5f + a.getHeight() * 0.5f;

			if (Math.abs(distX) >= Math.abs(distY))
			{
				if (distX >= 0)
					a.changePosition(minDistX - distX, 0f);
				else
					a.changePosition(-(minDistX + distX), 0f);
			} else
			{
				if (distY >= 0)
					a.changePosition(0f, minDistY - distY);
				else
					a.changePosition(0f, -(minDistY + distY));
			}
		}
	}

	public static void collision(GameObject a, GameObject b)
	{
		Rectangle2D recA = new Rectangle2D(a.getX(), a.getY(), a.getWidth(),
				a.getHeight());
		Rectangle2D recB = new Rectangle2D(b.getX(), b.getY(), b.getWidth(),
				b.getHeight());

		if (recA.intersects(recB))
		{
			a.onGameObjectCollision(b);
			b.onGameObjectCollision(a);
		}
	}
}
