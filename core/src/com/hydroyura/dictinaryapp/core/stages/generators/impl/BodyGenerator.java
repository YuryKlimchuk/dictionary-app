package com.hydroyura.dictinaryapp.core.stages.generators.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.core.stages.customs.Background;
import com.hydroyura.dictinaryapp.core.stages.generators.IGroupGenerator;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public class BodyGenerator implements IGroupGenerator {
    @Override
    public Group generate(Skin skin) {
        Group group = new Group();
        group.setName(BODY_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        group.setPosition(0f, 0f);
        group.addActor(new Background(Color.WHITE));

        // Table with autocomplete results
        Table table = new Table(skin);
        table.setName(BODY_WORDS_AUTOCOMPLETE_RESULT_ID);
        table.setWidth(Gdx.graphics.getWidth() * 1f);
        table.setPosition(0f, Gdx.graphics.getHeight() * 4 / 5);
        group.addActor(table);
        table.align(Align.topLeft);

        Label label = new Label("", skin.get("label-for-add-word", Label.LabelStyle.class));
        label.setName(BODY_WORDS_WORD_ADD_TITLE_ID);
        label.setVisible(false);
        group.addActor(label);

        Label addWordTitleTranscription = new Label("", skin.get("label-transcription", Label.LabelStyle.class));
        addWordTitleTranscription.setName(BODY_WORDS_WORD_ADD_TITLE_TRANSCRIPTION_ID);
        addWordTitleTranscription.setVisible(false);
        group.addActor(addWordTitleTranscription);

        // FIXME: add click listener
        ImageButton pronunciationBtn = new ImageButton(skin.get("pronunciation", ImageButton.ImageButtonStyle.class));
        pronunciationBtn.setName(BODY_WORDS_WORD_ADD_PRONUNCIATION_ID);
        pronunciationBtn.setSize(Gdx.graphics.getWidth() / 10f, Gdx.graphics.getWidth() / 10f);
        pronunciationBtn.setPosition(Gdx.graphics.getWidth() / 2f - pronunciationBtn.getWidth() / 2f, Gdx.graphics.getHeight() * 0.75f);
        pronunciationBtn.setVisible(false);
        group.addActor(pronunciationBtn);

        // Table for displaying translate variants
        Table translateVariantsTable = new Table();
        translateVariantsTable.setName(BODY_TRANSLATION_VARIANTS_TABLE_ID);
        translateVariantsTable.setWidth(Gdx.graphics.getWidth() * 0.9f);
        translateVariantsTable.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.7f);
        translateVariantsTable.align(Align.topLeft);
        translateVariantsTable.setVisible(false);
        group.addActor(translateVariantsTable);

        return group;

    }
}
