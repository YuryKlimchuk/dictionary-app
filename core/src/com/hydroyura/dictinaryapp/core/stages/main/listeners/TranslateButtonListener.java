package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.hydroyura.dictinaryapp.core.stages.StageConstants.*;

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
            button.setName(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID__SELECTED);
        } else {
            button.setStyle(style);
            button.setName(BODY_TRANSLATION_VARIANTS_TABLE_BUTTON_ID);
        }
    }
}
