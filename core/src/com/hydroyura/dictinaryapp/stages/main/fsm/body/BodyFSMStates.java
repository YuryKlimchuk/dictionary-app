package com.hydroyura.dictinaryapp.stages.main.fsm.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

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
        private List<String> translations;

        @Override
        public void setTranslations(List<String> translations) {
            this.isReadyTranslate = true;
            this.translations = translations;
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

                TextButton.TextButtonStyle style = ((AppStarter) Gdx.app.getApplicationListener())
                        .getResource("skins/main-skin.json", Skin.class)
                                .get("btn-translation", TextButton.TextButtonStyle.class);

                TextButton.TextButtonStyle styleSelected = ((AppStarter) Gdx.app.getApplicationListener())
                        .getResource("skins/main-skin.json", Skin.class)
                        .get("btn-translation-selected", TextButton.TextButtonStyle.class);

                translations.add("Прилагательное");
                translations.add("Прила");
                translations.add("Дрыська");
                translations.add("Аельное");
                translations.add("Дрыськаsdsd");
                translations.add("Дька");
                translations.add("Дрысddька");
                translations.add("Дька");
                translations.add("Дрысьdfsdfsfffка");



                ClickListener listener = null;

                Table table = entity.findActor(BODY_TRANSLATION_VARIANTS_TABLE_ID);
                table.setVisible(true);

                populateTranslateTable(table, translations, style);

                /*
                float MAX_ROW_WIDTH = 0.9f * Gdx.graphics.getWidth();
                //float currentRowWidth = 0f;

                Array<Actor> list = new Array<>();

                for(String item: translations) {
                    TextButton button = new TextButton(item, style);

                    float width = (item.length() + 2) * Gdx.graphics.getWidth() / 35;
                    float height = Gdx.graphics.getHeight() / 22;
                    float pad = Gdx.graphics.getWidth() / 80;
                    float currentWidth = getWidthActorsFromList(list);

                    button.setWidth(width);

                    Gdx.app.log("item", String.valueOf(item));
                    Gdx.app.log("width", String.valueOf(width));
                    Gdx.app.log("MAX_ROW_WIDTH", String.valueOf(MAX_ROW_WIDTH));
                    Gdx.app.log("currentWidth", String.valueOf(currentWidth));
                    Gdx.app.log("", "------------------------------------");

                    if((currentWidth + width) > MAX_ROW_WIDTH) {
                        Table tmpTable = new Table();
                        tmpTable.setPosition(0f, 0f);
                        tmpTable.align(Align.topLeft);
                        StreamSupport.stream(list.spliterator(), false).forEach(var1 -> {
                            tmpTable.add(var1).width(width).height(height).padTop(pad).padBottom(pad).padLeft(pad).padRight(pad);
                        });
                        table.add(tmpTable).align(Align.left).row();
                        list.clear();
                    }
                    list.add(button);
                };
                entity.addActor(table);                */
                clear();

            }
        }

        // FIXME: need to refactor
        private void populateTranslateTable(Table table, List<String> list, TextButton.TextButtonStyle style) {
            float MAX_WIDTH = 0.9f * Gdx.graphics.getWidth();
            for(int i = 0; i < list.size(); i++) {
                Table tmpTable = new Table();
                TextButton b1 = new TextButton(list.get(i), style);
                tmpTable.add(b1)
                        .width((b1.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                        .height(Gdx.graphics.getHeight() / 22).pad(15);

                for(int j = i + 1; j < list.size(); j++) {
                    TextButton b2 = new TextButton(list.get(j), style);
                    tmpTable.add(b2)
                            .width((b2.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                            .height(Gdx.graphics.getHeight() / 22).pad(15);
                    if(tmpTable.getPrefWidth() > MAX_WIDTH) {
                        tmpTable.getCells().removeIndex(tmpTable.getCells().size - 1);
                        tmpTable.removeActor(b2);
                        i = j - 1;
                        break;
                    }
                }
                table.add(tmpTable).align(Align.left).row();
            }
        }


        private float getWidthActorsFromList(Array<Actor> actors) {
            return
                    StreamSupport.stream(actors.spliterator(), false)
                            .map(actor -> actor.getWidth())
                            .reduce((w1, w2) -> w1 + w2).orElseGet(() -> Float.NaN).floatValue();
        }

        private void addToRow() {

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

    public void setTranslations(List<String> translations) {
        throw new RuntimeException("Method is blocked");
    }
}
