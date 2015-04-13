package com.dekler.destructionfury.gameobject;

import javafx.geometry.Rectangle2D;

import com.badlogic.gdx.math.Rectangle;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Crate extends GameObject
{
	public Crate(Level level)
	{
		super(level);
	}

	public Rectangle getTargetHitBox()
	{
		return new Rectangle(getX()+getWidth()*0.25f, getY()+getHeight()*0.25f, getWidth()*0.5f, getHeight()*0.5f);
	}
	
	@Override
	public void update()
	{
	}
	
	@Override
	public void onGameObjectCollision(GameObject o)
	{
	}

	@Override
	public void damage(int damage)
	{

	}

	@Override
	public void onTileCollision(TileEnum t, float x, float y)
	{
		
	}

}
