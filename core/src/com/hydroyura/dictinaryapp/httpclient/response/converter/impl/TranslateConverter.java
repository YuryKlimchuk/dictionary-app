package com.hydroyura.dictinaryapp.httpclient.response.converter.impl;

import com.badlogic.gdx.Net.HttpResponse;
import com.hydroyura.dictinaryapp.httpclient.response.converter.ResponseConverter;
import com.hydroyura.dictinaryapp.httpclient.response.dto.DTOTranslateResponse;

public class TranslateConverter implements ResponseConverter<DTOTranslateResponse> {
    @Override
    public DTOTranslateResponse convert(HttpResponse response) {
        return null;
    }
}
