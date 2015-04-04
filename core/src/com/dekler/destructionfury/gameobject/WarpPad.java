package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class WarpPad extends GameObject
{
	private boolean active;

	public WarpPad(Level level)
	{
		super(level);
		setSize(2.5f, 1.2f);
		active = false;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	@Override
	public void update()
	{
	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if(o instanceof Player && active)
		{
			if(o.getX() > getX() && o.getX()< getX() +getWidth()-o.getWidth())
				if(o.getY()> getY() && o.getY()< getY()+getHeight()-o.getHeight())
					level.nextLevel(this);
		}
	}

	@Override
	public void damage(int damage)
	{
		health -= damage;		
	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		
	}
	
	public String getKey()
	{
		return "warp-pad("+getIntX()+","+getIntY()+")";
	}
}
