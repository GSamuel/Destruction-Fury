package com.dekler.destructionfury.assetManager;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class AssetLoader implements Disposable
{
	private TextureAtlas textureAtlas;
	private ArrayList<TextureAtlas> atlasses;
	private HashMap<String, AtlasRegion> tiles;
	private HashMap<String, Animation> animations;
	
	private float frameTime = 0.2f;

	public AssetLoader()
	{
		atlasses = new ArrayList<TextureAtlas>();//Dispose list
		tiles = new HashMap<String, TextureAtlas.AtlasRegion>();
		animations = new HashMap<String, Animation>();
	}


	public void loadAssets()
	{
		textureAtlas = new TextureAtlas(Gdx.files.internal("tiles.pack"));
		atlasses.add(textureAtlas);

		tiles.put("water", textureAtlas.findRegion("tile-water"));
		tiles.put("stone", textureAtlas.findRegion("tile-stone"));
		tiles.put("grass", textureAtlas.findRegion("tile-grass"));
		tiles.put("sand", textureAtlas.findRegion("tile-sand"));
		tiles.put("wood", textureAtlas.findRegion("tile-wood"));

		
		//walk down
		textureAtlas = new TextureAtlas(Gdx.files.internal("player.pack"));
		atlasses.add(textureAtlas);
		

		tiles.put("floor", textureAtlas.findRegion("tile_floor", 1));
		tiles.put("warp1", textureAtlas.findRegion("warp_pad", 1));
		tiles.put("warp2", textureAtlas.findRegion("warp_pad", 2));

		TextureRegion[] walkDownFrames = new TextureRegion[4];

		walkDownFrames[0] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("walk_down", 3);

		animations.put("player_down", new Animation(frameTime, walkDownFrames));
		
		
		TextureRegion[] walkUpFrames = new TextureRegion[4];

		walkUpFrames[0] = textureAtlas.findRegion("walk_up", 2);
		walkUpFrames[1] = textureAtlas.findRegion("walk_up", 1);
		walkUpFrames[2] = textureAtlas.findRegion("walk_up", 2);
		walkUpFrames[3] = textureAtlas.findRegion("walk_up", 3);

		animations.put("player_up", new Animation(frameTime, walkUpFrames));
		
		
		TextureRegion[] walkLeftFrames = new TextureRegion[4];

		walkLeftFrames[0] = textureAtlas.findRegion("walk_left", 2);
		walkLeftFrames[1] = textureAtlas.findRegion("walk_left", 1);
		walkLeftFrames[2] = textureAtlas.findRegion("walk_left", 2);
		walkLeftFrames[3] = textureAtlas.findRegion("walk_left", 3);

		animations.put("player_left", new Animation(frameTime, walkLeftFrames));
		
		
		TextureRegion[] walkRightFrames = new TextureRegion[4];
		walkRightFrames[0] = textureAtlas.findRegion("walk_right", 2);
		walkRightFrames[1] = textureAtlas.findRegion("walk_right", 1);
		walkRightFrames[2] = textureAtlas.findRegion("walk_right", 2);
		walkRightFrames[3] = textureAtlas.findRegion("walk_right", 3);

		animations.put("player_right", new Animation(frameTime, walkRightFrames));
		

		TextureRegion[] rideDown = new TextureRegion[1];
		rideDown[0] = textureAtlas.findRegion("ride_down",1);
		TextureRegion[] rideUp = new TextureRegion[1];
		rideUp[0] = textureAtlas.findRegion("ride_up",1);
		TextureRegion[] rideLeft = new TextureRegion[1];
		rideLeft[0] = textureAtlas.findRegion("ride_left",1);
		TextureRegion[] rideRight = new TextureRegion[1];
		rideRight[0] = textureAtlas.findRegion("ride_right",1);
		animations.put("ride_down", new Animation(frameTime,rideDown));
		animations.put("ride_up", new Animation(frameTime,rideUp));
		animations.put("ride_left", new Animation(frameTime,rideLeft));
		animations.put("ride_right", new Animation(frameTime,rideRight));
		
		loadExplosionAnimation(textureAtlas);
	}
	
	private void loadExplosionAnimation(TextureAtlas textureAtlas)
	{
		TextureRegion[] explosion = new TextureRegion[6];
		for(int i=0; i <6; i++)
			explosion[i] = textureAtlas.findRegion("explosion", i+1);
		
		animations.put("explosion", new Animation(frameTime, explosion));
	}

	public TextureRegion getRegion(String textureName)
	{
		TextureRegion region = tiles.get(textureName);
		if (region == null)
			throw new NullPointerException();

		return region;
	}
	
	public Animation getAnimation(String animationName)
	{
		Animation animation = animations.get(animationName);
		if (animation == null)
			throw new NullPointerException();

		return animation;
	}

	@Override
	public void dispose()
	{
		for(TextureAtlas atlas: atlasses)
			atlas.dispose();
	}
}
