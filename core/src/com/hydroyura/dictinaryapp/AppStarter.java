package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hydroyura.dictinaryapp.screens.main.MainScreen;
import com.hydroyura.dictinaryapp.screens.splash.SplashScreen;


public class AppStarter extends Game {

	private AssetManager assetManager = new AssetManager();
	private Screen mainScreen = new MainScreen();
	private Screen splashScreen = new SplashScreen();

	@Override
	public void create() {
		loadResources();
		setScreen(new SplashScreen());
	}

	@Override
	public void dispose() {}

	private void loadResources() {
		assetManager.load("skin/skin-composer-ui.json", Skin.class);
	}

	public float getLoadingProgress() {
		return assetManager.getProgress();
	}

	public <T> T getResource(String resourceName, Class<T> clazz) {
		return assetManager.get(resourceName, clazz);
	}

	public boolean getAssetManagerUpdate() {
		return assetManager.update();
	}

	public void setMainScreen() {
		setScreen(mainScreen);
	}
}
