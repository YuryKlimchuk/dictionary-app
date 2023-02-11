package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.stages.main.listners.FooterMainListener;

import java.util.*;
import java.util.stream.StreamSupport;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.BODY_ID;
import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.FOOTER_MAIN_ID;

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
        }


        public Map<String, GroupGenerator> getData() {
            return data;
        }
    }



    public MainStage() {
        app = (AppStarter) Gdx.app.getApplicationListener();
        Skin skin = app.getResource("skins/main-skin.json", Skin.class);

        fsmInitialStates.put(FOOTER_MAIN_ID, FooterMainFSMStates.DICTIONARY);

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






    @Override
    public void draw() {
        StreamSupport.stream(fsms.spliterator(), false).forEach(entry -> entry.value.update());
        super.draw();
    }





















































































/*
    //----------- FSM ------------------
    private DefaultStateMachine<Group, BodyFSMStates> fsmBody;
    private ObjectMap<String, DefaultStateMachine<Group, State<Group>>> fsms = new ObjectMap<>();
    //----------------------------------


    private Map<String, List<String>> FOOTER_MAIN_BTNS_SETTINGS = new HashMap<>() {
        {
            put("MAIN_BTN_DICTIONARY", Arrays.asList("Словарь", "btn-dictionary"));
            put("MAIN_BTN_MY_WORDS", Arrays.asList("Мои слова", "btn-my-words"));
            put("MAIN_BTN_TRAIN", Arrays.asList("Тренажер", "btn-train"));
        }
    };
*/

    /*

    private AppStarter app;

    private Skin skin;

    private ObjectMapper mapper = new ObjectMapper();

    private Group footerMain, headerWordInput, body;

    private ObjectMap<String, Group> groups = new ObjectMap<>();

    private TextFieldListener fieldWordInputListener = new FieldWordInputListener();

    private ClickListener btnClearTextListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            Group group = event.getListenerActor().getParent();
            TextField textField = (TextField) group.findActor(FIELD_WORD_INPUT_ID);
            textField.setText("");

            ((Table) groups.get(BODY_ID).findActor(BODY_WORDS_AUTOCOMPLETE_RESULT_ID)).clearChildren();

        }
    };

    public MainStage() {
        Gdx.app.log(this.getClass().toString(), "MainStage()");
        app = (AppStarter) Gdx.app.getApplicationListener();
        skin = app.getResource("skins/main-skin.json", Skin.class);

        groups.put(BODY_ID, bodyCreate());
        groups.put(FOOTER_MAIN_ID, footerMainCreate());
        groups.put(HEADER_WORD_INPUT_ID, headerWordInputCreate());

        //groups.put(BODY_ID, GroupsData.getData().get("").generate());


        StreamSupport.stream(GroupsData.getData().spliterator(), false)
                .collect(Collectors.toMap(
                        entry -> entry.key,
                        entry -> entry.value.generate()));


    }

    Group footerMainCreate() {
        Group group = new Group();
        group.setName(FOOTER_MAIN_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
        group.setPosition(0f, 0f);
        group.addActor(new Background(Color.WHITE));
        addActor(group);

        int POSITION_X = Gdx.graphics.getWidth() / 7;
        int POSITION_Y = POSITION_X / 2;
        for(Map.Entry<String, List<String>> entry: FOOTER_MAIN_BTNS_SETTINGS.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            ImageTextButton btn = new ImageTextButton(value.get(0), skin.get(value.get(1), ImageTextButtonStyle.class));
            btn.setSize(Gdx.graphics.getWidth()/7, Gdx.graphics.getWidth()/7);
            btn.setPosition(POSITION_X, POSITION_Y);
            btn.getLabelCell().padTop(btn.getHeight());
            group.addActor(btn);
            POSITION_X += 2 * Gdx.graphics.getWidth() / 7;
        };

        fsms.put(FOOTER_MAIN_ID, new DefaultStateMachine<>(group, FooterMainFSMStates.DICTIONARY));

        return group;
    };


    Group headerWordInputCreate() {
        Group group = new Group();
        group.setName(HEADER_WORD_INPUT_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 5);
        group.setPosition(0f, Gdx.graphics.getHeight() * 4 / 5);
        group.addActor(new Background(skin.getColor("dictionary-color")));
        addActor(group);

        Label title = new Label("Словарь", skin.get("header-title", LabelStyle.class));
        //title.setColor(Color.BLUE);
        title.setPosition(group.getWidth() / 2 - title.getWidth() / 2, group.getHeight() * 2 / 3);
        group.addActor(title);

        TextField fieldWordInput = new TextField("", skin.get("field-word-input", TextFieldStyle.class));
        fieldWordInput.setName(FIELD_WORD_INPUT_ID);
        fieldWordInput.setSize(Gdx.graphics.getWidth() * 0.80f, group.getHeight() / 3f);
        fieldWordInput.setPosition(Gdx.graphics.getWidth() / 2f - fieldWordInput.getWidth() / 2f, group.getHeight() / 5f);
        fieldWordInput.getStyle().background.setLeftWidth(80f);
        fieldWordInput.setTextFieldListener(fieldWordInputListener);
        group.addActor(fieldWordInput);

        ImageButton btnClearText = new ImageButton(skin.get("clear-text", ImageButtonStyle.class));
        btnClearText.setSize(fieldWordInput.getHeight() / 3f, fieldWordInput.getHeight() / 3f);
        btnClearText.setPosition(fieldWordInput.getX() + fieldWordInput.getWidth() * 0.9f, (fieldWordInput.getY() + fieldWordInput.getHeight() / 2f) - btnClearText.getHeight() / 2f);
        btnClearText.addListener(btnClearTextListener);
        group.addActor(btnClearText);

        fsms.put(HEADER_WORD_INPUT_ID, new DefaultStateMachine<>(group, HeaderWordInputFSMStates.WORD_INPUT));

        return group;
    }


    Group bodyCreate() {
        Group group = new Group();
        group.setName(BODY_ID);
        group.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        group.setPosition(0f, 0);
        group.addActor(new Background(Color.WHITE));
        addActor(group);

        Table table = new Table(skin);
        table.setName(BODY_WORDS_AUTOCOMPLETE_RESULT_ID);
        table.setWidth(Gdx.graphics.getWidth() * 0.85f);
        table.setHeight(Gdx.graphics.getHeight() / 2f);
        table.setPosition(15f, 150f);
        group.addActor(table);
        table.align(Align.topLeft);

        // label-for-add-word
        Label addWordTitle = new Label("", skin.get("label-for-add-word", LabelStyle.class));
        addWordTitle.setName(BODY_WORDS_WORD_ADD_TITLE_ID);
        addWordTitle.setPosition(Gdx.graphics.getWidth() / 2f, 550f);
        addWordTitle.setVisible(false);
        group.addActor(addWordTitle);

        Label addWordTitleTranscription = new Label("", skin.get("label-for-add-word", LabelStyle.class));
        addWordTitleTranscription.setName(BODY_WORDS_WORD_ADD_TITLE_TRANSCRIPTION_ID);
        addWordTitleTranscription.setPosition(Gdx.graphics.getWidth() / 2f, 450f);
        addWordTitleTranscription.setVisible(false);
        group.addActor(addWordTitleTranscription);

        ImageButton pronunciationBtn = new ImageButton(skin.get("pronunciation", ImageButtonStyle.class));
        pronunciationBtn.setName(BODY_WORDS_WORD_ADD_PRONUNCIATION_ID);
        pronunciationBtn.setSize(Gdx.graphics.getWidth() / 10f, Gdx.graphics.getWidth() / 10f);
        pronunciationBtn.setPosition(Gdx.graphics.getWidth() / 2f - pronunciationBtn.getWidth() / 2f, Gdx.graphics.getHeight() * 0.75f);
        pronunciationBtn.setVisible(false);
        group.addActor(pronunciationBtn);

        fsms.put(BODY_ID, new DefaultStateMachine<>(group, BodyFSMStates.WORD_INPUT));

        return group;
    }



    public <T extends Actor> T findActor(String groupId, String actorId) {
        try {
            return groups.get(groupId).findActor(actorId);
        } catch (ClassCastException ex) {
            Gdx.app.log(this.getClass().toString(), "Cannot case to class");
        }
        return null;
    }

    public DefaultStateMachine<Group, State<Group>> getFSM(String id) {
        return fsms.get(id);
    }



    @Override
    public void draw() {
        super.draw();
        StreamSupport.stream(fsms.spliterator(), false).forEach(item -> item.value.update());
    }



*/












































    /*
    private AppStarter app;
    private Group footerGroup;
    private Group headerGroup;

    private Group bodyGroup;

    ClickListener clickListener;

    private DefaultStateMachine<Group, HeaderGroupStates> headerGroupFsm;
    private DefaultStateMachine<Group, FooterGroupStates> footerGroupFsm;

    private int MAIN_BUTTON_SIZE_X = Gdx.graphics.getWidth()/7;
    private int MAIN_BUTTON_SIZE_Y = Gdx.graphics.getWidth()/7;

    private ObjectMapper mapper = new ObjectMapper();

    private Skin skin;

    public MainStage() {
        super(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        app = (AppStarter) Gdx.app.getApplicationListener();
        skin = app.getResource("skin/custom-skin.json", Skin.class);

        createFooterGroup();

        try {
            createHeaderGroup();
        } catch (Exception ex) {

        }

        createBodyGroup();

        Gdx.app.log(this.getClass().toString(), "created");
    }

    private void  createFooterGroup() {
        footerGroup = new Group();
        footerGroup.setPosition(0f, 0f);
        footerGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
        footerGroup.setName("FOOTER_GROUP");
        addActor(footerGroup);

        Background background = new Background(Color.WHITE);
        background.setName("FOOTER_GROUP_BACKGROUND");
        footerGroup.addActor(background);

        Map<String, Collection<String>> FOOTER_BTN_SETTINGS = new HashMap<>();
        FOOTER_BTN_SETTINGS.put("MAIN_BTN_DICTIONARY", Arrays.asList("Dictionary", "btn-dictionary", "btn-dictionary-active"));
        FOOTER_BTN_SETTINGS.put("MAIN_BTN_MY_WORDS", Arrays.asList("My words", "btn-my-words", "btn-my-words-active"));
        FOOTER_BTN_SETTINGS.put("MAIN_BTN_TRAIN", Arrays.asList("Train", "btn-train", "btn-train-active"));

        clickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(this.getClass().toString(), "Btn with name: " + event.getListenerActor().getName() + " was pressed");
                if(event.getListenerActor().getName().equals("MAIN_BTN_DICTIONARY")) {
                    headerGroupFsm.changeState(HeaderGroupStates.DICTIONARY);
                    footerGroupFsm.changeState(FooterGroupStates.DICTIONARY);
                }
                if(event.getListenerActor().getName().equals("MAIN_BTN_MY_WORDS")) {
                    headerGroupFsm.changeState(HeaderGroupStates.MY_WORDS);
                    footerGroupFsm.changeState(FooterGroupStates.MY_WORDS);
                }
                if(event.getListenerActor().getName().equals("MAIN_BTN_TRAIN")) {
                    headerGroupFsm.changeState(HeaderGroupStates.TRAIN);
                    footerGroupFsm.changeState(FooterGroupStates.TRAIN);
                }
            }
        };

        int POSITION_X = MAIN_BUTTON_SIZE_X;
        int POSITION_Y = MAIN_BUTTON_SIZE_X/2;

        for(Map.Entry<String, Collection<String>> entry: FOOTER_BTN_SETTINGS.entrySet()) {
            ArrayList<String> list = new ArrayList<>(entry.getValue());
            Vector2 position = new Vector2(POSITION_X, POSITION_Y);
            footerGroup.addActor(createMainButton(list.get(0), entry.getKey(), skin.get(list.get(1), ImageButtonStyle.class), position));
            POSITION_X += (MAIN_BUTTON_SIZE_X * 2);
        };

        footerGroupFsm = new DefaultStateMachine<>(footerGroup);
        footerGroupFsm.changeState(FooterGroupStates.DICTIONARY);

    }

    private ImageButton createMainButton(String title, String id, ImageButtonStyle style, Vector2 position) {
        ImageButton btn = new ImageButton(style);

        btn.setSize(MAIN_BUTTON_SIZE_X, MAIN_BUTTON_SIZE_Y);
        btn.setPosition(position.x, position.y);
        btn.setName(id);
        btn.addListener(clickListener);

        return btn;
    };

    private void createHeaderGroup() throws JsonProcessingException {
        headerGroup = new Group();
        headerGroup.setPosition(0, Gdx.graphics.getHeight()*5/6);
        headerGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/6);
        headerGroup.setName("HEADER_GROUP");
        headerGroup.setColor(Color.RED);

        LabelStyle style = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("title", LabelStyle.class);

        Background background = new Background(Color.DARK_GRAY);
        background.setName("HEADER_GROUP_BACKGROUND");
        headerGroup.addActor(background);

        Label title = new Label("TITLE_1", style);
        title.setName("HEADER_GROUP_TITLE");
        title.setColor(background.getColor());
        title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2,20f);
        headerGroup.addActor(title);

        TextFieldStyle searchInputStyle = app
                .getResource("skin/custom-skin.json", Skin.class)
                .get("search-input", TextFieldStyle.class);

        TextField searchInput = new TextField("", searchInputStyle);
        searchInput.getStyle().font.setColor(Color.BLUE);
        searchInput.getStyle().fontColor = Color.BROWN;
        searchInput.setName("HEADER_GROUP_SEARCH_INPUT");
        searchInput.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight()/20);
        searchInput.setPosition(
                Gdx.graphics.getWidth()/2 - searchInput.getWidth()/2,
                Gdx.graphics.getHeight()/10
        );


        searchInput.setOnscreenKeyboard(new TextField.DefaultOnscreenKeyboard());
        searchInput.setTextFieldListener(new SearchTextFieldListener());


        headerGroup.addActor(searchInput);


        addActor(headerGroup);
        headerGroupFsm = new DefaultStateMachine<>(headerGroup, HeaderGroupStates.DICTIONARY);


        // test http request
        HttpClient httpClient = app.getHttpClient();

        String url = HttpClient.URL_TRANSLATE;
        Map<String, String> headers = Map.of(
                "X-RapidAPI-Key", "244b5dd242msh2d660d8777daa5cp110e59jsn2bcfbca1afed",
                "X-RapidAPI-Host", "ai-translate.p.rapidapi.com",
                "content-type", "application/json"
        );

        Map<String, Object> body = new HashMap<>();
        body.put("texts", Arrays.asList("Hello world"));
        body.put("tls", Arrays.asList("ru"));
        body.put("sl", "en");

        mapper.writeValueAsString(body);
        //httpClient.post(url, headers, mapper.writeValueAsString(body));

        String url2 = HttpClient.URL_AUTOCOMPLETE;
        Map<String, String> headers2 = Map.of(
                "X-RapidAPI-Key", "a29d3e29demshc51d3e75c10c484p1eebcejsn8aaa8c3f733f",
                "X-RapidAPI-Host", "typewise-ai.p.rapidapi.com",
                "content-type", "application/json"
        );

        Map<String, Object> body2 = new HashMap<>();
        body2.put("text", "foo");
        body2.put("correctTypoInPartialWord", false);
        body2.put("language", "en");

        mapper.writeValueAsString(body2);
        //httpClient.post(url2, headers2, mapper.writeValueAsString(body2));


    }

    private void createBodyGroup() {
        bodyGroup = new Group();
        bodyGroup.setName("BODY_GROUP");
        bodyGroup.setPosition(0, Gdx.graphics.getHeight()/8);
        bodyGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 17 / 24);
        addActor(bodyGroup);

        Background background = new Background(Color.RED);
        background.setName("BODY_GROUP_BACKGROUND");
        //bodyGroup.addActor(background);

        Skin skin = app.getResource("skin/custom-skin.json", Skin.class);

        VerticalGroup searchResult = new VerticalGroup();
        searchResult.columnAlign(Align.left);
        searchResult.align(Align.left);
        bodyGroup.addActor(searchResult);

        searchResult.setName("SEARCH_RESULT");
        searchResult.setPosition(5, 300);
        searchResult.setWidth(Gdx.graphics.getWidth() - 10);
        searchResult.setHeight(200);


        LabelStyle style = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("title", LabelStyle.class);

        Label label = new Label("La la la", style);
        label.setPosition(100, 100);
        searchResult.addActor(label);

        Label label1 = new Label("La la la2222222222222", style);
        searchResult.addActor(label1);




        app.setSearchResult(searchResult);

        bodyGroup.addActor(searchResult);
    }

    @Override
    public void draw() {
        super.draw();
        //headerGroupFsm.update();
        //footerGroupFsm.update();
    }
     */
}
