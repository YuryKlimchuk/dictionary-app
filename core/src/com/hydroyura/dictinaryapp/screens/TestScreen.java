package com.hydroyura.dictinaryapp.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

import javax.xml.validation.Schema;
import java.util.*;

public class TestScreen extends ScreenAdapter {

    Stage stage;
    Skin skin;
    @Override
    public void show() {
        skin = ((AppStarter) Gdx.app.getApplicationListener()).getResource("skins/main-skin.json", Skin.class);
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

        /*
        while(iterator.hasNext()) {
            Table tmpTable = new Table();
            TextButton b1 = new TextButton(iterator.next(), style);
            tmpTable.add(b1)
                    .width((b1.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                    .height(Gdx.graphics.getHeight() / 22).pad(15);

            while(iterator.hasNext()) {
                TextButton b2 = new TextButton(iterator.next(), style);

                iterator.forEachRemaining();

            }

// ((Table) table.getCells().get(2).getActor()).getCells()

        }
         */

        // table.getCells().removeIndex(3).getActor()





/*

        strArr.forEach(name -> {
            table.add(new TextButton(name, style))
                    .width((name.length() + 2) * Gdx.graphics.getWidth() / 35)
                    .height(Gdx.graphics.getHeight() / 22).pad(15);
            Gdx.app.log("TABLE_WIDTH", String.valueOf(table.getPrefWidth()));


        });
*/

        /*
        Table tmp1 = new Table();
        TextButton b11 = new TextButton("sad", style);
        tmp1.add(b11)
                .width((b11.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                .height(Gdx.graphics.getHeight() / 22).pad(15);

        TextButton b12 = new TextButton("ddsad", style);
        tmp1.add(b12)
                .width((b12.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                .height(Gdx.graphics.getHeight() / 22).pad(15);

        TextButton b13 = new TextButton("saGGd", style);
        tmp1.add(b13)
                .width((b13.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                .height(Gdx.graphics.getHeight() / 22).pad(15);
        table.add(tmp1).align(Align.left).row();

        Table tmp2 = new Table();
        TextButton b21 = new TextButton("sdddad", style);
        tmp2.add(b21)
                .width((b21.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                .height(Gdx.graphics.getHeight() / 22).pad(15);

        TextButton b22 = new TextButton("ddd", style);
        tmp2.add(b22)
                .width((b22.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                .height(Gdx.graphics.getHeight() / 22).pad(15);
        table.add(tmp2).align(Align.left);
        */


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
