package com.hydroyura.dictinaryapp.httpclient.response.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.hydroyura.dictinaryapp.httpclient.response.AbstractHttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.impl.AutoCompleteConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOAutoCompleteResponse;
import com.hydroyura.dictinaryapp.stages.customs.Line;
import com.hydroyura.dictinaryapp.stages.main.fsm.footer.FooterMainFSMStates;
import com.hydroyura.dictinaryapp.stages.main.listners.AutoCompleteWordListener;

import static com.hydroyura.dictinaryapp.stages.main.MainStageConstants.*;

public class AutoCompleteHttpResponse extends AbstractHttpResponse<DTOAutoCompleteResponse> {

    private Table table;

    private TextButton.TextButtonStyle style;

    private ClickListener clickListener;

    public AutoCompleteHttpResponse() {
        super();
        this.converter = new AutoCompleteConverter();
        this.clickListener = new AutoCompleteWordListener();
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

            button.addListener(clickListener);

            table.add(button).width(table.getWidth()).expandX().align(Align.left).row();
            table.add(new Line()).row();

        });

    }

    private Table findTable() {
        return (Table) app.getMainStage().findActor(BODY_ID, BODY_WORDS_AUTOCOMPLETE_RESULT_ID);
    }

    private TextButtonStyle findStyle() {
        return app.getResource("skins/main-skin.json", Skin.class).get("autocomplete-result-label", TextButtonStyle.class);
    }
}
