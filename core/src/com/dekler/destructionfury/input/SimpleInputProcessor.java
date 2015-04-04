package com.dekler.destructionfury.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.level.LevelLoaderExporter;
import com.dekler.destructionfury.level.PropertyManager;

public class SimpleInputProcessor implements InputProcessor, GestureListener
{

	private Level level;
	private PropertyManager propManager;

	private boolean w, a, s, d, g, space;

	public SimpleInputProcessor(Level level, PropertyManager propManager)
	{
		this.level= level;
		this.propManager = propManager;
	}
	
	public void setLevel(Level level)
	{
		this.level = level;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);
		g = Gdx.input.isKeyPressed(Input.Keys.G);
		space = Gdx.input.isKeyPressed(Input.Keys.SPACE);
		
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.R))
		{
			if(propManager.getIntegerProperty("load-level")>0)
				LevelLoaderExporter.loadLevel(level, propManager);
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.N))
			if(level.getLevelProperties() != null)
			{
				PropertyManager levelProp = level.getLevelProperties();
				String s = levelProp.getProperty("next-level");
				if (!s.isEmpty())
				{
					System.out.println(":"+s+":");
					levelProp.putProperty("level-name", s);
					LevelLoaderExporter.loadLevel(level, propManager);
				}
				
			}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.P))
			level.setPause(!level.paused());
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);
		g = Gdx.input.isKeyPressed(Input.Keys.G);
		space = Gdx.input.isKeyPressed(Input.Keys.SPACE);

		return true;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void update()
	{
		if(level.paused())
			return;
		
		if (w || a || s || d)
		{
			float dx = 0f, dy = 0f;
			if (Gdx.input.isKeyPressed(Input.Keys.A))
				dx -= 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.D))
				dx += 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.W))
				dy -= 1f;
			if (Gdx.input.isKeyPressed(Input.Keys.S))
				dy += 1f;
			
			if(dx <0)
				level.getPlayer().moveLeft();
			else if(dx>0)
				level.getPlayer().moveRight();
			else
				level.getPlayer().stopMoveX();
			//only move vertical when nog moving horizontal
			if(dy <0 && dx ==0)
				level.getPlayer().moveUp();
			else if(dy>0 && dx ==0)
				level.getPlayer().moveDown();
			else
				level.getPlayer().stopMoveY();
		}
		else
		{
			level.getPlayer().stopMoveX();
			level.getPlayer().stopMoveY();
		}
		
		if(space)
		{
			level.getPlayer().attack();
		}
		
		if(g)
			level.getPlayer().altAttack();
	}

}
