package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hydroyura.dictinaryapp.data.FileDesktopRepository;

import java.util.Map;

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

		Map<String, String> files = Map.of(
				FileDesktopRepository.FILE_NAMES.WORDS.toString(), "words.json",
				FileDesktopRepository.FILE_NAMES.LANG.toString(), "",
				FileDesktopRepository.FILE_NAMES.COLLECTION.toString(), "",
				FileDesktopRepository.FILE_NAMES.TYPES.toString(), ""
		);

		new Lwjgl3Application(new AppStarter(new FileDesktopRepository("", files)), config);
	}
}
