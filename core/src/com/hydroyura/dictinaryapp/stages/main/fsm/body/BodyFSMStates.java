package com.hydroyura.dictinaryapp.stages.main.fsm.body;

/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;
import com.hydroyura.dictinaryapp.httpclient.response.impl.TranslateHttpResponse;
import com.hydroyura.dictinaryapp.stages.main.MainStage;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterWordAddFSMStates;
import com.hydroyura.dictinaryapp.stages.main.listners.AddCustomTranslateButtonListener;
import com.hydroyura.dictinaryapp.stages.main.listners.TranslateButtonListener;
*/

import java.util.*;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;
/*
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

        @Override
        public void addTranslate(String translate) {
            translations.add(translate);
            isReadyTranslate = true;
        }

        private void clear() {
            this.isReadyTranslate = false;
            //this.translations = null;
        }

        @Override
        public void update(Group entity) {
            super.update(entity);

            Table table = entity.findActor(BODY_TRANSLATION_VARIANTS_TABLE_ID);

            TextButton.TextButtonStyle style = AppStarter.getInstance().getStyle("skins/main-skin.json", "btn-translation", TextButton.TextButtonStyle.class);
            TextButton.TextButtonStyle styleSelected = AppStarter.getInstance().getStyle("skins/main-skin.json", "btn-translation-selected", TextButton.TextButtonStyle.class);
            TextButton.TextButtonStyle addCustomTranslationStyle = AppStarter.getInstance().getStyle("skins/main-skin.json", "btn-add-custom-translation", TextButton.TextButtonStyle.class);

            if(isReadyTranslate) {
                Gdx.app.log(this.getClass().toString(), "translate is ready");

                table.clear();


                table.setVisible(true);

                populateTranslateTable(table, translations, style, styleSelected, addCustomTranslationStyle);
                clear();
            }

            // show/hide footer_add_word
            // FIXME: Maybe need to replace from update ???
            boolean isNeedToShowFooterAdd = StreamSupport.stream(table.getChildren().spliterator(), false).anyMatch(
                    actor -> {
                        if(actor instanceof Table) {
                            Spliterator<Actor> spliterator = ((Table) actor).getChildren().spliterator();
                            return StreamSupport.stream(spliterator, false).anyMatch(
                                    childActor -> {
                                        if(childActor instanceof TextButton) {
                                            TextButton button = (TextButton) childActor;
                                            if(button.getName().equals(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID) && button.getStyle().equals(styleSelected)) {
                                                return true;
                                            }
                                        }
                                        return false;
                                    }
                            );
                        }
                        return false;
                    }
            );

            DefaultStateMachine<Group, State<Group>> fsmFooterAddWord =
                    ((MainStage) entity.getStage()).getFsm(FOOTER_ADD_WORD_ID);

            if(isNeedToShowFooterAdd) {
                fsmFooterAddWord.changeState(FooterWordAddFSMStates.DISPLAY);
            } else {
                fsmFooterAddWord.changeState(FooterWordAddFSMStates.HIDE);
            }

        }

        // FIXME: need to refactor
        private void populateTranslateTable(Table table, List<String> list, TextButton.TextButtonStyle style, TextButton.TextButtonStyle selectedStyle, TextButton.TextButtonStyle addCustomTranslationStyle) {
            float MAX_WIDTH = 0.9f * Gdx.graphics.getWidth();

            ClickListener listener = new TranslateButtonListener(style, selectedStyle);
            for(int i = 0; i < list.size(); i++) {
                Table tmpTable = new Table();
                TextButton b1 = new TextButton(list.get(i), style);
                b1.addListener(listener);
                b1.setName(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID);
                tmpTable.add(b1)
                        .width((b1.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                        .height(Gdx.graphics.getHeight() / 22)
                        .padRight(Gdx.graphics.getWidth() / 50).padTop(Gdx.graphics.getWidth() / 50);

                for(int j = i + 1; j < list.size(); j++) {
                    TextButton b2 = new TextButton(list.get(j), style);
                    b2.addListener(listener);
                    b2.setName(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID);
                    tmpTable.add(b2)
                            .width((b2.getText().length() + 2) * Gdx.graphics.getWidth() / 35)
                            .height(Gdx.graphics.getHeight() / 22)
                            .padRight(Gdx.graphics.getWidth() / 50).padTop(Gdx.graphics.getWidth() / 50);
                    if(tmpTable.getPrefWidth() > MAX_WIDTH) {
                        tmpTable.getCells().removeIndex(tmpTable.getCells().size - 1);
                        tmpTable.removeActor(b2);
                        i = j - 1;
                        break;
                    }
                }
                table.add(tmpTable).align(Align.left).row();
            }

            ClickListener listener2 = new AddCustomTranslateButtonListener();

            TextButton customTranslateButton = new TextButton("Свой варинат перевода", addCustomTranslationStyle);
            customTranslateButton.getLabel().setWrap(true);
            customTranslateButton.setName(BODY_BUTTON_ADD_CUSTOM_TRANSLATION_ID);
            customTranslateButton.addListener(listener2);
            table.add(customTranslateButton)
                    .width(Gdx.graphics.getWidth() / 2)
                    .height(Gdx.graphics.getHeight() / 10)
                    .align(Align.center)
                    .padTop(Gdx.graphics.getHeight() / 20);


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

            ObjectMapper objectMapper = AppStarter.getInstance().getBean(ObjectMapper.class);

            try {
                objectMapper.writeValueAsString(body2);
                AppStarter.getInstance().getBean(HttpClient.class).post(
                        url2,
                        headers2,
                        objectMapper.writeValueAsString(body2),
                        AppStarter.getInstance().getBean(TranslateHttpResponse.class));
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

    public void addTranslate(String translate) {
        throw new RuntimeException("Method is blocked");
    }

    public void setTranslations(List<String> translations) {
        throw new RuntimeException("Method is blocked");
    }
}
*/