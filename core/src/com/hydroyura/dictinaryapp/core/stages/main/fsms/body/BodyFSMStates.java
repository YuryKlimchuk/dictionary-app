package com.hydroyura.dictinaryapp.core.stages.main.fsms.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.core.ApplicationStarter;
import com.hydroyura.dictinaryapp.core.http.autocomplete.AutocompleteAPI;
import com.hydroyura.dictinaryapp.core.http.translate.TranslateAPI;
import com.hydroyura.dictinaryapp.core.stages.customs.Line;
import com.hydroyura.dictinaryapp.core.stages.main.MainStage;
import com.hydroyura.dictinaryapp.core.stages.main.listeners.AutoCompletedTextButtonClickListener;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

public enum BodyFSMStates implements State<Group> {

    PREVIOUS_INPUTS() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
        }
    },

    WORD_INPUT() {

        private TextButton.TextButtonStyle style;

        private ClickListener clickListener;

        private String currentAutoComplete = "";

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

            Table table = entity.findActor(BODY_WORDS_AUTOCOMPLETE_RESULT_ID);

            AutocompleteAPI autocompleteAPI = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("AutocompleteAPI", AutocompleteAPI.class);

            if(clickListener == null) clickListener = new AutoCompletedTextButtonClickListener();

            if(style == null) style = findStyle();

            // Туда
            if (autocompleteAPI.isBufferContainValue(currentInput) && isUpdateAutocompleteRequired(currentInput)) {
                table.clearChildren();
                autocompleteAPI.getValueFromBuffer(currentInput).forEach(item -> {
                    TextButton button = new TextButton(item, style);
                    button.setDebug(true);


                    table.add(button).width(table.getWidth()).expandX().align(Align.left).row();
                    table.add(new Line()).row();

                    button.addListener(clickListener);
                });
                currentAutoComplete = currentInput;
            }
        }

        private TextButton.TextButtonStyle findStyle() {
            Skin skin = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("AssertManager", AssetManager.class).get("skins/main-skin.json", Skin.class);
            return skin.get("autocomplete-result-label", TextButton.TextButtonStyle.class);
        }

        private boolean isUpdateAutocompleteRequired(String currentInput) {
            return !currentInput.equals(currentAutoComplete);
        }

    },

    SELECT_TRANSLATE {

        private TranslateAPI translateAPI;

        @Override
        public void update(Group entity) {
            super.update(entity);

            if (translateAPI.isResultReady()) {
                int b = 2;
                translateAPI.clearResult();
            };
        }

        @Override
        public void enter(Group entity) {
            String word = ((TextButton) entity.findActor(BODY_SELECTED_WORD_ID)).getText().toString();
            Gdx.app.log(this.getClass().toString(), "Word to add [" + word + "]");

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

            if (translateAPI == null) {
                translateAPI = ((ApplicationStarter) Gdx.app.getApplicationListener()).getContext().getBean("TranslateAPI", TranslateAPI.class);
            }

            translateAPI.post(word);
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
