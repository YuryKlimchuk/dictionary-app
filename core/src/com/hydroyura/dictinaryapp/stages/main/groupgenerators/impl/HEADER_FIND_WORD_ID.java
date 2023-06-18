package com.hydroyura.dictinaryapp.stages.main.groupgenerators.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.stages.customs.Background;
import com.hydroyura.dictinaryapp.stages.main.MainStageConstants;
import com.hydroyura.dictinaryapp.stages.main.groupgenerators.IGroupGenerator;
import com.hydroyura.dictinaryapp.stages.main.listners.BtnClearTextListener;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.FIELD_WORD_INPUT_ID;

public class HEADER_FIND_WORD_ID implements IGroupGenerator {
    @Override
    public Group generate(Skin skin) {
        Group group = new Group();
        group.setName(MainStageConstants.HEADER_FIND_WORD_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 5);
        group.setPosition(0f, Gdx.graphics.getHeight() * 4 / 5);
        group.addActor(new Background(skin.getColor("orange")));
        //addActor(group);

        Label title = new Label("Словарь", skin.get("header-title", Label.LabelStyle.class));
        title.setPosition(group.getWidth() / 2 - title.getWidth() / 2, group.getHeight() * 2 / 3);
        group.addActor(title);

        TextField fieldWordInput = new TextField("", skin.get("field-word-input", TextField.TextFieldStyle.class));
        fieldWordInput.setName(FIELD_WORD_INPUT_ID);
        fieldWordInput.setSize(Gdx.graphics.getWidth() * 0.80f, group.getHeight() / 3f);
        fieldWordInput.setPosition(Gdx.graphics.getWidth() / 2f - fieldWordInput.getWidth() / 2f, group.getHeight() / 5f);
        fieldWordInput.getStyle().background.setLeftWidth(80f);

        //TextField.TextFieldListener fieldWordInputListener = new FieldWordInputListener();
        //fieldWordInput.setTextFieldListener(fieldWordInputListener);

        fieldWordInput.getStyle().focusedBackground.setLeftWidth(Gdx.graphics.getWidth() / 9f);
        fieldWordInput.getStyle().focusedBackground.setRightWidth(Gdx.graphics.getWidth() / 9f);

        group.addActor(fieldWordInput);

        ImageButton btnClearText = new ImageButton(skin.get("clear-text", ImageButton.ImageButtonStyle.class));
        btnClearText.setSize(fieldWordInput.getHeight() / 3f, fieldWordInput.getHeight() / 3f);
        btnClearText.setPosition(fieldWordInput.getX() + fieldWordInput.getWidth() * 0.9f, (fieldWordInput.getY() + fieldWordInput.getHeight() / 2f) - btnClearText.getHeight() / 2f);

        ClickListener btnClearTextListener = new BtnClearTextListener();
        btnClearText.addListener(btnClearTextListener);

        group.addActor(btnClearText);

        return group;
    }
}
