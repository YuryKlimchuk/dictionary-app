package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.stages.main.MainStage;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;

public class FooterMainListener extends ClickListener {

    private MainStage stage;


    public FooterMainListener(MainStage stage) {
        this.stage = stage;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        Gdx.app.log(this.getClass().toString(), "clicked(), button = " + event.getListenerActor().getName());

        String btnId = event.getListenerActor().getName();
        String groupId = event.getListenerActor().getParent().getName();

        DefaultStateMachine<Group, State<Group>> fsm = stage.getFsm(groupId);
        fsm.changeState(getState(btnId));
    }


    // FIXME: create map
    private State<Group> getState(String id) {
        if(id.equals("MAIN_BTN_DICTIONARY")) return FooterMainFSMStates.DICTIONARY;
        if(id.equals("MAIN_BTN_MY_WORDS")) return FooterMainFSMStates.MY_WORDS;
        if(id.equals("MAIN_BTN_TRAIN")) return FooterMainFSMStates.TRAIN;

        throw new RuntimeException("Not fount required state");
    }
}
