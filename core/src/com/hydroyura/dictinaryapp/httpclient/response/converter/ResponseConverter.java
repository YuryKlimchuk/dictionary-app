package com.hydroyura.dictinaryapp.httpclient.response.converter;

import com.badlogic.gdx.Net.HttpResponse;

public interface ResponseConverter<DTO> {

    public DTO convert(HttpResponse response);

}
