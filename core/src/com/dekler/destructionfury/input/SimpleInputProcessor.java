package com.dekler.destructionfury.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.level.Level;

public class SimpleInputProcessor implements InputProcessor, GestureListener
{

	private Stage stage;
	private Level level;
	private OrthographicCamera camera;

	private boolean w, a, s, d, q, e;

	public SimpleInputProcessor(Stage stage, Level level)
	{
		this.stage = stage;
		this.camera = (OrthographicCamera) stage.getCamera();
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
		q = Gdx.input.isKeyPressed(Input.Keys.Q);
		e = Gdx.input.isKeyPressed(Input.Keys.E);
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);

		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		q = Gdx.input.isKeyPressed(Input.Keys.Q);
		e = Gdx.input.isKeyPressed(Input.Keys.E);
		w = Gdx.input.isKeyPressed(Input.Keys.W);
		a = Gdx.input.isKeyPressed(Input.Keys.A);
		s = Gdx.input.isKeyPressed(Input.Keys.S);
		d = Gdx.input.isKeyPressed(Input.Keys.D);

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
			
			level.getPlayer().setVelX(dx);
			level.getPlayer().setVelY(dy);
			
		}
		else
		{
			level.getPlayer().setVelX(0);
			level.getPlayer().setVelY(0);
		}
	}

}
