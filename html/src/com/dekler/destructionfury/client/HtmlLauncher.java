package com.dekler.destructionfury.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dekler.destructionfury.DestructionFury;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
        	GwtApplicationConfiguration gwtConfig =  new GwtApplicationConfiguration(1408, 832);
                return gwtConfig;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new DestructionFury(new HTMLLevelLoaderExporter(),true);
        }
}