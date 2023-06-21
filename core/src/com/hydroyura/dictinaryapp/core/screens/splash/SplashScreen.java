package com.hydroyura.dictinaryapp.core.screens.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.context.ApplicationContext;

public class SplashScreen implements Screen {

    private ApplicationContext context;
    private ApplicationStarter application;

    public ApplicationStarter getApplication() {
        return application;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public DefaultStateMachine<Screen, State<Screen>> getFsm() {
        return fsm;
    }

    private AssetManager assetManager;

    private DefaultStateMachine<Screen, State<Screen>> fsm;

    private Color backgroundColor = Color.SKY;

    private Stage stage;

    public SplashScreen(ApplicationContext context, ApplicationStarter application) {
        this.context = context;
        this.application = application;



        fsm = new DefaultStateMachine<>(this, SplashScreenStates.ANIMATE);

    }


    @Override
    public void show() {

        Texture circle = new Texture("splash/circle.png");
        stage = new Stage();

        Image image = new Image(circle);
        image.setSize(50f, 50f);
        image.setPosition(image.getWidth(), Gdx.graphics.getHeight() / 2);
        stage.addActor(image);

        ParallelAction action = new ParallelAction();

        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(Gdx.graphics.getWidth() - (1.5f * image.getWidth()), image.getY());
        moveToAction.setDuration(5f);
        action.addAction(moveToAction);

        ScaleToAction scaleToAction = new ScaleToAction();
        scaleToAction.setScale(0.5f);
        scaleToAction.setDuration(5f);
        action.addAction(scaleToAction);

        AlphaAction alphaAction = new AlphaAction();
        alphaAction.setAlpha(0.2f);
        alphaAction.setDuration(5f);
        action.addAction(alphaAction);

        action.addAction(Actions.sequence(
                Actions.delay(5f),
                Actions.run(() -> {
                    image.setSize(50f, 50f);
                    image.setPosition(image.getWidth(), Gdx.graphics.getHeight() / 2);
                    action.reset();
                    action.restart();
                    image.addAction(action);
                })
        ));

        image.addAction(action);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        fsm.update();
        stage.act(delta);
        stage.draw();
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
        assetManager.dispose();
        stage.dispose();
    }
}
