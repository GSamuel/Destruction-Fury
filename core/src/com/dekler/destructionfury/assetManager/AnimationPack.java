package com.dekler.destructionfury.assetManager;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.dekler.destructionfury.map.AnimationEnum;

public interface AnimationPack
{
	public abstract Animation getAnimation(AnimationEnum animation);
}
