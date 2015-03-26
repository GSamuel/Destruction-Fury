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
		textureAtlas = new TextureAtlas(Gdx.files.internal("walk-down.pack"));
		atlasses.add(textureAtlas);

		TextureRegion[] walkDownFrames = new TextureRegion[4];

		walkDownFrames[0] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[1] = textureAtlas.findRegion("walk_down", 1);
		walkDownFrames[2] = textureAtlas.findRegion("walk_down", 2);
		walkDownFrames[3] = textureAtlas.findRegion("walk_down", 3);

		animations.put("player_down", new Animation(0.25f, walkDownFrames));
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
