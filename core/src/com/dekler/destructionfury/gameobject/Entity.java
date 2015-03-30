package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;

public abstract class Entity extends GameObject
{
	protected Vector2 vel;
	protected float speed = 2;
	protected float time = 0f;	
	protected Direction direction;
	protected int damageTimer;
	
	public Entity(Level level)
	{
		super(level);
		this.vel = new Vector2();
		direction = Direction.DOWN;
	}
	
	public float getVelX()
	{
		return vel.x;
	}
	
	public float getVelY()
	{
		return vel.y;
	}
	
	public void setVelX(float velX)
	{
		this.vel.x = velX;
	}
	
	public void setVelY(float velY)
	{
		this.vel.y = velY;
	}
	
	public void moveLeft()
	{
		vel.x = -speed;
		direction = Direction.LEFT;
		stopMoveY();
	}

	public void moveRight()
	{
		vel.x = speed;
		direction = Direction.RIGHT;
		stopMoveY();
	}

	public void stopMoveX()
	{
		vel.x = 0;
	}

	public void moveUp()
	{
		vel.y = speed;
		direction = Direction.UP;
		stopMoveX();
	}

	public void moveDown()
	{
		vel.y = -speed;
		direction = Direction.DOWN;
		stopMoveX();
	}

	public void stopMoveY()
	{
		vel.y = 0;
	}
	
	public float getTime()
	{
		if(vel.x == 0f && vel.y == 0f)
			return 0f;
		return time;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void update()
	{
		if (health <= 0)
		{
			level.addEffect(new Explosion(getX(), getY()));
			remove();
			return;
		}
		
		damageTimer--;
		
		float forceX = force.x * Gdx.graphics.getDeltaTime()*3;
		float forceY = force.y * Gdx.graphics.getDeltaTime()*3;
		pos.add(forceX, forceY);
		force.x -= forceX;
		force.y -= forceY;
		if(force.x < 0.5f && force.x >-2.5f)
			force.x =0;
		if(force.y < 0.5f && force.y >-2.5f)
			force.y =0;
		pos.add(vel.x*Gdx.graphics.getDeltaTime(), vel.y*Gdx.graphics.getDeltaTime());
		time += Gdx.graphics.getDeltaTime();
	}
	
	public int getDamageTimer()
	{
		return damageTimer;
	}
	
	@Override
	public void damage(int damage)
	{
		if(damageTimer <= 0)
		{
			health -= damage;
			damageTimer = 50;
		}
		
	}

	public abstract void attack();
}
