package com.hydroyura.dictinaryapp.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

public class MainScreen implements Screen {

    public Stage getStage() {
        return stage;
    }

    private Stage stage;

    @Override
    public void show() {
        stage = new MainStage();
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(13/255f, 20/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
