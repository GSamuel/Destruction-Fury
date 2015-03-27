package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Player extends Entity
{

	public Player(Level level)
	{
		super(level);
		this.setSize(0.9f, 0.9f);
	}

	@Override
	public void onTileCollision(TileEnum t)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (o != this)
		{
			Vector2 diff = new Vector2(getX() - o.getX(), getY() - o.getY());
			diff.setLength(2.6f);
			setForceX(diff.x);
			setForceY(diff.y);
			
			level.addEffect(new Explosion(pos.x, pos.y, "explosion"));
		}
	}
}
