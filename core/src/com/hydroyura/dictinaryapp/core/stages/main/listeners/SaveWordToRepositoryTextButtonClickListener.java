package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
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
        int a = 1;

        List<Actor> temp1 = Arrays.stream(table.getChildren().items)
                .filter(arg1 -> (arg1 != null))
                .filter(arg2 -> true)
                .collect(Collectors.toList());

        /*
        List<TextButton> selectedButtons = Arrays.stream(table.getChildren().items)
                .filter(arg1 -> arg1.getName().equals(BODY_TRANSLATION_VARIANTS_TABLE_BUFFER_ID))
                .map(arg2 -> ((Table) arg2))
                .flatMap(arg3 -> Arrays.stream(arg3.getChildren().items))
                .filter(arg4 -> arg4.getName().equals(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID))
                .map(arg5 -> ((TextButton) arg5))
                .collect(Collectors.toList());

         */

        int aa = 1;

    }


}
