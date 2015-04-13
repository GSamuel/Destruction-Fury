package com.dekler.destructionfury.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dekler.destructionfury.DestructionFury;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Destruction Fury";
		config.useGL30 = false;
		config.width = 1280;
		config.height = 720;
		
		new LwjglApplication(new DestructionFury(new LevelLoaderExporter()), config);
	}
}
