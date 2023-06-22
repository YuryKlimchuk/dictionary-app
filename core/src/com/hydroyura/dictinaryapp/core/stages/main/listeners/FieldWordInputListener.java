package com.hydroyura.dictinaryapp.core.stages.main.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import com.hydroyura.dictinaryapp.core.http.autocomplete.AutocompleteAPI;
import com.hydroyura.dictinaryapp.core.http.autocomplete.IAutocompleteAPI;

@Bean(name = "FieldWordInputListener")
public class FieldWordInputListener implements TextField.TextFieldListener {

    @Inject(name = "AutocompleteAPI")
    private AutocompleteAPI autocompleteAPI;

    @Override
    public void keyTyped(TextField textField, char c) {
        Gdx.app.log(getClass().toString(), "inputted char [" + c + "] current value [" + textField.getText() + "]");
        autocompleteAPI.post(textField.getText());
    }
}
