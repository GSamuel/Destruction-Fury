package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AssetManager
{
	private AssetLoader assetLoader;
	private TexturePack texturePack;
	private HashMap<String, AnimationPack> animationPacks;
	private HashMap<String, Animation> animations;
	
	public AssetManager()
	{
		animationPacks = new HashMap<String, AnimationPack>();
		animations = new HashMap<String, Animation>();
		
		assetLoader = new AssetLoader();
		assetLoader.loadAssets();
		
		texturePack = new SimpleTexturepack(assetLoader);
		animationPacks.put("player", new PlayerAnimation(assetLoader));
		animationPacks.put("robot", new RobotAnimation(assetLoader));
		
		animations.put("explosion", assetLoader.getAnimation("explosion"));
	}
	
	public Animation getAnimation(String animationName)
	{
		return animations.get(animationName);
	}
	
	public AnimationPack getAnimationPack(String packName)
	{
		return animationPacks.get(packName);
	}
	
	public TexturePack getTexturePack()
	{
		return texturePack;
	}

	public AssetLoader getAssetLoader()
	{
		return assetLoader;
	}
}
