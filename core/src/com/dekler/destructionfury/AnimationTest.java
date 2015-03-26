package com.dekler.destructionfury;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTest extends Game
{
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime = 0;

	public void create()
	{
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("walk-down.pack"));

		TextureRegion[] walkDownFrames = new TextureRegion[4];
		
		walkDownFrames[0] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("walk_down", 3);
		
		animation = new Animation(0.25f, walkDownFrames);
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		// sprite.draw(batch);
		elapsedTime += Gdx.graphics.getDeltaTime();
		TextureRegion region = animation.getKeyFrame(elapsedTime, true);
		batch.draw(region, 512-region.getRegionWidth()*0.5f, 0);
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

}
