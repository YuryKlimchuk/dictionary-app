package com.hydroyura.dictinaryapp.stages.main.fsm.footer;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;

public enum FooterMainFSMStates implements State<Group> {

    DICTIONARY() {},
    MY_WORDS() {},

    TRAIN() {},

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
}
