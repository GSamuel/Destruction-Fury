package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public class ScientistAnimation implements AnimationPack
{
	private HashMap<AnimationEnum, Animation> animations;

	public ScientistAnimation(AssetLoader assetLoader)
	{
		animations = new HashMap<AnimationEnum, Animation>();
		animations.put(AnimationEnum.WALK_DOWN,
				assetLoader.getAnimation("scientist_down"));
		animations.put(AnimationEnum.WALK_UP,
				assetLoader.getAnimation("scientist_up"));
		animations.put(AnimationEnum.WALK_LEFT,
				assetLoader.getAnimation("scientist_left"));
		animations.put(AnimationEnum.WALK_RIGHT,
				assetLoader.getAnimation("scientist_right"));
	}

	@Override
	public Animation getAnimation(AnimationEnum animation)
	{
		return animations.get(animation);
	}

}
