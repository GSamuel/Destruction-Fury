package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public class PlayerAnimation implements AnimationPack
{
	private HashMap<AnimationEnum, Animation> animations;
	
	public PlayerAnimation(AssetLoader assetLoader)
	{
		animations = new HashMap<AnimationEnum, Animation>();
		animations.put(AnimationEnum.WALK_DOWN,assetLoader.getAnimation("player_down"));
		//animations.put(AnimationEnum.WALK_UP,assetLoader.getAnimation("player_up"));
		//animations.put(AnimationEnum.WALK_LEFT,assetLoader.getAnimation("player_left"));
		//animations.put(AnimationEnum.WALK_RIGHT,assetLoader.getAnimation("player_right"));
	}

	@Override
	public Animation getAnimation(AnimationEnum animation)
	{
		return animations.get(animation);
	}
}
