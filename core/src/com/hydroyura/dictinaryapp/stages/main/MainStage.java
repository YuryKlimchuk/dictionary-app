package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ObjectMap;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.stages.customs.Background;
import com.hydroyura.dictinaryapp.stages.customs.Line;
import com.hydroyura.dictinaryapp.stages.main.fsm.body.BodyFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.header.HeaderFindWordFSMStates;
import com.hydroyura.dictinaryapp.stages.main.listners.BtnClearTextListener;
import com.hydroyura.dictinaryapp.stages.main.listners.FieldWordInputListener;
import com.hydroyura.dictinaryapp.stages.main.listners.FooterMainListener;

import java.util.*;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class MainStage extends Stage {



    private AppStarter app;

    private ObjectMap<String, Group> groups = new ObjectMap<>();

    private ObjectMap<String, DefaultStateMachine<Group, State<Group>>> fsms = new ObjectMap<>();

    private ObjectMap<String, State<Group>> fsmInitialStates = new ObjectMap<>();

    public DefaultStateMachine<Group, State<Group>> getFsm(String id) {
        return fsms.get(id);
    }



    @FunctionalInterface
    private interface GroupGenerator {
        Group generate(Skin skin);
    }

    class GroupsData {

         Map<String, GroupGenerator> data = new LinkedHashMap<>();

        {
            data.put(BODY_ID, (skin) -> {
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
                translateVariantsTable.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.75f);
                translateVariantsTable.align(Align.topLeft);
                translateVariantsTable.setVisible(false);
                group.addActor(translateVariantsTable);


                return group;
            });

            data.put(FOOTER_MAIN_ID, (skin) -> {
                Group group = new Group();
                group.setName(FOOTER_MAIN_ID);
                group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
                group.setPosition(0f, 0f);
                group.addActor(new Background(Color.WHITE));

                Map<String, List<String>> FOOTER_MAIN_BTNS_SETTINGS = new HashMap<>() {
                    {
                        put("MAIN_BTN_DICTIONARY", Arrays.asList("Словарь", "btn-dictionary"));
                        put("MAIN_BTN_MY_WORDS", Arrays.asList("Мои слова", "btn-my-words"));
                        put("MAIN_BTN_TRAIN", Arrays.asList("Тренажер", "btn-train"));
                    }
                };

                FooterMainListener listener = new FooterMainListener(MainStage.this);

                int POSITION_X = Gdx.graphics.getWidth() / 7;
                int POSITION_Y = POSITION_X / 2;
                for(Map.Entry<String, List<String>> entry: FOOTER_MAIN_BTNS_SETTINGS.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    ImageTextButton btn = new ImageTextButton(value.get(0), skin.get(value.get(1), ImageTextButtonStyle.class));
                    btn.setName(key);
                    btn.addListener(listener);
                    btn.setSize(Gdx.graphics.getWidth()/7, Gdx.graphics.getWidth()/7);
                    btn.setPosition(POSITION_X, POSITION_Y);
                    btn.getLabelCell().padTop(btn.getHeight());
                    group.addActor(btn);
                    POSITION_X += 2 * Gdx.graphics.getWidth() / 7;
                };

                Line line = new Line(Color.GRAY);
                line.setY(group.getHeight());

                group.addActor(line);

                return group;
            });

            data.put(HEADER_FIND_WORD_ID, (skin) -> {
                Group group = new Group();
                group.setName(HEADER_FIND_WORD_ID);
                group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 5);
                group.setPosition(0f, Gdx.graphics.getHeight() * 4 / 5);
                group.addActor(new Background(skin.getColor("orange")));
                addActor(group);

                Label title = new Label("Словарь", skin.get("header-title", Label.LabelStyle.class));
                title.setPosition(group.getWidth() / 2 - title.getWidth() / 2, group.getHeight() * 2 / 3);
                group.addActor(title);

                TextField fieldWordInput = new TextField("", skin.get("field-word-input", TextField.TextFieldStyle.class));
                fieldWordInput.setName(FIELD_WORD_INPUT_ID);
                fieldWordInput.setSize(Gdx.graphics.getWidth() * 0.80f, group.getHeight() / 3f);
                fieldWordInput.setPosition(Gdx.graphics.getWidth() / 2f - fieldWordInput.getWidth() / 2f, group.getHeight() / 5f);
                fieldWordInput.getStyle().background.setLeftWidth(80f);

                TextField.TextFieldListener fieldWordInputListener = new FieldWordInputListener();
                fieldWordInput.setTextFieldListener(fieldWordInputListener);

                fieldWordInput.getStyle().focusedBackground.setLeftWidth(Gdx.graphics.getWidth() / 9f);
                fieldWordInput.getStyle().focusedBackground.setRightWidth(Gdx.graphics.getWidth() / 9f);

                group.addActor(fieldWordInput);

                ImageButton btnClearText = new ImageButton(skin.get("clear-text", ImageButton.ImageButtonStyle.class));
                btnClearText.setSize(fieldWordInput.getHeight() / 3f, fieldWordInput.getHeight() / 3f);
                btnClearText.setPosition(fieldWordInput.getX() + fieldWordInput.getWidth() * 0.9f, (fieldWordInput.getY() + fieldWordInput.getHeight() / 2f) - btnClearText.getHeight() / 2f);

                ClickListener btnClearTextListener = new BtnClearTextListener();
                btnClearText.addListener(btnClearTextListener);

                group.addActor(btnClearText);

                return group;
            });
        }


        public Map<String, GroupGenerator> getData() {
            return data;
        }
    }



    public MainStage() {
        app = (AppStarter) Gdx.app.getApplicationListener();
        Skin skin = app.getResource("skins/main-skin.json", Skin.class);

        fsmInitialStates.put(FOOTER_MAIN_ID, FooterMainFSMStates.DICTIONARY);
        fsmInitialStates.put(HEADER_FIND_WORD_ID, HeaderFindWordFSMStates.DISPLAY);
        fsmInitialStates.put(BODY_ID, BodyFSMStates.WORD_INPUT);

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

    }

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




    @Override
    public void draw() {
        StreamSupport.stream(fsms.spliterator(), false).forEach(entry -> entry.value.update());
        super.draw();
    }


}
