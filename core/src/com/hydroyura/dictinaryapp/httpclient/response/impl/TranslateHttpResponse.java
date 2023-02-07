package com.hydroyura.dictinaryapp.httpclient.response.impl;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.AbstractHttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.impl.TranslateConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOTranslateResponse;

public class TranslateHttpResponse extends AbstractHttpResponse<DTOTranslateResponse> {

    public TranslateHttpResponse() {
        super();
        this.converter = new TranslateConverter();

    }

    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        super.handleHttpResponse(httpResponse);

        DTOTranslateResponse dto = converter.convert(httpResponse);


    }
}
