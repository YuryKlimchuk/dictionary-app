package com.hydroyura.dictinaryapp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.AppStarter;

import java.util.Arrays;
import java.util.List;

public class TestScreen2 extends ScreenAdapter {

    Stage stage;
    Skin skin;



    @Override
    public void show() {
        stage = new Stage();
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
        skin = AppStarter.getInstance().getSkin("skins/main-skin.json");

        Group group = new Group();
        ScrollPane scrollPane = new ScrollPane(group);
        scrollPane.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2);
        scrollPane.setPosition(0f, Gdx.graphics.getHeight() / 5);

        List<String> list = Arrays.asList("Actor", "Lobsterr", "Shlyapa", "Sosisly", "Lobsterr", "Shlyapa", "Sosisly",
                "Shly13231apa", "Sosis34ly", "Lobstevvvrr",
                "Shlya32pa", "Sosi25sly", "Lobstvverr",
                "Shlya33pa", "Sosi113sly", "Lobstdsderr",
                "Shly22apa", "Sosiseee65ly", "Lobsterrs");



        for(int i = 0; i < list.size(); i++) {
            Label.LabelStyle style = AppStarter.getInstance().getStyle("skins/main-skin.json", "label-transcription", Label.LabelStyle.class);
            Label label = new Label(list.get(i), style);
            label.setPosition(50f, i * Gdx.graphics.getHeight() / 20);
            group.addActor(label);
        }

        stage.addActor(scrollPane);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(13 / 255f, 120 / 255f, 135 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

}
