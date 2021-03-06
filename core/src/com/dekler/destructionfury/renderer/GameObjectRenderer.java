package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.Crate;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.gameobject.Grenade;
import com.dekler.destructionfury.gameobject.Spit;
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
			else if (o instanceof Crate)
			{
				sprite = assetManager.getTexturePack().getSprite("crate");
				sprite.setSize(o.getHeight()*tileSize, o.getHeight()*tileSize);
				sprite.setPosition(o.getX()*tileSize, o.getY()*tileSize);
				sprite.draw(batch);
			}
			else if (o instanceof Grenade)
			{
				sprite = assetManager.getTexturePack().getSprite("grenade");
				sprite.setSize(o.getWidth()*tileSize, o.getHeight()*tileSize);
				sprite.setPosition(o.getX()*tileSize, o.getY()*tileSize);
				sprite.draw(batch);
			}
			else if (o instanceof Spit)
			{
				sprite = assetManager.getTexturePack().getSprite("spit");
				sprite.setSize(o.getWidth()*tileSize, o.getHeight()*tileSize);
				sprite.setPosition(o.getX()*tileSize, o.getY()*tileSize);
				sprite.draw(batch);
			}
			
		}

		batch.end();
	}
}
