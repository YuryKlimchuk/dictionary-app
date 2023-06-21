package com.hydroyura.dictinaryapp.core.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hydroyura.dictinaryapp.core.context.ApplicationContext;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.stages.generators.IGroupGenerator;
import com.hydroyura.dictinaryapp.core.stages.generators.impl.FooterMainGenerator;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.listeners.FooterMainListener;

import java.util.HashMap;
import java.util.Map;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.FOOTER_MAIN_ID;

@Bean(name = "MainStage")
public class MainStage extends Stage {

    private Map<String, DefaultStateMachine<Group, State<Group>>> fsms = new HashMap<>();

    @Inject(name = "Context")
    private ApplicationContext context;

    IGroupGenerator footerMainGenerator = new FooterMainGenerator(new FooterMainListener(this));

    public MainStage() {

    }

    public void init() {
        Group footerMainGroup = footerMainGenerator.generate(context.getBean("AssertManager", AssetManager.class).get("skins/main-skin.json"));
        DefaultStateMachine<Group, State<Group>> footerMainFSM = new DefaultStateMachine<>(footerMainGroup);
        fsms.put(FOOTER_MAIN_ID, footerMainFSM);
        footerMainFSM.setGlobalState(FooterMainFSMStates.DICTIONARY);
        addActor(footerMainGroup);

        Gdx.input.setInputProcessor(this);



    }


    public DefaultStateMachine<Group, State<Group>> getFsm(String id) {
        return fsms.get(id);
    }
}
