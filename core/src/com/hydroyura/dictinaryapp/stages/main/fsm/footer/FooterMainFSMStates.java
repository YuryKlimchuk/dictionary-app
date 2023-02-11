package com.hydroyura.dictinaryapp.stages.main.fsm.footer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hydroyura.dictinaryapp.AppStarter;


/*

                        put("MAIN_BTN_DICTIONARY", Arrays.asList("Словарь", "btn-dictionary"));
                        put("MAIN_BTN_MY_WORDS", Arrays.asList("Мои слова", "btn-my-words"));
                        put("MAIN_BTN_TRAIN", Arrays.asList("Тренажер", "btn-train"));

 */

public enum FooterMainFSMStates implements State<Group> {

    DICTIONARY() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_DICTIONARY");
            setStyle("btn-dictionary-active", btn);
        }

        @Override
        public void exit(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_DICTIONARY");
            setStyle("btn-dictionary", btn);
        }
    },
    MY_WORDS() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_MY_WORDS");
            setStyle("btn-my-words-active", btn);
        }

        @Override
        public void exit(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_MY_WORDS");
            setStyle("btn-my-words", btn);
        }
    },

    TRAIN() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_TRAIN");
            setStyle("btn-train-active", btn);
        }

        @Override
        public void exit(Group entity) {
            super.enter(entity);
            Button btn = entity.findActor("MAIN_BTN_TRAIN");
            setStyle("btn-train", btn);
        }
    },

    HIDE() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            entity.setVisible(false);
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

    public void setStyle(String styleName, Button btn) {
        Skin skin = ((AppStarter) Gdx.app.getApplicationListener()).getResource("skins/main-skin.json", Skin.class);
        ImageTextButtonStyle style = skin.get(styleName, ImageTextButtonStyle.class);
        btn.setStyle(style);
    }
}
