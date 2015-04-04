package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Player;
import com.dekler.destructionfury.level.Level;

public class UIRenderer
{

	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		Player player = level.getPlayer();
		int health = player.getHealth();
		Sprite sprite = assetManager.getTexturePack().getSprite("health");
		
		batch.begin();
		for(int i=0; i < health; i++)
		{
			sprite.setPosition(camera.position.x-Gdx.graphics.getWidth()*0.5f+tileSize*0.38f*i, camera.position.y-Gdx.graphics.getHeight()*0.5f);
			sprite.draw(batch);
		}
		batch.end();
			
	}
}
