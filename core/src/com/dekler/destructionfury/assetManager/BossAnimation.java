package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public class BossAnimation implements AnimationPack
{
private HashMap<AnimationEnum, Animation> animations;
	
	public BossAnimation(AssetLoader assetLoader)
	{
		animations = new HashMap<AnimationEnum, Animation>();
		animations.put(AnimationEnum.WALK_DOWN,assetLoader.getAnimation("boss_down"));
		animations.put(AnimationEnum.WALK_UP,assetLoader.getAnimation("boss_up"));
		animations.put(AnimationEnum.WALK_LEFT,assetLoader.getAnimation("boss_left"));
		animations.put(AnimationEnum.WALK_RIGHT,assetLoader.getAnimation("boss_right"));
		
		animations.put(AnimationEnum.ATTACK_RIGHT, assetLoader.getAnimation("boss_right_mouth"));
		animations.put(AnimationEnum.ATTACK_LEFT, assetLoader.getAnimation("boss_left_mouth"));
		animations.put(AnimationEnum.ATTACK_UP, assetLoader.getAnimation("boss_up_mouth"));
		animations.put(AnimationEnum.ATTACK_DOWN, assetLoader.getAnimation("boss_down_mouth"));
	}

	@Override
	public Animation getAnimation(AnimationEnum animation)
	{
		return animations.get(animation);
	}
}
