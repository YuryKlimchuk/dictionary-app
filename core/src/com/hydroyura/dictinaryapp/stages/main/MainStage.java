package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hydroyura.dictinaryapp.GameContext;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.header.HeaderFindWordFSMStates;
import com.hydroyura.dictinaryapp.stages.main.groupgenerators.impl.FOOTER_MAIN_ID;
import com.hydroyura.dictinaryapp.stages.main.groupgenerators.impl.HEADER_FIND_WORD_ID;
import com.hydroyura.dictinaryapp.stages.main.listners.FooterMainListener;

import java.util.HashMap;
import java.util.Map;

public class MainStage extends Stage {

    private GameContext app;

    private Map<String, DefaultStateMachine<Group, State<Group>>> fsms = new HashMap<>();

    /*
    private ObjectMap<String, Group> groups = new ObjectMap<>();

    private ObjectMap<String, DefaultStateMachine<Group, State<Group>>> fsms = new ObjectMap<>();

    private ObjectMap<String, State<Group>> fsmInitialStates = new ObjectMap<>();

    public DefaultStateMachine<Group, State<Group>> getFsm(String id) {
        return fsms.get(id);
    }

    Map<String, GroupGenerator> data2 = new LinkedHashMap<>() {{
       data2.put(BODY_ID, new BODY_ID());
    }};



    @FunctionalInterface
    private interface GroupGenerator {
        Group generate(Skin skin);
    }


    class GroupsData {
         Map<String, GroupGenerator> data = new LinkedHashMap<>();

        {




            data.put(FOOTER_ADD_WORD_ID, (skin) -> {
                Group group = new Group();
                group.setName(FOOTER_ADD_WORD_ID);
                group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 5);
                group.setPosition(0f, -1 * Gdx.graphics.getHeight() / 5);
                group.addActor(new Background(skin.getColor("orange")));
                addActor(group);


                TextButton.TextButtonStyle st1 = skin.get("btn-select-collection", TextButton.TextButtonStyle.class);
                TextButton.TextButtonStyle st2 = skin.get("btn-save-translation", TextButton.TextButtonStyle.class);

                TextButton b1 = new TextButton("Выбрать коллекцию", st1);
                b1.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 10);
                b1.setPosition(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 20);
                b1.getLabel().setWrap(true);
                group.addActor(b1);

                TextButton b2 = new TextButton("Добавить в колекцию", st2);
                b2.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 10);
                b2.setPosition(11 * Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() / 20);
                b2.getLabel().setWrap(true);
                group.addActor(b2);

                return group;
            });
        }


        public Map<String, GroupGenerator> getData() {
            return data;
        }
    }

*/

    public MainStage() {}


    public DefaultStateMachine<Group, State<Group>> getFsm(String id) {
        return fsms.get(id);
    }

    public void init() {
        app = (GameContext) Gdx.app.getApplicationListener();
        Skin skin = app.getAssetManager().get("skins/main-skin.json", Skin.class);


        Group groupFooterMainId = new FOOTER_MAIN_ID(new FooterMainListener(this)).generate(skin);
        DefaultStateMachine<Group, State<Group>> fsmFooterMainId = new DefaultStateMachine<>(groupFooterMainId);
        fsmFooterMainId.changeState(FooterMainFSMStates.DICTIONARY);
        fsms.put(MainStageConstants.FOOTER_MAIN_ID, fsmFooterMainId);
        addActor(groupFooterMainId);

        Group headerFindWordId = new HEADER_FIND_WORD_ID().generate(skin);
        DefaultStateMachine<Group, State<Group>> fsmHeaderFindWordId = new DefaultStateMachine<>(headerFindWordId);
        fsmHeaderFindWordId.changeState(HeaderFindWordFSMStates.DISPLAY);
        fsms.put(MainStageConstants.HEADER_FIND_WORD_ID, fsmHeaderFindWordId);
        addActor(headerFindWordId);



        /*
        fsmInitialStates.put(FOOTER_MAIN_ID, FooterMainFSMStates.DICTIONARY);
        fsmInitialStates.put(HEADER_FIND_WORD_ID, HeaderFindWordFSMStates.DISPLAY);
        fsmInitialStates.put(BODY_ID, BodyFSMStates.WORD_INPUT);
        fsmInitialStates.put(FOOTER_ADD_WORD_ID, FooterWordAddFSMStates.HIDE);

        GroupsData groupsData = new GroupsData();
        groupsData.getData().forEach(
                (key, value) -> {
                    Group group = value.generate(skin);
                    addActor(group);
                    groups.put(key, group);
                }
        );

        StreamSupport.stream(groups.spliterator(), false).forEach(
                entry -> {
                    DefaultStateMachine<Group, State<Group>> fsm = new DefaultStateMachine<>(entry.value);
                    fsm.changeState(fsmInitialStates.get(entry.key));
                    fsms.put(entry.key, fsm);
                }
        );

         */
    }



/*
    // FIXME add generic return type <? extends Actor>
    public <T extends Actor> T findActor(String groupId, String actorId) {
        return groups.get(groupId).findActor(actorId);
    }

    // FIXME: To need refactor
    public<T extends Actor> T findActor(String groupId, String actorId, Class<T> clazz) {

        Actor actor = groups.get(groupId).findActor(actorId);

        T actor2;

        try {
            actor2 = clazz.cast(actor);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            actor2 = null;
        }

        return actor2;
    }

*/



    @Override
    public void draw() {
        fsms.values().forEach(DefaultStateMachine::update);
        super.draw();
    }


/*
    class BODY_ID implements GroupGenerator {

        @Override
        public Group generate(Skin skin) {
            Group group = new Group();
            group.setName(BODY_ID);
            group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            group.setPosition(0f, 0f);
            group.addActor(new Background(Color.WHITE));

            Table table = new Table(skin);
            table.setName(BODY_WORDS_AUTOCOMPLETE_RESULT_ID);
            table.setWidth(Gdx.graphics.getWidth() * 1f);
            //table.setHeight(Gdx.graphics.getHeight() / 2f);
            table.setPosition(0f, Gdx.graphics.getHeight() * 4 / 5);
            group.addActor(table);
            table.align(Align.topLeft);

            Label label = new Label("", skin.get("label-for-add-word", Label.LabelStyle.class));
            label.setName(BODY_WORDS_WORD_ADD_TITLE_ID);
            label.setVisible(false);
            group.addActor(label);

            Label addWordTitleTranscription = new Label("", skin.get("label-transcription", Label.LabelStyle.class));
            addWordTitleTranscription.setName(BODY_WORDS_WORD_ADD_TITLE_TRANSCRIPTION_ID);
            addWordTitleTranscription.setVisible(false);
            group.addActor(addWordTitleTranscription);

            // FIXME: add click listener
            ImageButton pronunciationBtn = new ImageButton(skin.get("pronunciation", ImageButton.ImageButtonStyle.class));
            pronunciationBtn.setName(BODY_WORDS_WORD_ADD_PRONUNCIATION_ID);
            pronunciationBtn.setSize(Gdx.graphics.getWidth() / 10f, Gdx.graphics.getWidth() / 10f);
            pronunciationBtn.setPosition(Gdx.graphics.getWidth() / 2f - pronunciationBtn.getWidth() / 2f, Gdx.graphics.getHeight() * 0.75f);
            pronunciationBtn.setVisible(false);
            group.addActor(pronunciationBtn);

            // Table for displaying translate variants
            Table translateVariantsTable = new Table();
            translateVariantsTable.setName(BODY_TRANSLATION_VARIANTS_TABLE_ID);
            translateVariantsTable.setWidth(Gdx.graphics.getWidth() * 0.9f);
            translateVariantsTable.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.7f);
            translateVariantsTable.align(Align.topLeft);
            translateVariantsTable.setVisible(false);
            group.addActor(translateVariantsTable);

            return group;
        }
    }

 */
}



