package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dekler.destructionfury.assetManager.AnimationPack;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Entity;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.AnimationEnum;

public class PlayerRenderer
{
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		Entity player = level.getPlayer();

		batch.setProjectionMatrix(camera.combined);
		AnimationPack aniPack = assetManager.getAnimationPack("robot");
		Animation ani;
		switch (player.getDirection())
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

		batch.begin();

		TextureRegion region = ani.getKeyFrame(player.getTime(), true);
		Sprite sprite = new Sprite(region);
		sprite.setScale(1.0f*tileSize/512);
		sprite.setPosition(player.getX() * tileSize + tileSize * 0.5f,
				player.getY() * tileSize);
		sprite.translate(
				-sprite.getWidth() * 0.5f,
				(-sprite.getHeight() + (sprite.getHeight() * sprite.getScaleY())) * 0.5f);
		sprite.draw(batch);

		batch.end();
	}
}
