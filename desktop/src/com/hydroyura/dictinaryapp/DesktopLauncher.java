package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("dictionary-app");
        config.setWindowedMode(400, 800);

        new Lwjgl3Application(new ApplicationStarter(new DesktopRepository()), config);
    }
}
