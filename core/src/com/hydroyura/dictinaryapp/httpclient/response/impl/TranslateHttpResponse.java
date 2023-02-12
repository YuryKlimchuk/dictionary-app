package com.hydroyura.dictinaryapp.httpclient.response.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.AbstractHttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.impl.TranslateConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOTranslateResponse;
import com.hydroyura.dictinaryapp.stages.main.fsm.body.BodyFSMStates;

public class TranslateHttpResponse extends AbstractHttpResponse<DTOTranslateResponse> {

    public String getTranslate() {
        return translate;
    }

    private String translate;
    private boolean isTranslateReady = false;

    public TranslateHttpResponse() {
        super();
        this.converter = new TranslateConverter();

    }

    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        //super.handleHttpResponse(httpResponse);

        DTOTranslateResponse dto = converter.convert(httpResponse);

        Gdx.app.log(this.getClass().toString(), "Translate is [" + dto.getTexts() + "]");


        isTranslateReady = true;
        translate = dto.getTexts();

        BodyFSMStates.SELECT_TRANSLATE.setTranslations();


    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public boolean isTranslateReady() {
        return isTranslateReady;
    }

    public void setTranslateReady(boolean translateReady) {
        isTranslateReady = translateReady;
    }
}
