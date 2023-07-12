package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
