package com.hydroyura.dictinaryapp.core.stages.main.fsms.header;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public enum HeaderFindWordFSMStates implements State<Group> {

    HIDE() {
        @Override
        public void enter(Group entity) {
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, Gdx.graphics.getHeight());
            action.setDuration(1.0f);

            entity.addAction(action);
        }
    },

    DISPLAY() {};


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
