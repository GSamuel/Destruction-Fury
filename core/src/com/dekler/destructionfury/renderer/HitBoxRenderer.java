package com.dekler.destructionfury.renderer;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dekler.destructionfury.assetManager.AssetManager;
import com.dekler.destructionfury.gameobject.GameObject;
import com.dekler.destructionfury.level.Level;

public class HitBoxRenderer {
	
	private static ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public static void render(Level level, SpriteBatch batch,
			OrthographicCamera camera, AssetManager assetManager, int tileSize)
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.addAll(level.getEntities());
		objects.addAll(level.getGameObjects());
		objects.addAll(level.getHurtables());
		
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
	      batch.begin();
	      shapeRenderer.setColor(Color.RED);
	      shapeRenderer.begin(ShapeType.Line);
	      for(GameObject o: objects)
	    	  shapeRenderer.rect(o.getX()*tileSize, o.getY()*tileSize, o.getWidth()*tileSize, o.getHeight()*tileSize);
	      shapeRenderer.end();
	      batch.end();  
	}
}
