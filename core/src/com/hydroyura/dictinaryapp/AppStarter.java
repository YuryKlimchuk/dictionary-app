package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.ObjectMap;
import com.hydroyura.dictinaryapp.data.repository.IRepository;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;
import com.hydroyura.dictinaryapp.screens.main.MainScreen;
import com.hydroyura.dictinaryapp.screens.splash.SplashScreen;
import com.hydroyura.dictinaryapp.stages.main.MainStage;
import com.hydroyura.dictinaryapp.stages.main.MainStageConstants;

import java.util.HashMap;
import java.util.Map;

/*
	FIXME:
	1. add method returned instance
	2. create container for storing beans
 */
public class AppStarter extends Game {

	private AssetManager assetManager = new AssetManager();
	private Screen mainScreen = new MainScreen();
	private Screen splashScreen = new SplashScreen();

	private HttpClient httpClient = new HttpClient();

	private VerticalGroup searchResult;

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
		assetManager.load("skins/main-skin.json", Skin.class, generateFonts());
	}

	private SkinParameter generateFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/inter-bold.ttf"));
		FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/inter.ttf"));

		ObjectMap<String, Object> fonts = new ObjectMap<>();

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.color = new Color(33/255, 48/255, 89/255, 1);
		parameter.size = Gdx.graphics.getWidth() / 25;
		BitmapFont mainBtnsFont = generator.generateFont(parameter);
		fonts.put("main-btns-font", mainBtnsFont);

		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
		parameter1.color = new Color(33/255, 48/255, 89/255, 1);
		parameter1.size = Gdx.graphics.getWidth() / 12;
		BitmapFont headerTitleFont = generator.generateFont(parameter1);
		fonts.put("header-title-font", headerTitleFont);

		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		parameter2.color = new Color(Color.DARK_GRAY);
		parameter2.size = Gdx.graphics.getWidth() / 18;
		BitmapFont wordInputFont = generator1.generateFont(parameter2);
		fonts.put("word-input-font", wordInputFont);


		return new SkinParameter(fonts);
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

	public VerticalGroup getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(VerticalGroup searchResult) {
		this.searchResult = searchResult;
	}


	public MainStage getMainStage() {
		return ((MainStage) ((MainScreen) mainScreen).getStage());
	}

}
