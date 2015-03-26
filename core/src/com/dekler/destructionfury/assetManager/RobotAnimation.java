package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public class RobotAnimation implements AnimationPack
{

	private HashMap<AnimationEnum, Animation> animations;
	
	public RobotAnimation(AssetLoader assetLoader)
	{
		animations = new HashMap<AnimationEnum, Animation>();
		animations.put(AnimationEnum.WALK_DOWN,assetLoader.getAnimation("ride_down"));
		animations.put(AnimationEnum.WALK_UP,assetLoader.getAnimation("ride_up"));
		animations.put(AnimationEnum.WALK_LEFT,assetLoader.getAnimation("ride_left"));
		animations.put(AnimationEnum.WALK_RIGHT,assetLoader.getAnimation("ride_right"));
	}

	@Override
	public Animation getAnimation(AnimationEnum animation)
	{
		return animations.get(animation);
	}

}
