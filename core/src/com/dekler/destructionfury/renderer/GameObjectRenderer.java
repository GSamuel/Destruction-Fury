package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Knife;
import com.dekler.destructionfury.gameobject.WarpPad;
import com.dekler.destructionfury.level.Level;

public class GameObjectRenderer
{
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.addAll(level.getGameObjects());
		objects.addAll(level.getHurtables());

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		for (GameObject o : objects)
		{
			Sprite sprite;
			if (o instanceof WarpPad)
			{
				WarpPad pad = (WarpPad) o;
				if (pad.isActive())
					sprite = assetManager.getTexturePack().getSprite("warp1");
				else
					sprite = assetManager.getTexturePack().getSprite("warp2");
				
				sprite.setPosition(pad.getX()*tileSize, pad.getY()*tileSize);

				sprite.draw(batch);
			}
			else if(o instanceof Knife)
			{
				Knife k  =(Knife) o;
				sprite = assetManager.getTexturePack().getSprite("knife");
				sprite.setPosition(o.getX()*tileSize, o.getY()*tileSize-tileSize);
				sprite.setRotation(k.getRotation());
				sprite.setScale(0.28f);
				sprite.draw(batch);
			}
			
		}

		batch.end();
	}
}
