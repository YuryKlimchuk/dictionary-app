package com.hydroyura.dictinaryapp.stages.main.fsm.header;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;

public enum HeaderWordInputFSMStates implements State<Group> {

    HIDE() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            entity.setVisible(false);
        }
    },

    WAIT_WORD_INPUT() {},

    WORD_INPUT() {};

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
