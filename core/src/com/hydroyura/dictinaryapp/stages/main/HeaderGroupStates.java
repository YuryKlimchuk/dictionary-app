package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;

enum HeaderGroupStates implements State<Group> {

    DICTIONARY() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in DICTIONARY state");
        }
    },

    MY_WORDS() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in MY_WORDS state");
        }
    },

    TRAIN() {
        @Override
        public void enter(Group entity) {
            Gdx.app.log(this.getClass().toString(), "HeaderGroup enter in TRAIN state");
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
}
