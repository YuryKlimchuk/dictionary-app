package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hydroyura.dictinaryapp.data.repository.IRepository;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;
import com.hydroyura.dictinaryapp.screens.main.MainScreen;
import com.hydroyura.dictinaryapp.screens.splash.SplashScreen;


public class AppStarter extends Game {

	private AssetManager assetManager = new AssetManager();
	private Screen mainScreen = new MainScreen();
	private Screen splashScreen = new SplashScreen();

	private HttpClient httpClient = new HttpClient();

	private IRepository repository;

	private AppStarter() {
		throw new RuntimeException("Forbidden to use");
	}

	public AppStarter(IRepository repository) {
		this.repository = repository;
	}

	@Override
	public void create() {
		loadResources();
		setScreen(new SplashScreen());
	}

	@Override
	public void dispose() {}

	public IRepository getRepository() {
		return repository;
	}

	private void loadResources() {
		assetManager.load("skin/skin-composer-ui.json", Skin.class);
		assetManager.load("skin/custom-skin.json", Skin.class);
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

	public HttpClient getHttpClient() {
		return httpClient;
	}
}
