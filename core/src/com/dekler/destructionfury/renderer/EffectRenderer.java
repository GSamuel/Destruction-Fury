package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Explosion;
import com.dekler.destructionfury.level.Level;

public class EffectRenderer
{
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		ArrayList<Explosion> effects = level.getEffects();
		batch.setProjectionMatrix(camera.combined);
		for (Explosion e : effects)
		{
			Animation ani = assetManager.getAnimation("explosion");

			if (e.getTime() > ani.getAnimationDuration())
			{
				level.getEntities().remove(e);
			} else
			{
				TextureRegion region = ani.getKeyFrame(e.getTime(), true);

				Sprite sprite = new Sprite(region);
				sprite.setScale(0.5f);
				sprite.setPosition(e.getX() * tileSize + tileSize * 0.5f, e.getY()
						* tileSize- tileSize*0.5f);
				sprite.translate(-sprite.getWidth() * 0.5f,
						(-sprite.getHeight() + (sprite.getHeight() * sprite
								.getScaleY())) * 0.5f);
				batch.begin();
				sprite.draw(batch);
				batch.end();
			}
		}

	}
}
