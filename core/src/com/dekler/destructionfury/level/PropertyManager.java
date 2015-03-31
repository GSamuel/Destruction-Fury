package com.dekler.destructionfury.level;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

public class PropertyManager
{
	private Properties properties;
	private String fileName;
	
	public PropertyManager(String fileName, Properties properties)
	{
		this.fileName = fileName;
		this.properties = properties;
	}
	
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	public int getIntegerProperty(String key)
	{
		return Integer.parseInt(properties.getProperty(key));
	}
	
	public Color getColorProperty(String key)
	{
		String s = properties.getProperty(key);
		String[] split = s.split(",");
		float r = 1.0f * Integer.parseInt(split[0])/255;
		float g = 1.0f * Integer.parseInt(split[1])/255;
		float b = 1.0f * Integer.parseInt(split[2])/255;
		return new Color(r,g,b, 1.0f);
	}
	
	public void putProperty(Object key, Object value)
	{
		properties.put(key, value);
	}

	public void readPropertyFile()
	{
		FileHandle path = Gdx.files.local(fileName);
		if(Gdx.files.isLocalStorageAvailable() && path.exists())
		{
			InputStream input = path.read();
			
			try
			{
				properties.load(input);
			} catch (IOException e)
			{
				//logger.error("can't read properties from "+fileName);
			}
			finally
			{
				if(input != null)
					try
					{
						input.close();
					} catch (IOException e)
					{
						//logger.error("can't close InputStream "+fileName);
					}
			}

		}
	}

	public void writePropertyFile()
	{
		FileHandle path = Gdx.files.local(fileName);

		if (Gdx.files.isLocalStorageAvailable())
		{
			OutputStream output = path.write(false);
			try
			{
				properties.store(output, null);
			} catch (IOException e)
			{
				//logger.error("can't save properties to file "+fileName);
			} finally
			{
				if (output != null)
					try
					{
						output.close();
					} catch (IOException e)
					{
						//logger.error("can't close OutputStream "+fileName);
					}
			}
		}

	}
	
	public void showAll()
	{
		for(Object o:properties.values())
			System.out.println(o.toString());
	}

}
