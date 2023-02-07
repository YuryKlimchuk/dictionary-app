package com.hydroyura.dictinaryapp.httpclient.response.converter;

import com.badlogic.gdx.Net.HttpResponse;

// FIXME: remove it and replaced by JAVA standard functional interface
public interface ResponseConverter<DTO> {

    public DTO convert(HttpResponse response);

}
