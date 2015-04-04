package com.dekler.destructionfury.gameobject;

import com.badlogic.gdx.math.Vector2;

public enum Direction
{
	UP, DOWN, LEFT, RIGHT;

	public Direction reverse()
	{
		switch (this)
		{
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		default:
			return DOWN;
		}
	}

	public Vector2 getDirectionVector()
	{
		switch (this)
		{
		case UP:
			return new Vector2(0,1f);
		case DOWN:
			return new Vector2(0,-1f);
		case LEFT:
			return new Vector2(-1f,0);
		case RIGHT:
			return new Vector2(1f,0);
		default:
			return new Vector2(0,0);
		}
	}
}
