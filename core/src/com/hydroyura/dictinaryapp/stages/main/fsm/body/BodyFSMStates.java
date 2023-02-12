package com.hydroyura.dictinaryapp.stages.main.fsm.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    SELECT_TRANSLATE {

        private boolean isReadyTranslate = false;
        private Collection<String> translations;

        @Override
        public void setTranslations() {
            Gdx.app.log("LOGGGGG", "F....................FFFF");
            this.isReadyTranslate = true;
        }

        private void clear() {
            this.isReadyTranslate = false;
            this.translations = null;
        }

        @Override
        public void update(Group entity) {
            super.update(entity);
            if(isReadyTranslate) {
                Gdx.app.log(this.getClass().toString(), "translate is ready");
                clear();
            }
        }

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

            sendRequest(word);
        }

        private void sendRequest(String text) {
            String url2 = HttpClient.URL_TRANSLATE;
            Map<String, String> headers2 = Map.of(
                    "X-RapidAPI-Key", "244b5dd242msh2d660d8777daa5cp110e59jsn2bcfbca1afed",
                    "X-RapidAPI-Host", "ai-translate.p.rapidapi.com",
                    "content-type", "application/json"
            );

            Map<String, Object> body2 = new HashMap<>();
            body2.put("texts", Arrays.asList(text));
            body2.put("tls", Arrays.asList("ru"));
            body2.put("sl", "en");

            try {
                ((AppStarter) Gdx.app.getApplicationListener()).getMapper().writeValueAsString(body2);
                ((AppStarter) Gdx.app.getApplicationListener()).getHttpClient().post(
                        url2,
                        headers2,
                        ((AppStarter) Gdx.app.getApplicationListener()).getMapper().writeValueAsString(body2),
                        ((AppStarter) Gdx.app.getApplicationListener()).getTranslateHttpResponse());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
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

    public void setTranslations() {
        throw new RuntimeException("Method is blocked");
    }
}
