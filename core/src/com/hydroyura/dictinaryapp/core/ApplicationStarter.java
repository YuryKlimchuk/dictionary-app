package com.hydroyura.dictinaryapp.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.hydroyura.dictinaryapp.core.context.ApplicationContext;
import com.hydroyura.dictinaryapp.core.screens.splash.SplashScreen;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;

public class ApplicationStarter extends Game {

    private ApplicationContext context;
    private AssetManager assetManager;

    @Override
    public void create() {
        context = new ApplicationContext("com.hydroyura.dictinaryapp.core");
		assetManager = new AssetManager();
		loadResources();
        context.addBean(AssetManager.class, assetManager, "AssertManager");
		context.addBean(MainStage.class, new MainStage(), "MainStage");
		context.addBean(ApplicationContext.class, context, "Context");

        setScreen(new SplashScreen(context, this));

        new Thread(() -> {
            ApplicationContext p = context;
            context.init();
        }).start();

    }

    private void loadResources() {
	    assetManager.load("skins/main-skin.json", Skin.class, generateFonts());
    }

    private SkinLoader.SkinParameter generateFonts() {
		ObjectMap<String, Object> fonts = new ObjectMap<>();
		FreeTypeFontGenerator boldFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Bold.ttf"));
		FreeTypeFontGenerator normalFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("skins/fonts/Inter-Thin.ttf"));

		String ALL_CHARACTERS = FreeTypeFontGenerator.DEFAULT_CHARS
				+ "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";

		int NORMAL_SIZE = Gdx.graphics.getHeight() / 54;

		FreeTypeFontGenerator.FreeTypeFontParameter boldFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

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

		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.color = new Color(33/255, 48/255, 89/255, 1);
		parameter.size = Gdx.graphics.getWidth() / 25;
		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
		BitmapFont mainBtnsFont = generator.generateFont(parameter);
		fonts.put("main-btns-font", mainBtnsFont);

		FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter1.color = new Color(33/255, 48/255, 89/255, 1);
		parameter1.size = Gdx.graphics.getWidth() / 12;
		parameter1.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
				+ "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
				+ "1234567890.,:;_¡!¿?\"'+-*/()[]={}";
		BitmapFont headerTitleFont = generator.generateFont(parameter1);
		fonts.put("header-title-font", headerTitleFont);

		FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter2.color = new Color(Color.DARK_GRAY);
		parameter2.size = Gdx.graphics.getWidth() / 18;
		BitmapFont wordInputFont = generator1.generateFont(parameter2);
		fonts.put("word-input-font", wordInputFont);


		return new SkinLoader.SkinParameter(fonts);
	}

	public ApplicationContext getContext() {
		return context;
	}
}
