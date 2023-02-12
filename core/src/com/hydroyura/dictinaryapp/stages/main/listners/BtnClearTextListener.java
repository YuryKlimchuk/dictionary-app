package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class BtnClearTextListener extends ClickListener {

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Group group = event.getListenerActor().getParent();
        TextField textField = (TextField) group.findActor(FIELD_WORD_INPUT_ID);
        textField.setText("");

        // ((Table) groups.get(BODY_ID).findActor(BODY_WORDS_AUTOCOMPLETE_RESULT_ID)).clearChildren();
    }

}

