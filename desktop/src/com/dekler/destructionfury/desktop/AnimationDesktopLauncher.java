package com.dekler.destructionfury.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dekler.destructionfury.AnimationTest;

public class AnimationDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Destruction Fury";
		config.useGL30 = false;
		config.width = 960;
		config.height = 640;
		
		new LwjglApplication(new AnimationTest(), config);
	}
}
