package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Grenade extends Entity
{
	private float flyingTime;
	private float explosionTime;

	public Grenade(Level level)
	{
		super(level);
		setSize(0.7f, 0.7f);
		flyingTime = 0.5f;
		explosionTime = 1f;
	}
	
	public boolean isFlying()
	{
		return flyingTime >0;
	}

	@Override
	public void update()
	{
		spawnCD.stop();
		super.update();
		if (flyingTime > 0)
			flyingTime -= Gdx.graphics.getDeltaTime();
		else
		{
			vel.x = 0;
			vel.y = 0;
			explosionTime -= Gdx.graphics.getDeltaTime();
		}

		if (explosionTime < 0)
			explode();
	}

	private void explode()
	{
		Explosion boom = new Explosion(level);
		boom.setSize(1.5f,1.5f);
		boom.setPosition(getX(), getY());
		level.addEffect(boom);
		
		AreaDamage area = new AreaDamage(level, 0.7f);
		area.setSize(1.2f, 1.2f);
		area.setPosition(getX()-0.1f, getY()-0.1f);
		level.addHurtable(area);
		
		remove();
	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if (!(o instanceof Player))
			flyingTime = 0;
	}

	@Override
	public void damage(int damage)
	{

	}

	@Override
	public void attack()
	{

	}

}
