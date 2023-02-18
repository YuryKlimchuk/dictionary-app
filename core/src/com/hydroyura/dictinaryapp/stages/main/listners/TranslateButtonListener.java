package com.hydroyura.dictinaryapp.stages.main.listners;

import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

import java.util.stream.StreamSupport;


import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class TranslateButtonListener extends ClickListener {

    private TextButton.TextButtonStyle style;
    private TextButton.TextButtonStyle selectedStyle;

    public TranslateButtonListener(TextButton.TextButtonStyle style, TextButton.TextButtonStyle selectedStyle) {
        this.style = style;
        this.selectedStyle = selectedStyle;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Button button = ((Button) event.getListenerActor());
        if (button.getStyle().equals(style)) {
            button.setStyle(selectedStyle);
        } else {
            button.setStyle(style);
        }
    }
}
