package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dekler.destructionfury.assetManager.AnimationPack;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Boss;
import com.dekler.destructionfury.gameobject.Direction;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Knife;
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

			Animation ani = null;
			
			if(e instanceof Boss)
			{
				Boss b = (Boss) e;
				aniPack = assetManager.getAnimationPack("boss");
				if(b.mouthIsOpen())switch (e.getDirection())
				{
				case DOWN:
					ani = aniPack.getAnimation(AnimationEnum.ATTACK_DOWN);
					break;
				case LEFT:
					ani = aniPack.getAnimation(AnimationEnum.ATTACK_LEFT);
					break;
				case RIGHT:
					ani = aniPack.getAnimation(AnimationEnum.ATTACK_RIGHT);
					break;
				case UP:
					ani = aniPack.getAnimation(AnimationEnum.ATTACK_UP);
					break;
				default:
					ani = aniPack.getAnimation(AnimationEnum.ATTACK_DOWN);
					break;
				}
					
			}
			if(ani == null)
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
			int hurtTime = e.getDamageTimer();
			if(hurtTime>0)
				sprite.setColor(Color.RED);
			if(e instanceof Boss)
				sprite.setScale(1.0f * tileSize / 256);
			else
				sprite.setScale(1.0f * tileSize / 512);
			sprite.setPosition(e.getX() * tileSize + tileSize * 0.5f, e.getY()
					* tileSize);
			sprite.translate(-sprite.getWidth() * 0.5f,
					(-sprite.getHeight() + (sprite.getHeight() * sprite
							.getScaleY())) * 0.5f);
			
			if(e instanceof Boss)
				sprite.translate(0.5f * tileSize, 0);
			
			if(e instanceof Player && e.getDirection() != Direction.DOWN)
				drawKnife(level, batch, camera, assetManager, tileSize);
			
			sprite.draw(batch);
			
			if(e instanceof Player && e.getDirection() == Direction.DOWN)
				drawKnife(level, batch, camera, assetManager, tileSize);

		}
		batch.end();
	}
	
	private static void drawKnife(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		for(GameObject o :level.getHurtables())
			if(o instanceof Knife)
			{
				Knife k  =(Knife) o;
				Sprite sprite = assetManager.getTexturePack().getSprite("knife");
				sprite.setPosition(o.getX()*tileSize, o.getY()*tileSize-tileSize);
				sprite.setRotation(k.getRotation());
				sprite.setScale(0.28f);
				sprite.draw(batch);
			}
	}
}
