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
		textureAtlas = new TextureAtlas(Gdx.files.internal("destructionFury.pack"));
		atlasses.add(textureAtlas);

		tiles.put("health", textureAtlas.findRegion("health"));
		tiles.put("bomb_ui", textureAtlas.findRegion("bomb_UI"));
		

		tiles.put("floor", textureAtlas.findRegion("tile_floor", 1));
		tiles.put("crate_floor", textureAtlas.findRegion("tile_floor",2));
		tiles.put("warp1", textureAtlas.findRegion("warp_pad", 1));
		tiles.put("warp2", textureAtlas.findRegion("warp_pad", 2));
		tiles.put("knife", textureAtlas.findRegion("knife"));
		tiles.put("grenade", textureAtlas.findRegion("bomb"));
		tiles.put("spit", textureAtlas.findRegion("boss_projectile"));
		
		
		tiles.put("wooden_crate_front", textureAtlas.findRegion("crates/wooden_crate", 1));
		tiles.put("wooden_crate_top", textureAtlas.findRegion("crates/wooden_crate", 2));
		tiles.put("metal_crate_front", textureAtlas.findRegion("crates/metal_crate", 1));
		tiles.put("metal_crate_top", textureAtlas.findRegion("crates/metal_crate", 2));

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
		
		loadBossAnimations(textureAtlas);
		loadBullAnimations(textureAtlas);
		loadExplosionAnimation(textureAtlas);
		loadScientistAnimation(textureAtlas);
	}
	
	private void loadBossAnimations(TextureAtlas textureAtlas)
	{
		TextureRegion[] walkDownFrames = new TextureRegion[4];

		walkDownFrames[0] = textureAtlas.findRegion("boss_walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("boss_walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("boss_walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("boss_walk_down", 3);

		animations.put("boss_down", new Animation(frameTime, walkDownFrames));
		
		TextureRegion[] walkUpFrames = new TextureRegion[4];

		walkUpFrames[0] = textureAtlas.findRegion("boss_walk_up", 2);
		walkUpFrames[1] = textureAtlas.findRegion("boss_walk_up", 1);
		walkUpFrames[2] = textureAtlas.findRegion("boss_walk_up", 2);
		walkUpFrames[3] = textureAtlas.findRegion("boss_walk_up", 3);

		animations.put("boss_up", new Animation(frameTime, walkUpFrames));
		
		
		TextureRegion[] walkLeftFrames = new TextureRegion[4];
		TextureRegion[] walkRightFrames = new TextureRegion[4];
		
		walkRightFrames[0] = textureAtlas.findRegion("boss_walk_right", 2);
		walkRightFrames[1] = textureAtlas.findRegion("boss_walk_right", 1);
		walkRightFrames[2] = textureAtlas.findRegion("boss_walk_right", 2);
		walkRightFrames[3] = textureAtlas.findRegion("boss_walk_right", 3);
		
		walkLeftFrames[0] = textureAtlas.findRegion("boss_walk_left", 2);
		walkLeftFrames[1] = textureAtlas.findRegion("boss_walk_left", 1);
		walkLeftFrames[2] = textureAtlas.findRegion("boss_walk_left", 2);
		walkLeftFrames[3] = textureAtlas.findRegion("boss_walk_left", 3);
		
		for(int i =0; i < walkLeftFrames.length; i++)
		{
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}

		animations.put("boss_right", new Animation(frameTime, walkRightFrames));
		animations.put("boss_left", new Animation(frameTime, walkLeftFrames));

		TextureRegion[] mouthOpenRight = {textureAtlas.findRegion("boss_open_right")};
		TextureRegion[] mouthOpenLeft = {new TextureRegion(mouthOpenRight[0])};
		mouthOpenLeft[0].flip(true, false);
		TextureRegion[] mouthOpenUp = {textureAtlas.findRegion("boss_open_up")};
		TextureRegion[] mouthOpenDown = {textureAtlas.findRegion("boss_open_down")};

		animations.put("boss_right_mouth",new Animation(frameTime, mouthOpenRight));
		animations.put("boss_left_mouth",new Animation(frameTime, mouthOpenLeft));
		animations.put("boss_up_mouth",new Animation(frameTime, mouthOpenUp));
		animations.put("boss_down_mouth",new Animation(frameTime, mouthOpenDown));
	}
	
	private void loadBullAnimations(TextureAtlas textureAtlas)
	{
		TextureRegion[] walkDownFrames = new TextureRegion[4];

		walkDownFrames[0] = textureAtlas.findRegion("bull_walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("bull_walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("bull_walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("bull_walk_down", 3);

		animations.put("bull_down", new Animation(frameTime, walkDownFrames));
		
		TextureRegion[] walkUpFrames = new TextureRegion[4];

		walkUpFrames[0] = textureAtlas.findRegion("bull_walk_up", 2);
		walkUpFrames[1] = textureAtlas.findRegion("bull_walk_up", 1);
		walkUpFrames[2] = textureAtlas.findRegion("bull_walk_up", 2);
		walkUpFrames[3] = textureAtlas.findRegion("bull_walk_up", 3);

		animations.put("bull_up", new Animation(frameTime, walkUpFrames));
		
		
		TextureRegion[] walkLeftFrames = new TextureRegion[4];
		TextureRegion[] walkRightFrames = new TextureRegion[4];
		
		walkRightFrames[0] = textureAtlas.findRegion("bull_walk_right", 2);
		walkRightFrames[1] = textureAtlas.findRegion("bull_walk_right", 1);
		walkRightFrames[2] = textureAtlas.findRegion("bull_walk_right", 2);
		walkRightFrames[3] = textureAtlas.findRegion("bull_walk_right", 3);
		
		walkLeftFrames[0] = textureAtlas.findRegion("bull_walk_left", 2);
		walkLeftFrames[1] = textureAtlas.findRegion("bull_walk_left", 1);
		walkLeftFrames[2] = textureAtlas.findRegion("bull_walk_left", 2);
		walkLeftFrames[3] = textureAtlas.findRegion("bull_walk_left", 3);
		
		for(int i =0; i < walkLeftFrames.length; i++)
		{
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}

		animations.put("bull_right", new Animation(frameTime, walkRightFrames));
		animations.put("bull_left", new Animation(frameTime, walkLeftFrames));

		TextureRegion[] chargeRight = {textureAtlas.findRegion("bull_charge_right")};
		TextureRegion[] chargeLeft = {new TextureRegion(chargeRight[0])};
		chargeLeft[0].flip(true, false);
		TextureRegion[] chargeUp = {textureAtlas.findRegion("bull_charge_up")};
		TextureRegion[] chargeDown = {textureAtlas.findRegion("bull_charge_down")};

		animations.put("bull_charge_right", new Animation(frameTime, chargeRight));
		animations.put("bull_charge_left", new Animation(frameTime, chargeLeft));
		animations.put("bull_charge_up", new Animation(frameTime, chargeUp));
		animations.put("bull_charge_down", new Animation(frameTime, chargeDown));
		
		TextureRegion[] stuckRight = new TextureRegion[2];
		stuckRight[0] = textureAtlas.findRegion("bull_stuck_right",1);
		stuckRight[1] = textureAtlas.findRegion("bull_stuck_right",2);
				
		TextureRegion[] stuckLeft = new TextureRegion[2];
		for(int i =0; i < stuckLeft.length; i++)
		{
			stuckLeft[i] = new TextureRegion(stuckRight[i]);
			stuckLeft[i].flip(true, false);
		}
		
		TextureRegion[] stuckUp = new TextureRegion[2];
		stuckUp[0] = textureAtlas.findRegion("bull_stuck_up",1);
		stuckUp[1] = textureAtlas.findRegion("bull_stuck_up",2);
		
		TextureRegion[] stuckDown = new TextureRegion[2];
		stuckDown[0] = textureAtlas.findRegion("bull_stuck_down",1);
		stuckDown[1] = textureAtlas.findRegion("bull_stuck_down",2);

		animations.put("bull_stuck_right", new Animation(frameTime, stuckRight));
		animations.put("bull_stuck_left", new Animation(frameTime, stuckLeft));
		animations.put("bull_stuck_up", new Animation(frameTime, stuckUp));
		animations.put("bull_stuck_down", new Animation(frameTime, stuckDown));
	}
	
	private void loadScientistAnimation(TextureAtlas textureAtlas)
	{
		TextureRegion[] walkDownFrames = new TextureRegion[4];

		walkDownFrames[0] = textureAtlas.findRegion("scienstein_walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("scienstein_walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("scienstein_walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("scienstein_walk_down", 3);

		animations.put("scientist_down", new Animation(frameTime, walkDownFrames));
		
		TextureRegion[] walkUpFrames = new TextureRegion[4];

		walkUpFrames[0] = textureAtlas.findRegion("scienstein_walk_up", 2);
		walkUpFrames[1] = textureAtlas.findRegion("scienstein_walk_up", 1);
		walkUpFrames[2] = textureAtlas.findRegion("scienstein_walk_up", 2);
		walkUpFrames[3] = textureAtlas.findRegion("scienstein_walk_up", 3);

		animations.put("scientist_up", new Animation(frameTime, walkUpFrames));
		
		
		TextureRegion[] walkLeftFrames = new TextureRegion[4];
		TextureRegion[] walkRightFrames = new TextureRegion[4];
		
		walkRightFrames[0] = textureAtlas.findRegion("scienstein_walk_right", 2);
		walkRightFrames[1] = textureAtlas.findRegion("scienstein_walk_right", 1);
		walkRightFrames[2] = textureAtlas.findRegion("scienstein_walk_right", 2);
		walkRightFrames[3] = textureAtlas.findRegion("scienstein_walk_right", 3);
		
		walkLeftFrames[0] = textureAtlas.findRegion("scienstein_walk_left", 2);
		walkLeftFrames[1] = textureAtlas.findRegion("scienstein_walk_left", 1);
		walkLeftFrames[2] = textureAtlas.findRegion("scienstein_walk_left", 2);
		walkLeftFrames[3] = textureAtlas.findRegion("scienstein_walk_left", 3);
		
		for(int i =0; i < walkLeftFrames.length; i++)
		{
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}

		animations.put("scientist_right", new Animation(frameTime, walkRightFrames));
		animations.put("scientist_left", new Animation(frameTime, walkLeftFrames));
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
