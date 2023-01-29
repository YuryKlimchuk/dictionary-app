package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.data.Repository;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	static {
		System.getProperties().setProperty("vmArgs", "-XstartOnFirstThread");
	}

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("dictionary-app");
		config.setWindowedMode(400, 800);
		new Lwjgl3Application(new AppStarter(new Repository()), config);
	}
}
