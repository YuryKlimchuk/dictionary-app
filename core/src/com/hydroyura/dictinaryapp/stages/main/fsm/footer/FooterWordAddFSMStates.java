package com.hydroyura.dictinaryapp.stages.main.fsm.footer;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public enum FooterWordAddFSMStates implements State<Group> {

    HIDE() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, -entity.getHeight());
            action.setDuration(1.0f);

            entity.addAction(action);
        }
    },

    DISPLAY() {
        @Override
        public void enter(Group entity) {
            super.enter(entity);
            MoveToAction action = new MoveToAction();
            action.setPosition(0f, 0f);
            action.setDuration(1.0f);

            entity.addAction(action);
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
