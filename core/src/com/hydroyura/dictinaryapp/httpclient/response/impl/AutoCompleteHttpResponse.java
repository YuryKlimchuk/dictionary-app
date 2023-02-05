package com.hydroyura.dictinaryapp.httpclient.response.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.response.AbstractHttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.impl.AutoCompleteConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOAutoCompleteResponse;
import com.hydroyura.dictinaryapp.stages.main.MainStage;
import com.hydroyura.dictinaryapp.stages.main.fsm.body.BodyFSMStates;
import com.hydroyura.dictinaryapp.stages.main.fsm.header.HeaderWordInputFSMStates;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class AutoCompleteHttpResponse extends AbstractHttpResponse<DTOAutoCompleteResponse> {

    private AppStarter app = (AppStarter) Gdx.app.getApplicationListener();
    private Table table;

    private TextButton.TextButtonStyle style;

    public AutoCompleteHttpResponse() {
        this.converter = new AutoCompleteConverter();
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        Gdx.app.log(getClass().toString(), "handleHttpResponse - start");
        DTOAutoCompleteResponse convertedResponse = converter.convert(httpResponse);
        Gdx.app.log(getClass().toString(), "handleHttpResponse, response = " + convertedResponse.toString());

        if(table == null) table = findTable();
        if(style == null) style = findStyle();

        table.clearChildren();
        convertedResponse.getAutoCompleteTexts().forEach(item -> {
            TextButton button = new TextButton(item, style);

            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    TextButton button = (TextButton) event.getListenerActor();
                    Gdx.app.log(this.getClass().toString(), "clicked(), word = " + button.getText());

                    ((MainStage) event.getStage()).getFSM(BODY_ID).changeState(BodyFSMStates.WORD_ADD);
                    ((MainStage) event.getStage()).getFSM(HEADER_WORD_INPUT_ID).changeState(HeaderWordInputFSMStates.HIDE);

                }
            });
            table.add(button).row();
        });

    }

    private Table findTable() {
        return (Table) app.getMainStage().findActor(BODY_ID, BODY_WORDS_AUTOCOMPLETE_RESULT_ID);
    }

    private TextButtonStyle findStyle() {
        return app.getResource("skins/main-skin.json", Skin.class).get("autocomplete-result-label", TextButtonStyle.class);
    }
}
