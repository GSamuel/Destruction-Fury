package com.dekler.destructionfury.gameobject;

import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;

public class Knife extends GameObject
{
	private int damage;
	private int time;
	private int degrees;
	public Knife(Level level)
	{
		super(level);
		damage = 1;
		time = 6;
	}
	
	public int getRotation()
	{
		return degrees;
	}
	
	public void setRotation(int degrees)
	{
		this.degrees = degrees;
	}

	@Override
	public void update()
	{
		Entity player = level.getPlayer();
		Direction direction = player.getDirection();
		switch (direction) {
		case RIGHT:
			setPosition(player.getX()+0.5f, player.getY());
			setRotation(90);
			break;
		case LEFT:
			setPosition(player.getX()-0.5f, player.getY());
			setRotation(270);
			break;
		case UP:
			setPosition(player.getX(), player.getY()+0.5f);
			setRotation(180);
			break;
		case DOWN:
			setPosition(player.getX(), player.getY()-0.5f);
			setRotation(0);
			break;
			
			

		default:
			break;
		}
		
		
		time --;
		if(time <=0)
			remove();
	}

	@Override
	public void onTileCollision(TileEnum t)
	{

	}

	@Override
	public void onGameObjectCollision(GameObject o)
	{
		if(o instanceof Robot)
			o.damage(damage);
	}

	@Override
	public void damage(int damage)
	{
		
	}
	
}
