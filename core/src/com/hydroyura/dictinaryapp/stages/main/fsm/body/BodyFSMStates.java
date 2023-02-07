package com.hydroyura.dictinaryapp.stages.main.fsm.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;

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
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            String word = ((TextButton) entity.findActor(BODY_SELECTED_WORD_ID)).getText().toString();
            Gdx.app.log(this.getClass().toString(), "Word to add -> " + word);
            Label label = entity.findActor(BODY_WORDS_WORD_ADD_TITLE_ID);
            label.setVisible(true);
            label.setText(word);
            label.layout();
            label.setPosition(Gdx.graphics.getWidth() / 2f - label.getGlyphLayout().width / 2f, Gdx.graphics.getHeight() * 0.9f);

            String transcription = "[" + "transcription" + "]";
            Gdx.app.log(this.getClass().toString(), "Word transcription -> " + transcription);
            Label labelTranscription = entity.findActor(BODY_WORDS_WORD_ADD_TITLE_TRANSCRIPTION_ID);
            labelTranscription.setVisible(true);
            labelTranscription.setText(transcription);
            labelTranscription.layout();
            labelTranscription.setPosition(Gdx.graphics.getWidth() / 2f - labelTranscription.getGlyphLayout().width / 2f, Gdx.graphics.getHeight() * 0.85f);

            entity.findActor(BODY_WORDS_WORD_ADD_PRONUNCIATION_ID).setVisible(true);

        }
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
