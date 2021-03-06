package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dekler.destructionfury.assetManager.TexturePack;
import com.dekler.destructionfury.level.Level;
import com.dekler.destructionfury.map.TileEnum;
import com.dekler.destructionfury.map.TiledMap;

public class MapRenderer
{
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, TexturePack texturePack, int tileSize)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		TiledMap map = level.getMap();

		batch.setProjectionMatrix(camera.combined);

		Sprite wall = texturePack.getSprite(TileEnum.WALL);
		wall.setSize(tileSize, tileSize);		
		Sprite floor = texturePack.getSprite(TileEnum.FLOOR);
		floor.setSize(tileSize, tileSize);
		Sprite crateFloor = texturePack.getSprite(TileEnum.CRATE_FLOOR);
		crateFloor.setSize(tileSize, tileSize);
		Sprite crateTarget = texturePack.getSprite(TileEnum.CRATE_TARGET);
		crateTarget.setSize(tileSize, tileSize);
		Sprite breakWall = texturePack.getSprite(TileEnum.BREAKABLE_WALL);
		breakWall.setSize(tileSize, tileSize);

		TileEnum t;
		batch.begin();
		for (int i = 0; i < map.getWidth(); i++)
			for (int j = 0; j < map.getHeight(); j++)
			{
				t = map.getTile(i, j);
				if (t == TileEnum.WALL)
				{
					wall.setPosition(i * tileSize, j * tileSize);
					wall.draw(batch);
				} else if (t == TileEnum.CRATE_FLOOR)
				{
					crateFloor.setPosition(i * tileSize, j * tileSize);
					crateFloor.draw(batch);
				} else if (t == TileEnum.CRATE_TARGET)
				{
					crateTarget.setPosition(i * tileSize, j * tileSize);
					crateTarget.draw(batch);
				}else if (t == TileEnum.BREAKABLE_WALL)
				{
					breakWall.setPosition(i * tileSize, j * tileSize);
					breakWall.draw(batch);
				} else
				{
					floor.setPosition(i * tileSize, j * tileSize);
					floor.draw(batch);
				}
			}
		batch.end();
	}
}
