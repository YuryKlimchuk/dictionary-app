package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.HttpClient;

import java.util.*;

public class MainStage extends Stage {

    private AppStarter app;
    private Group footerGroup;
    private Group headerGroup;

    ClickListener clickListener;

    private DefaultStateMachine<Group, HeaderGroupStates> headerGroupFsm;
    private DefaultStateMachine<Group, FooterGroupStates> footerGroupFsm;

    private int MAIN_BUTTON_SIZE_X = Gdx.graphics.getWidth()/7;
    private int MAIN_BUTTON_SIZE_Y = Gdx.graphics.getWidth()/7;

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

        Label background = new Label("", style);
        background.setName("HEADER_GROUP_BACKGROUND");
        background.setColor(Color.DARK_GRAY);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/6);
        background.setPosition(0, 0);
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

        Json json = new Json();
        json.setTypeName(null);
        //json.setUsePrototypes(false);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(body);

        httpClient.post(url, headers, mapper.writeValueAsString(body));
    }

    @Override
    public void draw() {
        super.draw();
        //headerGroupFsm.update();
        footerGroupFsm.update();
    }
}
