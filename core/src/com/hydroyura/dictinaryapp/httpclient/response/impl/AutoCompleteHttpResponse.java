package com.hydroyura.dictinaryapp.httpclient.response.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.hydroyura.dictinaryapp.AppStarter;
import com.hydroyura.dictinaryapp.httpclient.response.AbstractHttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.impl.AutoCompleteConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOAutoCompleteResponse;

public class AutoCompleteHttpResponse extends AbstractHttpResponse<DTOAutoCompleteResponse> {

    private AppStarter app = (AppStarter) Gdx.app.getApplicationListener();
    private VerticalGroup searchResult = app.getSearchResult();



    public AutoCompleteHttpResponse() {
        this.converter = new AutoCompleteConverter();
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        Gdx.app.log(getClass().toString(), "handleHttpResponse - start");
        DTOAutoCompleteResponse convertedResponse = converter.convert(httpResponse);
        Gdx.app.log(getClass().toString(), "handleHttpResponse, response = " + convertedResponse.toString());

        if(searchResult == null) searchResult = app.getSearchResult();

        searchResult.clearChildren();

        Label.LabelStyle style = app
                .getResource("skin/skin-composer-ui.json", Skin.class)
                .get("title", Label.LabelStyle.class);

        convertedResponse.getAutoCompleteTexts().forEach(item -> searchResult.addActor(new Label(item, style)));


    }
}
