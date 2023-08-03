package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.model.Word;
import com.hydroyura.dictinaryapp.core.repository.IRepository;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public class SaveWordToRepositoryTextButtonClickListener extends ClickListener {

    private IRepository repository;

    public SaveWordToRepositoryTextButtonClickListener() {
        this.repository = ((ApplicationStarter) Gdx.app.getApplicationListener()).getRepository();
    }

    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        TextButton button = (TextButton) event.getListenerActor();
        Gdx.app.log(this.getClass().toString(), "clicked button with value[" + button.getText() + "]");

        MainStage stage = (MainStage) button.getStage();
        Table table = stage.getGroup(BODY_ID).findActor(BODY_TRANSLATION_VARIANTS_TABLE_ID);

        List<TextButton> selectedButtons = Arrays.stream(table.getChildren().items)
                .filter(Objects::nonNull)
                .filter(Table.class::isInstance)
                .map(Table.class::cast)
                .flatMap(agr1 -> Arrays.stream(agr1.getChildren().items))
                .filter(Objects::nonNull)
                .filter(TextButton.class::isInstance)
                .map(TextButton.class::cast)
                .filter(arg2 -> arg2.getName().equals(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID__SELECTED))
                .collect(Collectors.toList());

        String[] translate = selectedButtons.stream()
                .map(TextButton::getText)
                .map(String::valueOf)
                .toArray(String[]::new);
        String collectionId = "TEST_COL";
        int trainStatus = 0;
        String type = "существительное";
        String original = ((Label) stage.getGroup(BODY_ID).findActor(BODY_WORDS_WORD_ADD_TITLE_ID)).getText().toString();

        Word word = new Word()
                .setCollectionId(collectionId)
                .setOriginal(original)
                .setType(type)
                .setTrainStatus(trainStatus)
                .setTranslate(translate);

        repository.addWord(word);

    }


}
