package com.hydroyura.dictinaryapp.httpclient.response;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.httpclient.response.converter.ResponseConverter;

public abstract class AbstractHttpResponse<DTO> implements HttpResponseListener {

    protected ResponseConverter<DTO> converter;

    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        Gdx.app.log(getClass().toString(), "handleHttpResponse - start");
        DTO convertedResponse = converter.convert(httpResponse);
        Gdx.app.log(getClass().toString(), "handleHttpResponse, response = " + convertedResponse.toString());
    }

    @Override
    public void failed(Throwable t) {
        Gdx.app.log(getClass().toString(), "failed, t = " + t.toString());
    }

    @Override
    public void cancelled() {
        Gdx.app.log(getClass().toString(), "cancelled");
    }
}
