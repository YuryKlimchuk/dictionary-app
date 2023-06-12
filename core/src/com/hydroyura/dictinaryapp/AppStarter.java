package com.hydroyura.dictinaryapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.ObjectMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.data.SQLiteRepository;
import com.hydroyura.dictinaryapp.data.repository.IRepository;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;
import com.hydroyura.dictinaryapp.httpclient.response.impl.TranslateHttpResponse;
import com.hydroyura.dictinaryapp.screens.main.MainScreen;
import com.hydroyura.dictinaryapp.screens.splash.SplashScreen;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AppStarter extends Game {

	private static AppStarter app;
	private AssetManager assetManager = new AssetManager();
	private Map<Class<?>, Object> context = new HashMap<>();


	public <T> T getBean(Class<T> clazz) {
		if(!context.containsKey(clazz)) throw new RuntimeException("Don`t have bean of current class type.");
		return clazz.cast(context.get(clazz));
	}

	public void initialContext() {
		context.put(ObjectMapper.class, new ObjectMapper());
		context.put(TranslateHttpResponse.class, new TranslateHttpResponse());
		context.put(HttpClient.class, new HttpClient());
	}

	public static AppStarter getInstance() {
		return app;
	}

	public Skin getSkin(String resourceName) {
		return assetManager.get(resourceName, Skin.class);
	}

	public <T> T getStyle(String skinName, String resourceName, Class<T> clazz) {
		return getSkin(skinName).get(resourceName, clazz);
	}









	private Screen mainScreen = new MainScreen();
	private Screen splashScreen = new SplashScreen();


	private IRepository repository;

	private AppStarter() {
		throw new RuntimeException("Forbidden to use");
	}

	public AppStarter(IRepository repository) {
		//this.repository = repository;
		this.app = this;

		this.repository = new SQLiteRepository();

	}

	@Override
	public void create() {
		loadResources();
		setScreen(new SplashScreen());
		app.initialContext();
		try {
			repository.init();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

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


	// FIXME: clean this method
	private SkinParameter generateFonts() {
		ObjectMap<String, Object> fonts = new ObjectMap<>();
		FreeTypeFontGenerator boldFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Bold.ttf"));
		FreeTypeFontGenerator normalFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Thin.ttf"));

		String ALL_CHARACTERS = FreeTypeFontGenerator.DEFAULT_CHARS
				+ "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";

		int NORMAL_SIZE = Gdx.graphics.getHeight() / 54;

		FreeTypeFontParameter boldFontParameter = new FreeTypeFontParameter();

		boldFontParameter.size = NORMAL_SIZE;
		boldFontParameter.characters = ALL_CHARACTERS;
		fonts.put("bold-dark-blue-1_0", boldFontGenerator.generateFont(boldFontParameter));

		boldFontParameter.size = (int) (NORMAL_SIZE * 1.5);
		fonts.put("bold-dark-blue-1_5", boldFontGenerator.generateFont(boldFontParameter));
		fonts.put("normal-dark-blue-1_5", normalFontGenerator.generateFont(boldFontParameter));

		boldFontParameter.size = (int) (NORMAL_SIZE * 2);
		fonts.put("bold-dark-blue-2_0", boldFontGenerator.generateFont(boldFontParameter));

		boldFontParameter.size = (int) (NORMAL_SIZE * 2.5);
		fonts.put("bold-dark-blue-2_5", boldFontGenerator.generateFont(boldFontParameter));

		// ----------------------------------------------------



		// ----------------------------------------------------
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Bold.ttf"));
		FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Thin.ttf"));

		//ObjectMap<String, Object> fonts = new ObjectMap<>();

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.color = new Color(33/255, 48/255, 89/255, 1);
		parameter.size = Gdx.graphics.getWidth() / 25;
		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
		BitmapFont mainBtnsFont = generator.generateFont(parameter);
		fonts.put("main-btns-font", mainBtnsFont);

		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
		parameter1.color = new Color(33/255, 48/255, 89/255, 1);
		parameter1.size = Gdx.graphics.getWidth() / 12;
		parameter1.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
		BitmapFont headerTitleFont = generator.generateFont(parameter1);
		fonts.put("header-title-font", headerTitleFont);

		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		parameter2.color = new Color(Color.DARK_GRAY);
		parameter2.size = Gdx.graphics.getWidth() / 18;
		BitmapFont wordInputFont = generator1.generateFont(parameter2);
		fonts.put("word-input-font", wordInputFont);


		return new SkinParameter(fonts);
	}


	public boolean getAssetManagerUpdate() {
		return assetManager.update();
	}

	public void setMainScreen() {
		setScreen(mainScreen);
	}

	/*

	public MainStage getMainStage() {
		return ((MainStage) ((MainScreen) mainScreen).getStage());
	}
	*/

}
