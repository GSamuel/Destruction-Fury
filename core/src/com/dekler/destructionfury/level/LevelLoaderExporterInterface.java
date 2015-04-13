package com.dekler.destructionfury.level;

public interface LevelLoaderExporterInterface
{
	public void loadLevel(Level level, PropertyManager propManager);
	public void saveLevel(Level level, String fileName,PropertyManager propManager);
}
