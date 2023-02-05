package com.hydroyura.dictinaryapp.stages.main.fsm.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

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
    },

    WORD_ADD{

    };


    @Override
    public void enter(Group entity) {
        Gdx.app.log(this.getClass().toString(), "Enter to state: " + this.toString());
    }

    @Override
    public void update(Group entity) {
        // Gdx.app.log(this.getClass().toString(), "Update to state: " + this.toString());
    }

    @Override
    public void exit(Group entity) {
        Gdx.app.log(this.getClass().toString(), "Exit to state: " + this.toString());
    }

    @Override
    public boolean onMessage(Group entity, Telegram telegram) {
        Gdx.app.log(this.getClass().toString(), "onMessage to state: " + this.toString());
        return false;
    }
}
