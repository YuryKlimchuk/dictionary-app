package com.hydroyura.dictinaryapp.core.stages.generators.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.hydroyura.dictinaryapp.core.stages.customs.Background;
import com.hydroyura.dictinaryapp.core.stages.generators.IGroupGenerator;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.FOOTER_ADD_WORD_ID;

public class FooterAddWordGenerator implements IGroupGenerator {

    @Override
    public Group generate(Skin skin) {
        Group group = new Group();
        group.setName(FOOTER_ADD_WORD_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 5);
        group.setPosition(0f, -1 * Gdx.graphics.getHeight() / 5);
        group.addActor(new Background(skin.getColor("orange")));



        TextButton.TextButtonStyle st1 = skin.get("btn-select-collection", TextButton.TextButtonStyle.class);
        TextButton.TextButtonStyle st2 = skin.get("btn-save-translation", TextButton.TextButtonStyle.class);

        TextButton b1 = new TextButton("Выбрать коллекцию", st1);
        b1.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 10);
        b1.setPosition(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 20);
        b1.getLabel().setWrap(true);
        group.addActor(b1);

        TextButton b2 = new TextButton("Добавить в колекцию", st2);
        b2.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 10);
        b2.setPosition(11 * Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() / 20);
        b2.getLabel().setWrap(true);
        group.addActor(b2);

        return group;
    }


}
