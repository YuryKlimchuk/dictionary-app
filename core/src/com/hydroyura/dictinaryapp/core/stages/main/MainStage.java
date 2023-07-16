package com.hydroyura.dictinaryapp.core.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hydroyura.dictinaryapp.core.context.ApplicationContext;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.stages.generators.IGroupGenerator;
import com.hydroyura.dictinaryapp.core.stages.generators.impl.BodyGenerator;
import com.hydroyura.dictinaryapp.core.stages.generators.impl.FooterAddWordGenerator;
import com.hydroyura.dictinaryapp.core.stages.generators.impl.FooterMainGenerator;
import com.hydroyura.dictinaryapp.core.stages.generators.impl.HeaderFindWordGenerator;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.body.BodyFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.footer.FooterAddWordFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.fsms.header.HeaderFindWordFSMStates;
import com.hydroyura.dictinaryapp.core.stages.main.listeners.FooterMainListener;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

@Bean(name = "MainStage")
public class MainStage extends Stage {

    private Map<String, DefaultStateMachine<Group, State<Group>>> fsms = new HashMap<>();

    @Inject(name = "Context")
    private ApplicationContext context;

    private Map<String, IGroupGenerator> groupGenerators = new LinkedHashMap<>();

    private Map<String, Group> groups = new HashMap<>();

    public MainStage() {}

    public void init() {
        groupGenerators.put(BODY_ID, new BodyGenerator());
        groupGenerators.put(FOOTER_MAIN_ID, new FooterMainGenerator(new FooterMainListener(this)));
        groupGenerators.put(HEADER_FIND_WORD_ID, new HeaderFindWordGenerator());
        groupGenerators.put(FOOTER_ADD_WORD_ID, new FooterAddWordGenerator());

        generateGroups(groupGenerators);

        //this.setDebugAll(true);

        Gdx.input.setInputProcessor(this);
    }

    private void generateGroups(Map<String, IGroupGenerator> groupGenerators) {
        Skin skin = context.getBean("AssertManager", AssetManager.class).get("skins/main-skin.json");
        Map<String, State<Group>> initStates = generateInitStates();

        groupGenerators.forEach((key, value) -> {
            Group group = value.generate(skin);
            groups.put(key, group);
            DefaultStateMachine<Group, State<Group>> fsm = new DefaultStateMachine<>(group);
            fsm.changeState(initStates.get(key));
            fsms.put(key, fsm);
            addActor(group);
        });



    }

    private Map<String, State<Group>> generateInitStates() {
        Map<String, State<Group>> map = new HashMap<>();

        map.put(BODY_ID, BodyFSMStates.WORD_INPUT);
        map.put(FOOTER_MAIN_ID, FooterMainFSMStates.DICTIONARY);
        map.put(FOOTER_ADD_WORD_ID, FooterAddWordFSMStates.HIDE);
        map.put(HEADER_FIND_WORD_ID, HeaderFindWordFSMStates.DISPLAY);


        return map;
    }


    public DefaultStateMachine<Group, State<Group>> getFsm(String id) {
        return fsms.get(id);
    }


    public Group getGroup(String id) {
        return groups.get(id);
    }

    @Override
    public void draw() {
        fsms.values().forEach(StateMachine::update);
        super.draw();
    }
}
