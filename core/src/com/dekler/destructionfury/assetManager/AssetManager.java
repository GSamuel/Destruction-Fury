package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

public class AssetManager
{
	private AssetLoader assetLoader;
	private TexturePack texturePack;
	private HashMap<String, AnimationPack> animations;
	
	public AssetManager()
	{
		animations = new HashMap<String, AnimationPack>();
		
		assetLoader = new AssetLoader();
		assetLoader.loadAssets();
		
		texturePack = new SimpleTexturepack(assetLoader);
		animations.put("player", new PlayerAnimation(assetLoader));
		
		
	}
	
	public AnimationPack getAnimationPack(String packName)
	{
		return animations.get(packName);
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
