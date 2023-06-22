package com.hydroyura.dictinaryapp.core.stages.main.fsms.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.http.autocomplete.AutocompleteAPI;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;

import java.util.HashMap;
import java.util.Map;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public enum BodyFSMStates implements State<Group> {

    PREVIOUS_INPUTS() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
        }
    },

    WORD_INPUT() {

        @Override
        public void enter(Group entity) {
            super.enter(entity);
            entity.findActor(BODY_WORDS_AUTOCOMPLETE_RESULT_ID).setVisible(true);
        }

        @Override
        public void exit(Group entity) {
            super.exit(entity);
            entity.findActor(BODY_WORDS_AUTOCOMPLETE_RESULT_ID).setVisible(false);
        }

        @Override
        public void update(Group entity) {
            super.update(entity);

            TextField textInputField = ((MainStage) entity.getStage()).getGroup(HEADER_FIND_WORD_ID).findActor(FIELD_WORD_INPUT_ID);
            String currentInput = textInputField.getText();

            AutocompleteAPI autocompleteAPI = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("AutocompleteAPI", AutocompleteAPI.class);

            int a = 1;
        }
    };

    @Override
    public void enter(Group entity) {

    }

    @Override
    public void update(Group entity) {

    }

    @Override
    public void exit(Group entity) {

    }

    @Override
    public boolean onMessage(Group entity, Telegram telegram) {
        return false;
    }
}
