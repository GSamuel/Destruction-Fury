package com.dekler.destructionfury.level;

import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.map.SimpleTiledMap;
import com.dekler.destructionfury.map.TileEnum;

public class SimpleLevel extends Level
{
	public SimpleLevel()
	{
		map = new SimpleTiledMap(22, 22);

		player = new Player(this);
		player.setPosition(1f, 1f);

		entities.add(player);

		GameObject o = new WarpPad(this);
		o.setPosition(4f, 4f);
		objects.add(o);

		Entity robot;

		Crate crate = new Crate(this);
		crate.setPosition(2f, 1f);
		objects.add(crate);
		map.setTile(2, 1, TileEnum.CRATE_FLOOR);

		crate = new Crate(this);
		crate.setPosition(3f, 1f);
		objects.add(crate);
		map.setTile(3, 1, TileEnum.CRATE_FLOOR);

		for (int i = 0; i < 20; i++)
		{
			robot = new Robot(this);
			robot.setPosition((float) Math.random() * map.getWidth(),
					(float) Math.random() * map.getHeight());
			entities.add(robot);
		}


	}
}
