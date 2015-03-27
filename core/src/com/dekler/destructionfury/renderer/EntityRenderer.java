package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dekler.destructionfury.assetManager.AnimationPack;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.gameobject.Robot;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.AnimationEnum;

public class EntityRenderer
{
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		ArrayList<Entity> entities = level.getEntities();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		for (Entity e : entities)
		{
			AnimationPack aniPack = null;
			if(e instanceof Player)
				aniPack = assetManager.getAnimationPack("player");
			if(e instanceof Robot)
				aniPack = assetManager.getAnimationPack("robot");
			if(aniPack == null)
				aniPack = assetManager.getAnimationPack("player");
			
			Animation ani;
			switch (e.getDirection())
			{
			case DOWN:
				ani = aniPack.getAnimation(AnimationEnum.WALK_DOWN);
				break;
			case LEFT:
				ani = aniPack.getAnimation(AnimationEnum.WALK_LEFT);
				break;
			case RIGHT:
				ani = aniPack.getAnimation(AnimationEnum.WALK_RIGHT);
				break;
			case UP:
				ani = aniPack.getAnimation(AnimationEnum.WALK_UP);
				break;
			default:
				ani = aniPack.getAnimation(AnimationEnum.WALK_DOWN);
				break;
			}

			TextureRegion region = ani.getKeyFrame(e.getTime(), true);
			Sprite sprite = new Sprite(region);
			sprite.setScale(1.0f * tileSize / 512);
			sprite.setPosition(e.getX() * tileSize + tileSize * 0.5f, e.getY()
					* tileSize);
			sprite.translate(-sprite.getWidth() * 0.5f,
					(-sprite.getHeight() + (sprite.getHeight() * sprite
							.getScaleY())) * 0.5f);
			sprite.draw(batch);

		}
		batch.end();
	}
}
