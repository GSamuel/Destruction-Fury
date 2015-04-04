package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class AreaDamage extends GameObject
{
	private float duration;
	public AreaDamage(Level level, float duration)
	{
		super(level);
		this.duration = duration;
	}

	@Override
	public void update()
	{
		duration -= Gdx.graphics.getDeltaTime();
		if(duration < 0)
			remove();
		
	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		o.damage(1);
	}

	@Override
	public void damage(int damage)
	{
		
	}

}
