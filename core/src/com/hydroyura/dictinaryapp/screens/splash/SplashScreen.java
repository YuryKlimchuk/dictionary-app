package com.hydroyura.dictinaryapp.screens.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.screens.TestScreen2;

public class SplashScreen implements Screen {

    private AppStarter app;

    @Override
    public void show() {
        Gdx.app.log(this.getClass().toString(), "show()");
        app = (AppStarter) Gdx.app.getApplicationListener();
    }

    @Override
    public void render(float delta) {

        if(app.getAssetManagerUpdate()) {
            Gdx.app.log(this.getClass().toString(), "resources loading was finished");
            app.setMainScreen();
            //app.setScreen(new TestScreen2());
        }

        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
