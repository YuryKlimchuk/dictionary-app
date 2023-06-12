package com.hydroyura.dictinaryapp.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hydroyura.dictinaryapp.context.Inject;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

public class MainScreen implements Screen {

    @Inject(key = "MainStage")
    private Stage stage;

    @Override
    public void show() {
        ((MainStage) stage).init();
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
