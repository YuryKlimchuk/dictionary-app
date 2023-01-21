package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hydroyura.dictinaryapp.AppStarter;

import java.util.Map;

enum FooterGroupStates implements State<Group> {

    DICTIONARY() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in DICTIONARY state");
            updateBtnStyle("MAIN_BTN_DICTIONARY", "btn-dictionary-active", entity);
        }

        @Override
        public void exit(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup exit DICTIONARY state");
            updateBtnStyle("MAIN_BTN_DICTIONARY", "btn-dictionary", entity);
        }
    },

    MY_WORDS() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in MY_WORDS state");
            updateBtnStyle("MAIN_BTN_MY_WORDS", "btn-my-words-active", entity);
        }

        @Override
        public void exit(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup exit MY_WORDS state");
            updateBtnStyle("MAIN_BTN_MY_WORDS", "btn-my-words", entity);
        }
    },

    TRAIN() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in TRAIN state");
            updateBtnStyle("MAIN_BTN_TRAIN", "btn-train-active", entity);
        }

        @Override
        public void exit(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup exit TRAIN state");
            updateBtnStyle("MAIN_BTN_TRAIN", "btn-train", entity);
        }
    };

    @Override
    public void update(Group entity) {}

    @Override
    public boolean onMessage(Group entity, Telegram telegram) {
        return false;
    }

    void updateBtnStyle(String id, String styleName, Group group) {
        Skin skin = ((AppStarter) Gdx.app.getApplicationListener()).getResource("skin/custom-skin.json", Skin.class);
        ImageButton button = group.findActor(id);
        button.setStyle(skin.get(styleName, ImageButton.ImageButtonStyle.class));
    }
}
