package com.dekler.destructionfury.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.dekler.destructionfury.map.Tile;
import com.dekler.destructionfury.map.TiledMap;

public class MapRenderer implements Disposable
{
	private TiledMap map;
	
	//View Stuff
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	
	private ShapeRenderer shapeRenderer;
	
	public MapRenderer(TiledMap map)
	{
		this.map = map;
		
		stage = new Stage();
		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 960, 640);

		stage.getViewport().setCamera(camera);
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//update camera position
		
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		

	    shapeRenderer.setColor(Color.LIGHT_GRAY);
	    shapeRenderer.begin(ShapeType.Filled);
	    
	    Tile t;
		for(int i = 0; i <map.getWidth(); i++)
			for(int j =0; j < map.getHeight(); j++)
			{
				t = map.getTile(i, j);
				if(t == Tile.WALL)
				{
					shapeRenderer.setColor(Color.GRAY);
					shapeRenderer.rect(i*50,j*50,50,50);
				}
				else
				{
				    shapeRenderer.setColor(Color.LIGHT_GRAY);
					shapeRenderer.rect(i*50,j*50,50,50);
				}
					
				
			}
		shapeRenderer.end();
				
				
			
	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
