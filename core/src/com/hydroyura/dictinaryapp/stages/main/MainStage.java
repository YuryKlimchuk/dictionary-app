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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hydroyura.dictinaryapp.AppStarter;

import java.util.HashMap;
import java.util.Map;

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
        createHeaderGroup();

        Gdx.app.log(this.getClass().toString(), "created");
    }

    private void  createFooterGroup() {
        footerGroup = new Group();
        footerGroup.setPosition(0f, 0f);
        footerGroup.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
        footerGroup.setName("FOOTER_GROUP");
        addActor(footerGroup);

        Background background = new Background(Color.CORAL);
        footerGroup.addActor(background);

        /*
        LabelStyle labelStyle = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("title", LabelStyle.class);
        Label background = new Label("", labelStyle);
        background.setName("FOOTER_GROUP_BACKGROUND");
        background.setColor(Color.WHITE);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
        background.setPosition(0f, 0f);
        background.setZIndex(1);
        footerGroup.addActor(background);

        Skin customSkin = app.getResource("skin/custom-skin.json", Skin.class);

        // key: btn id; value: btn title
        Map<String, String> BTN_DATA = new HashMap<>() {{
            put("MAIN_BTN_DICTIONARY", "Dictionary");
            put("MAIN_BTN_MY_WORDS", "My words");
            put("MAIN_BTN_TRAIN", "Train");
        }};

        Map<String, String> BTN_DATA_STYLES = new HashMap<>() {{
            put("MAIN_BTN_DICTIONARY", "btn-dictionary");
            put("MAIN_BTN_MY_WORDS", "btn-my-words");
            put("MAIN_BTN_TRAIN", "btn-train");
        }};

        int POSITION_X = MAIN_BUTTON_SIZE_X;
        int POSITION_Y = MAIN_BUTTON_SIZE_X;

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

        for(Map.Entry<String, String> entry: BTN_DATA.entrySet()) {
            Vector2 position = new Vector2(POSITION_X, POSITION_Y/2);
            footerGroup.addActor(createMainButton(entry.getValue(), entry.getKey(), customSkin.get(BTN_DATA_STYLES.get(entry.getKey()), ImageButtonStyle.class), position));
            POSITION_X += (MAIN_BUTTON_SIZE_X * 2);
        };

        footerGroupFsm = new DefaultStateMachine<>(footerGroup, FooterGroupStates.DICTIONARY);

        addActor(footerGroup);


         */
    }

    private ImageButton createMainButton(String title, String id, ImageButtonStyle style, Vector2 position) {
        ImageButton btn = new ImageButton(style);

        btn.setSize(MAIN_BUTTON_SIZE_X, MAIN_BUTTON_SIZE_Y);
        btn.setPosition(position.x, position.y);
        btn.setName(id);
        btn.addListener(clickListener);
        btn.setZIndex(1500);

        return btn;
    };

    private void createHeaderGroup() {
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

        addActor(headerGroup);

        headerGroupFsm = new DefaultStateMachine<>(headerGroup, HeaderGroupStates.DICTIONARY);

    }

    @Override
    public void draw() {
        super.draw();
        //headerGroupFsm.update();
        //footerGroupFsm.update();
    }
}
