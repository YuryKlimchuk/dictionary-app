package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hydroyura.dictinaryapp.AppStarter;

import java.util.Collection;
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

    public MainStage() {
        super(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        app = (AppStarter) Gdx.app.getApplicationListener();

        createFooterGroup();
        createHeaderGroup();

        Gdx.app.log(this.getClass().toString(), "created");
    }

    private void  createFooterGroup() {
        footerGroup = new Group();
        footerGroup.setPosition(0f, 0f);
        footerGroup.setName("FOOTER_GROUP");

        LabelStyle labelStyle = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("title", LabelStyle.class);
        Label background = new Label("", labelStyle);
        background.setName("FOOTER_GROUP_BACKGROUND");
        background.setColor(Color.PURPLE);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/6);
        background.setPosition(0f, 0f);
        background.setZIndex(1);
        footerGroup.addActor(background);

        ImageTextButtonStyle style = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("default", ImageTextButtonStyle.class);

        // key: btn id; value: btn title
        Map<String, String> BTN_DATA = new HashMap<>() {{
            put("MAIN_BTN_DICTIONARY", "Dictionary");
            put("MAIN_BTN_MY_WORDS", "My words");
            put("MAIN_BTN_TRAIN", "Train");
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
            Vector2 position = new Vector2(POSITION_X, POSITION_Y);
            footerGroup.addActor(createMainButton(entry.getValue(), entry.getKey(), style, position));
            POSITION_X += (MAIN_BUTTON_SIZE_X * 2);
        };

        footerGroupFsm = new DefaultStateMachine<>(footerGroup, FooterGroupStates.DICTIONARY);

        addActor(footerGroup);
    }

    private ImageTextButton createMainButton(String title, String id, ImageTextButtonStyle style, Vector2 position) {
        ImageTextButton btn = new ImageTextButton(title, style);

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
        headerGroupFsm.update();
    }
}
