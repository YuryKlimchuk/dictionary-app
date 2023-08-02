package com.hydroyura.dictinaryapp.core.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.model.Word;
import com.hydroyura.dictinaryapp.core.repository.IRepository;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;

@Bean(name = "MainScreen")
public class MainScreen implements Screen {

    private Color backgroundColor = Color.CORAL;

    @Inject(name = "MainStage")
    private MainStage mainStage;

    @Inject(name = "ObjectMapper")
    private ObjectMapper objectMapper;

    public MainScreen() {}

    @Override
    public void show() {
        mainStage.init();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        mainStage.act(delta);
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
