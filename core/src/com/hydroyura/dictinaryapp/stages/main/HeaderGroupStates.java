package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

enum HeaderGroupStates implements State<Group> {

    DICTIONARY() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in DICTIONARY state");

            //updateBackground(entity.findActor("HEADER_GROUP_BACKGROUND"), Color.BLUE);
            updateTitle(entity.findActor("HEADER_GROUP_TITLE"), "Dictionary", Color.BLUE);
        }
    },

    MY_WORDS() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in MY_WORDS state");

            //updateBackground(entity.findActor("HEADER_GROUP_BACKGROUND"), Color.DARK_GRAY);
            updateTitle(entity.findActor("HEADER_GROUP_TITLE"), "My words", Color.DARK_GRAY);
        }
    },

    TRAIN() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in TRAIN state");

            //updateBackground(entity.findActor("HEADER_GROUP_BACKGROUND"), Color.PURPLE);
            updateTitle(entity.findActor("HEADER_GROUP_TITLE"), "Train", Color.PURPLE);
        }
    };

    @Override
    public void enter(Group entity) {}

    @Override
    public void update(Group entity) {}

    @Override
    public void exit(Group entity) {}

    @Override
    public boolean onMessage(Group entity, Telegram telegram) {
        return false;
    }


    private static void updateBackground(Label label, Color color) {
        label.setColor(color);
    }

    private static void updateTitle(Label label, String title, Color color) {
        label.setText(title);
        label.setColor(color);
        label.setPosition(Gdx.graphics.getWidth()/2 - label.getWidth()/2, 20f);
    }
}
