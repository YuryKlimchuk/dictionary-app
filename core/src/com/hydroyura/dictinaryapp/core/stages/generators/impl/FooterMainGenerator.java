package com.hydroyura.dictinaryapp.core.stages.generators.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.core.stages.customs.Background;
import com.hydroyura.dictinaryapp.core.stages.customs.Line;
import com.hydroyura.dictinaryapp.core.stages.generators.IGroupGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.FOOTER_MAIN_ID;

public class FooterMainGenerator implements IGroupGenerator {
    private ClickListener listener;


    public FooterMainGenerator(ClickListener listener) {
        this.listener = listener;
    }


    @Override
    public Group generate(Skin skin) {
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

        int POSITION_X = Gdx.graphics.getWidth() / 7;
        int POSITION_Y = POSITION_X / 2;
        for(Map.Entry<String, List<String>> entry: FOOTER_MAIN_BTNS_SETTINGS.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            ImageTextButton btn = new ImageTextButton(value.get(0), skin.get(value.get(1), ImageTextButton.ImageTextButtonStyle.class));
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
    }
}
