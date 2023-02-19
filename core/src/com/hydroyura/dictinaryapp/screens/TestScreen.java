package com.hydroyura.dictinaryapp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.AppStarter;

import java.util.Arrays;
import java.util.List;

public class TestScreen extends ScreenAdapter {

    Stage stage;
    Skin skin;
    @Override
    public void show() {
        skin = AppStarter.getInstance().getSkin("skins/main-skin.json");
        TextButton.TextButtonStyle style = skin.get("btn-translation", TextButton.TextButtonStyle.class);
        stage = new Stage();
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.align(Align.topLeft);
        table.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.8f);

        List<String> strArr = Arrays.asList("Юра", "Юрий", "Трансформер", "Каштан", "Ловушка", "Юг", "Север", "Изба");

        float MAX_WIDTH = 0.9f * Gdx.graphics.getWidth();
        for(int i = 0; i < strArr.size(); i++) {
            Table tmpTable = new Table();
            TextButton b1 = new TextButton(strArr.get(i), style);
            tmpTable.add(b1)
                    .width((b1.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                    .height(Gdx.graphics.getHeight() / 22).pad(15);

            for(int j = i + 1; j < strArr.size(); j++) {
                TextButton b2 = new TextButton(strArr.get(j), style);
                tmpTable.add(b2)
                        .width((b2.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                        .height(Gdx.graphics.getHeight() / 22).pad(15);
                if(tmpTable.getPrefWidth() > MAX_WIDTH) {
                    tmpTable.getCells().removeIndex(tmpTable.getCells().size - 1);
                    tmpTable.removeActor(b2);
                    i = j - 1;
                    break;
                }
            }
            table.add(tmpTable).align(Align.left).row();
        }

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(13/255f, 20/255f, 35/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }


    private void populateTranslateTable(Table table, List<String> list, TextButton.TextButtonStyle style) {
        float MAX_WIDTH = 0.9f * Gdx.graphics.getWidth();
        for(int i = 0; i < list.size(); i++) {
            Table tmpTable = new Table();
            TextButton b1 = new TextButton(list.get(i), style);
            tmpTable.add(b1)
                    .width((b1.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                    .height(Gdx.graphics.getHeight() / 22).pad(15);

            for(int j = i + 1; j < list.size(); j++) {
                TextButton b2 = new TextButton(list.get(j), style);
                tmpTable.add(b2)
                        .width((b2.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                        .height(Gdx.graphics.getHeight() / 22).pad(15);
                if(tmpTable.getPrefWidth() > MAX_WIDTH) {
                    tmpTable.getCells().removeIndex(tmpTable.getCells().size - 1);
                    tmpTable.removeActor(b2);
                    i = j - 1;
                    break;
                }
            }
            table.add(tmpTable).align(Align.left).row();
        }
    }
}
