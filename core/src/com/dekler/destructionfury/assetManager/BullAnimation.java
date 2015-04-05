package com.dekler.destructionfury.assetManager;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public class BullAnimation implements AnimationPack
{
	private HashMap<AnimationEnum, Animation> animations;

	public BullAnimation(AssetLoader assetLoader)
	{
		animations = new HashMap<AnimationEnum, Animation>();
		animations.put(AnimationEnum.WALK_DOWN,
				assetLoader.getAnimation("bull_right"));
		animations.put(AnimationEnum.WALK_UP,
				assetLoader.getAnimation("bull_left"));
		animations.put(AnimationEnum.WALK_LEFT,
				assetLoader.getAnimation("bull_left"));
		animations.put(AnimationEnum.WALK_RIGHT,
				assetLoader.getAnimation("bull_right"));

		animations.put(AnimationEnum.ATTACK_RIGHT,
				assetLoader.getAnimation("bull_charge_right"));
		animations.put(AnimationEnum.ATTACK_LEFT,
				assetLoader.getAnimation("bull_charge_left"));
		animations.put(AnimationEnum.ATTACK_UP,
				assetLoader.getAnimation("bull_charge_left"));
		animations.put(AnimationEnum.ATTACK_DOWN,
				assetLoader.getAnimation("bull_charge_right"));

		animations.put(AnimationEnum.STUCK_RIGHT,
				assetLoader.getAnimation("bull_stuck_right"));
		animations.put(AnimationEnum.STUCK_LEFT,
				assetLoader.getAnimation("bull_stuck_left"));
		animations.put(AnimationEnum.STUCK_UP,
				assetLoader.getAnimation("bull_stuck_left"));
		animations.put(AnimationEnum.STUCK_DOWN,
				assetLoader.getAnimation("bull_stuck_right"));
	}

	@Override
	public Animation getAnimation(AnimationEnum animation)
	{
		return animations.get(animation);
	}

}
