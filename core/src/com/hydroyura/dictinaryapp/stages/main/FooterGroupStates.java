package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

enum FooterGroupStates implements State<Group> {

    DICTIONARY() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in DICTIONARY state");
            updateBackground(entity.findActor("FOOTER_GROUP_BACKGROUND"), Color.DARK_GRAY);
        }
    },

    MY_WORDS() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in MY_WORDS state");
            updateBackground(entity.findActor("FOOTER_GROUP_BACKGROUND"), Color.CLEAR);
        }
    },

    TRAIN() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "FooterGroup enter in TRAIN state");
            updateBackground(entity.findActor("FOOTER_GROUP_BACKGROUND"), Color.PINK);
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

}
